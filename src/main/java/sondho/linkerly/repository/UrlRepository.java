package sondho.linkerly.repository;

import org.springframework.data.repository.CrudRepository;
import sondho.linkerly.domain.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, String> {

    Optional<Url> findByOriginalUrl(String originalUrl);

    Optional<Url> findByLinkUrl(String linkUrl);

    boolean existsByLinkUrl(String linkUrl);

    void deleteById(Long id);
}
