package sondho.linkerly.service;

import org.springframework.http.ResponseEntity;
import sondho.linkerly.dto.CreateShortUrlRequest;
import sondho.linkerly.dto.LinkUrlResponse;

import javax.servlet.http.HttpServletRequest;

public interface UrlService {

    LinkUrlResponse createLinkUrl(CreateShortUrlRequest createShortUrlRequest);

    ResponseEntity<?> accessUrl(HttpServletRequest request, String linkUrl);

    void deleteShortUrl(String linkUrl);
}
