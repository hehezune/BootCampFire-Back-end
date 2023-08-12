package com.ssafy.campfire.bootcamp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBootRegion is a Querydsl query type for BootRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBootRegion extends EntityPathBase<BootRegion> {

    private static final long serialVersionUID = -1657852699L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBootRegion bootRegion = new QBootRegion("bootRegion");

    public final QBootcamp bootcamp;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRegion region;

    public QBootRegion(String variable) {
        this(BootRegion.class, forVariable(variable), INITS);
    }

    public QBootRegion(Path<? extends BootRegion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBootRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBootRegion(PathMetadata metadata, PathInits inits) {
        this(BootRegion.class, metadata, inits);
    }

    public QBootRegion(Class<? extends BootRegion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bootcamp = inits.isInitialized("bootcamp") ? new QBootcamp(forProperty("bootcamp")) : null;
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
    }

}

