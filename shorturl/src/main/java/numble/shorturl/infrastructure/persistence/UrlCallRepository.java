package numble.shorturl.infrastructure.persistence;

import numble.shorturl.domain.UrlCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlCallRepository extends JpaRepository<UrlCall, Long> {
}
