package numble.shorturl.infrastructure.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static numble.shorturl.domain.QUrl.url;

@Repository
public class UrlQueryRepository {

    private final JPAQueryFactory query;

    public UrlQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Long findUrlIdMax(){
        return query.select(url.id.max().coalesce(0L))
                .from(url)
                .fetchOne();
    }
}
