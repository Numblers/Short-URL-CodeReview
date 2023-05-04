package numble.shorturl.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import numble.shorturl.application.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("url/issue")
    public String urlIssue (String url){
        return urlService.urlIssue(url);
    }

    @GetMapping("{url}")
    public void callUrl(HttpServletRequest request , HttpServletResponse response, @PathVariable("url")String encodingUrl) throws IOException {
        response.sendRedirect(urlService.callUrl(request, encodingUrl));

    }
}
