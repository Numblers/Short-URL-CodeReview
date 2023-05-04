package numble.shorturl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallUrlDto {
    private String url;
    private String message;
    private int total;
    private List<UrlStatisticsDto> urlStatisticsDto;
}
