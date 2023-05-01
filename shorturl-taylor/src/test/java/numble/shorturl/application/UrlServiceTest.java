package numble.shorturl.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UrlEncodingService urlEncodingService;

    @Test
    @DisplayName("인코딩_URL_생성")
    public void urlEncoding() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/url/issue?url=http://www.naver.com")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("인코딩_URL_호출")
//    public void callUrl(){
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/B"))
//                .andExpect(status().isOk());
//    }

    @Test
    @DisplayName("URL 디코딩")
    public void decodingUrl(){
        Long urlId = urlEncodingService.decoding("N");
        System.out.println(urlId);
    }



}