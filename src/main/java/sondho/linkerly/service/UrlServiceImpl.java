package sondho.linkerly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sondho.linkerly.domain.Statistic;
import sondho.linkerly.domain.Url;
import sondho.linkerly.dto.CreateShortUrlRequest;
import sondho.linkerly.dto.LinkUrlResponse;
import sondho.linkerly.dto.StatisticResponse;
import sondho.linkerly.exception.ExpiredUrlException;
import sondho.linkerly.exception.UrlNotExistsException;
import sondho.linkerly.mapper.StatisticMapper;
import sondho.linkerly.mapper.UrlMapper;
import sondho.linkerly.repository.StatisticRepository;
import sondho.linkerly.repository.UrlRepository;
import sondho.linkerly.util.UrlUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final StatisticRepository statisticRepository;
    private final UrlMapper urlMapper;
    private final StatisticMapper statisticMapper;
    private final UrlUtils urlUtils;

    @Override
    public LinkUrlResponse createLinkUrl(CreateShortUrlRequest createShortUrlRequest) {
        Url url = urlRepository.findByOriginalUrl(createShortUrlRequest.getOriginalUrl())
                .orElseGet(() -> urlMapper.createShortUrlRequestToUrl(createShortUrlRequest, urlUtils.generateLinkUrl()));
        if (urlUtils.isExpiredUrl(url)) {
            url.setLinkUrl(urlUtils.generateLinkUrl());
        }
        BeanUtils.copyProperties(createShortUrlRequest, url);
        urlRepository.save(url);
        return urlMapper.urlToLinkUrlResponse(url);
    }

    @Override
    public ResponseEntity<?> accessUrl(HttpServletRequest request, String linkUrl) {
        if (linkUrl.charAt(linkUrl.length() - 1) == '+') {
            Url url = getAndValidateUrl(linkUrl.substring(0, linkUrl.length() - 1));
            return ResponseEntity.status(HttpStatus.OK).body(getStatisticListByUrlId(url.getId()));
        } else {
            Url url = getAndValidateUrl(linkUrl);
            createStatistic(request, url);
            return redirectOriginUrl(url);
        }
    }

    @Override
    public void deleteShortUrl(String linkUrl) {
        Url url = urlRepository.findByLinkUrl(linkUrl)
                .orElseThrow(() -> new UrlNotExistsException(linkUrl));
        statisticRepository.deleteAllByUrl_Id(url.getId());
        urlRepository.deleteById(url.getId());
    }

    private Url getAndValidateUrl(String linkUrl) {
        return urlRepository.findByLinkUrl(linkUrl)
                .map(url -> {
                    if (urlUtils.isExpiredUrl(url)) {
                        throw new ExpiredUrlException(linkUrl);
                    }
                    return url;
                })
                .orElseThrow(() -> new UrlNotExistsException(linkUrl));
    }

    private void createStatistic(HttpServletRequest request, Url url) {
        String referer = urlUtils.getHostOfRefererFromRequest(request);
        Statistic statistic = Statistic.builder()
                .url(url)
                .referer(referer)
                .build();
        statisticRepository.save(statistic);
    }

    private ResponseEntity<?> redirectOriginUrl(Url url) {
        String originalUrl = url.getOriginalUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    private List<StatisticResponse> getStatisticListByUrlId(Long urlId) {
        return statisticRepository.findAllByUrl_Id(urlId)
                .stream()
                .map(statisticMapper::statisticToStatisticResponse)
                .toList();
    }
}
