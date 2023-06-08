package numble.shorturl.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/index")
    public String webIndexPage(){
        return "index";
    }
}
