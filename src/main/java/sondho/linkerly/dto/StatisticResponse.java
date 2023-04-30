package sondho.linkerly.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StatisticResponse {
    private LocalDateTime accessedDate;
    private String referer;
}
