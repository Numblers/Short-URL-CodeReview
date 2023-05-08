package sondho.linkerly.mapper;

import org.springframework.stereotype.Component;
import sondho.linkerly.domain.Statistic;
import sondho.linkerly.dto.StatisticResponse;

@Component
public class StatisticMapper {

    public StatisticResponse statisticToStatisticResponse(Statistic statistic) {
        return StatisticResponse.builder()
                .accessedDate(statistic.getAccessedDate())
                .referer(statistic.getReferer())
                .build();
    }
}
