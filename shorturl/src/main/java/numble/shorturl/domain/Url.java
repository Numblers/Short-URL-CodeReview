package numble.shorturl.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originUrl;
    private String shortUrl;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime expiredTime;

    @Builder
    public Url(String originUrl, String shortUrl, Status status, int expireDate){
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
        this.status = status;
        this.expiredTime = LocalDateTime.now(ZoneOffset.UTC).plusDays(expireDate);
    }

}
