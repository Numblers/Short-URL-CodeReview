package numble.shorturl.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import numble.shorturl.domain.Browser;
import numble.shorturl.domain.Status;
import numble.shorturl.domain.Url;
import numble.shorturl.domain.UrlCall;
import numble.shorturl.domain.dto.CallUrlDto;
import numble.shorturl.domain.dto.UrlShortDto;
import numble.shorturl.infrastructure.persistence.UrlCallQueryRepository;
import numble.shorturl.infrastructure.persistence.UrlCallRepository;
import numble.shorturl.infrastructure.persistence.UrlQueryRepository;
import numble.shorturl.infrastructure.persistence.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlCallRepository urlCallRepository;
    private final UrlQueryRepository urlQueryRepository;
    private final UrlCallQueryRepository urlCallQueryRepository;
    private final UrlEncodingService urlEncodingService;
    private final RefererServlet refererServlet;

    private static final String MAIN_URL = "localhost:8080/";

    @Transactional
    public String urlIssue(UrlShortDto urlShortDto) {

        urlToStatus(urlShortDto);

        long urlMaxId = urlQueryRepository.findUrlIdMax() + 1;
        String encodingUrl = UrlEncodingService.encoding(urlMaxId);

        urlRepository.save(
                Url.builder()
                        .originUrl(urlShortDto.getUrl())
                        .shortUrl(encodingUrl)
                        .status(urlShortDto.getStatus())
                        .expireDate(urlShortDto.getExpireDate())
                        .build());
        return MAIN_URL + encodingUrl;
    }

    @Transactional
    public CallUrlDto callUrl(HttpServletRequest request, String encodingUrl) {

        if(checkUrlStatistics(encodingUrl)){
            String shortUrl = encodingUrl.substring(0, encodingUrl.length() - 1);
            CallUrlDto urlDto = CallUrlDto.builder()
                    .urlStatisticsDto(urlCallQueryRepository.findStatisticsByUrlId(shortUrl))
                    .build();
            return  urlDto;
        }

        Url findUrl = urlQueryRepository.findNonRemoveUrlById(encodingUrl).get();

        if(checkUrlExpiration(findUrl)){
            urlHistorySave(request, findUrl);
            return CallUrlDto.builder()
                    .url(findUrl.getOriginUrl())
                    .build();
        }
        return CallUrlDto.builder()
                .message("url이 만료되었습니다.")
                .build();
    }

    private boolean checkUrlStatistics(String encodingUrl) {
        return encodingUrl.charAt(encodingUrl.length() - 1) == '+';

    }

    private void urlToStatus(UrlShortDto urlShortDto) {
        Optional<Url> findUrl = urlQueryRepository.findNonRemoveByOriginUrl(urlShortDto.getUrl());
        findUrl.ifPresent(url -> url.setStatus(Status.REMOVE));
    }

    private boolean checkUrlExpiration(Url findUrl){

        if(findUrl.getStatus().equals(Status.EXPIRATION)){
            LocalDateTime severTime = LocalDateTime.now();
            return severTime.isBefore(findUrl.getExpiredTime()) ;
        }
        return true;
    }

    private Browser searchBrowser(HttpServletRequest request) {
        String referer = refererServlet.getReferer(request);
        return Arrays.stream(Browser.values())
                .filter(b -> referer.contains(b.toString()))
                .findAny().get();
    }

    private void urlHistorySave(HttpServletRequest request, Url url) {
        Browser browser = searchBrowser(request);

        urlCallRepository.save(
                UrlCall.builder()
                        .browser(browser)
                        .url(url)
                        .build()
        );

    }


}
