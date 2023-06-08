package numble.shorturl.infrastructure.persistence;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import numble.shorturl.domain.Status;
import numble.shorturl.domain.Url;
import numble.shorturl.domain.UrlCall;
import numble.shorturl.domain.dto.UrlStatisticsDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static numble.shorturl.domain.QUrl.url;
import static numble.shorturl.domain.QUrlCall.urlCall;


@Repository
public class UrlCallQueryRepository {

    private final JPAQueryFactory query;

    public UrlCallQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<UrlStatisticsDto> findStatisticsByUrlId(String shortUrl){
        return query.select(
                Projections.bean(UrlStatisticsDto.class
                        , urlCall.id.count().as("count")
                        , Expressions.stringTemplate("DATE({0})", urlCall.createdTime).as("callDate")
                ))
                .from(urlCall)
                .join(urlCall.url, url)
                .where(url.shortUrl.eq(shortUrl))
                .groupBy(Expressions.stringTemplate("DATE({0})", urlCall.createdTime))
                .fetch();
    }
}
