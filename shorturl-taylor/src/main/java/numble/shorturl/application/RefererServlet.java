package numble.shorturl.application;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefererServlet extends HttpServlet {
    protected String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("User-Agent");
        System.out.println("Referer: " + referer);
        return referer;
    }
}
