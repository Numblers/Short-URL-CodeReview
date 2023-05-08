package sondho.linkerly.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateShortUrlRequest {

    private String originalUrl;

    private LocalDateTime expiryDate;
}
