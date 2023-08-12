package com.ssafy.campfire.algorithm.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlgoManyRank is a Querydsl query type for AlgoManyRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlgoManyRank extends EntityPathBase<AlgoManyRank> {

    private static final long serialVersionUID = 1761149977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlgoManyRank algoManyRank = new QAlgoManyRank("algoManyRank");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    public final QAlgorithm algorithm;

    public final com.ssafy.campfire.bootcamp.domain.QBootcamp bootcamp;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.ssafy.campfire.user.domain.QUser user;

    public QAlgoManyRank(String variable) {
        this(AlgoManyRank.class, forVariable(variable), INITS);
    }

    public QAlgoManyRank(Path<? extends AlgoManyRank> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlgoManyRank(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlgoManyRank(PathMetadata metadata, PathInits inits) {
        this(AlgoManyRank.class, metadata, inits);
    }

    public QAlgoManyRank(Class<? extends AlgoManyRank> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.algorithm = inits.isInitialized("algorithm") ? new QAlgorithm(forProperty("algorithm")) : null;
        this.bootcamp = inits.isInitialized("bootcamp") ? new com.ssafy.campfire.bootcamp.domain.QBootcamp(forProperty("bootcamp")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.campfire.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

