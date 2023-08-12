package com.ssafy.campfire.bootcamp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBootTrack is a Querydsl query type for BootTrack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBootTrack extends EntityPathBase<BootTrack> {

    private static final long serialVersionUID = -189798086L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBootTrack bootTrack = new QBootTrack("bootTrack");

    public final QBootcamp bootcamp;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTrack track;

    public QBootTrack(String variable) {
        this(BootTrack.class, forVariable(variable), INITS);
    }

    public QBootTrack(Path<? extends BootTrack> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBootTrack(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBootTrack(PathMetadata metadata, PathInits inits) {
        this(BootTrack.class, metadata, inits);
    }

    public QBootTrack(Class<? extends BootTrack> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bootcamp = inits.isInitialized("bootcamp") ? new QBootcamp(forProperty("bootcamp")) : null;
        this.track = inits.isInitialized("track") ? new QTrack(forProperty("track")) : null;
    }

}

