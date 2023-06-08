package numble.shorturl.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUrl is a Querydsl query type for Url
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUrl extends EntityPathBase<Url> {

    private static final long serialVersionUID = -900499673L;

    public static final QUrl url = new QUrl("url");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final DateTimePath<java.time.LocalDateTime> expiredTime = createDateTime("expiredTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originUrl = createString("originUrl");

    public final StringPath shortUrl = createString("shortUrl");

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public QUrl(String variable) {
        super(Url.class, forVariable(variable));
    }

    public QUrl(Path<? extends Url> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUrl(PathMetadata metadata) {
        super(Url.class, metadata);
    }

}

