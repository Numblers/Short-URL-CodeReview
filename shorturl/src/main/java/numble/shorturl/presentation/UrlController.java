package numble.shorturl.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import numble.shorturl.application.UrlService;
import numble.shorturl.domain.dto.CallUrlDto;
import numble.shorturl.domain.dto.UrlShortDto;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("url/issue")
    public String urlIssue (@RequestBody UrlShortDto urlShortDto){
        System.out.println(urlShortDto);
        return urlService.urlIssue(urlShortDto);
    }

    @GetMapping("{url}")
    public CallUrlDto callUrl(HttpServletRequest request , HttpServletResponse response, @PathVariable("url")String encodingUrl) throws IOException {
        CallUrlDto callUrlDto = urlService.callUrl(request, encodingUrl);
        System.out.println(callUrlDto);
        if(callUrlDto.getUrl() == null){
            return  urlService.callUrl(request, encodingUrl);
        }
        response.sendRedirect(urlService.callUrl(request, encodingUrl).getUrl());
        return null;
    }
}
