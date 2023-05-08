package sondho.linkerly.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LinkUrlResponse {

    private String linkUrl;

    private LocalDateTime expiryDate;
}
