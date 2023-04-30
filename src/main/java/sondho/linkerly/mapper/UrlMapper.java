package sondho.linkerly.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sondho.linkerly.domain.Url;
import sondho.linkerly.dto.CreateShortUrlRequest;
import sondho.linkerly.dto.LinkUrlResponse;

@Component
public class UrlMapper {

    @Value("${env.host}")
    String host;

    public Url createShortUrlRequestToUrl(CreateShortUrlRequest request, String linkUrl) {
        return Url.builder()
                .originalUrl(request.getOriginalUrl())
                .expiryDate(request.getExpiryDate())
                .linkUrl(linkUrl)
                .build();
    }

    public LinkUrlResponse urlToLinkUrlResponse(Url url) {
        return LinkUrlResponse.builder()
                .linkUrl(host + "/" + url.getLinkUrl())
                .expiryDate(url.getExpiryDate())
                .build();
    }
}
