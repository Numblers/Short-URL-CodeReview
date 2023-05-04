package numble.shorturl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlStatisticsDto {
    private Long count;
    private LocalDate callDate;

    public UrlStatisticsDto(Long count, String callDate) {
        this.count = count;
        this.callDate = LocalDate.parse(callDate);
    }
}
