package com.ssafy.campfire.bootcamp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrack is a Querydsl query type for Track
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrack extends EntityPathBase<Track> {

    private static final long serialVersionUID = 1408636300L;

    public static final QTrack track = new QTrack("track");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QTrack(String variable) {
        super(Track.class, forVariable(variable));
    }

    public QTrack(Path<? extends Track> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrack(PathMetadata metadata) {
        super(Track.class, metadata);
    }

}

