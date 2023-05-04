package numble.shorturl.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(schema = "url_call")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UrlCall extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Browser browser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;

    @Builder
    public UrlCall(Url url, Browser browser){
        this.browser = browser;
        this.url = url;
    }

}
