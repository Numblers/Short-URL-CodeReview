package sondho.linkerly.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistic")
@EntityListeners(AuditingEntityListener.class)
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Url.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    @CreatedDate
    @Column(name = "accessed_data")
    private LocalDateTime accessedDate;

    @Column(name = "referer")
    private String referer;
}