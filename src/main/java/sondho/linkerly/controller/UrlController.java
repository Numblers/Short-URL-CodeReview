package sondho.linkerly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sondho.linkerly.dto.CreateShortUrlRequest;
import sondho.linkerly.dto.LinkUrlResponse;
import sondho.linkerly.service.UrlService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/urls")
    @ResponseBody
    public LinkUrlResponse saveLink(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
        return urlService.createLinkUrl(createShortUrlRequest);
    }

    @GetMapping("/{linkUrl}")
    @ResponseBody
    public ResponseEntity<?> accessUrl(HttpServletRequest request, @PathVariable("linkUrl") String linkUrl) {
        return urlService.accessUrl(request, linkUrl);
    }

    @DeleteMapping("/{linkUrl}")
    @ResponseBody
    public void deleteShortUrl(@PathVariable("linkUrl") String linkUrl) {
        urlService.deleteShortUrl(linkUrl);
    }
}
