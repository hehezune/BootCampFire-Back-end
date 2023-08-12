package com.ssafy.campfire.bootcamp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBootcamp is a Querydsl query type for Bootcamp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBootcamp extends EntityPathBase<Bootcamp> {

    private static final long serialVersionUID = -698428270L;

    public static final QBootcamp bootcamp = new QBootcamp("bootcamp");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    public final NumberPath<Integer> algoCnt = createNumber("algoCnt", Integer.class);

    public final BooleanPath card = createBoolean("card");

    public final NumberPath<Double> cost = createNumber("cost", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final BooleanPath hasCodingtest = createBoolean("hasCodingtest");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final StringPath onOff = createString("onOff");

    public final StringPath process = createString("process");

    public final NumberPath<Integer> reviewCnt = createNumber("reviewCnt", Integer.class);

    public final StringPath schedule = createString("schedule");

    public final StringPath siteUrl = createString("siteUrl");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final BooleanPath support = createBoolean("support");

    public final NumberPath<Double> totalScore = createNumber("totalScore", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QBootcamp(String variable) {
        super(Bootcamp.class, forVariable(variable));
    }

    public QBootcamp(Path<? extends Bootcamp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBootcamp(PathMetadata metadata) {
        super(Bootcamp.class, metadata);
    }

}

