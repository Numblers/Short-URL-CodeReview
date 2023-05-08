package sondho.linkerly.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
}
