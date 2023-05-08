package sondho.linkerly.util;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;
import sondho.linkerly.domain.Url;
import sondho.linkerly.exception.InvalidUrlException;
import sondho.linkerly.repository.UrlRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UrlUtils {

    private final UrlRepository urlRepository;

    // TODO: 더 나은 방법 찾아보기
    public String generateLinkUrl() {
        int URL_LEN = 8;
        String linkUrl = RandomString.make(URL_LEN);
        while (urlRepository.existsByLinkUrl(linkUrl)) {
            linkUrl = RandomString.make(URL_LEN);
        }
        return linkUrl;
    }

    public String getHostOfRefererFromRequest(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        try {
            if (referer == null) {
                return null;
            }
            return new URL(referer).getHost();
        } catch (MalformedURLException e) {
            throw new InvalidUrlException(referer);
        }
    }

    public boolean isExpiredUrl(Url url) {
        return url.getExpiryDate().isBefore(LocalDateTime.now());
    }
}
