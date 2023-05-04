package numble.shorturl.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUrlCall is a Querydsl query type for UrlCall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUrlCall extends EntityPathBase<UrlCall> {

    private static final long serialVersionUID = -133858779L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUrlCall urlCall = new QUrlCall("urlCall");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final EnumPath<Browser> browser = createEnum("browser", Browser.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUrl url;

    public QUrlCall(String variable) {
        this(UrlCall.class, forVariable(variable), INITS);
    }

    public QUrlCall(Path<? extends UrlCall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUrlCall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUrlCall(PathMetadata metadata, PathInits inits) {
        this(UrlCall.class, metadata, inits);
    }

    public QUrlCall(Class<? extends UrlCall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.url = inits.isInitialized("url") ? new QUrl(forProperty("url")) : null;
    }

}

