package numble.shorturl.domain.dto;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import numble.shorturl.domain.Status;
import numble.shorturl.domain.converter.StatusConverter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortDto {
    private String url;
    private int expireDate;
    @Convert(converter = StatusConverter.class)
    private Status status;
}
