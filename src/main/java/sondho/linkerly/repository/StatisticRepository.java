package sondho.linkerly.repository;

import org.springframework.data.repository.CrudRepository;
import sondho.linkerly.domain.Statistic;

import java.util.List;

public interface StatisticRepository extends CrudRepository<Statistic, String> {

    List<Statistic> findAllByUrl_Id(Long urlId);

    void deleteAllByUrl_Id(Long urlId);
}
