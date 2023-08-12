package com.ssafy.campfire.algorithm.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlgoFiftyRank is a Querydsl query type for AlgoFiftyRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlgoFiftyRank extends EntityPathBase<AlgoFiftyRank> {

    private static final long serialVersionUID = 968252134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlgoFiftyRank algoFiftyRank = new QAlgoFiftyRank("algoFiftyRank");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    public final QAlgorithm algorithm;

    public final com.ssafy.campfire.bootcamp.domain.QBootcamp bootcamp;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QAlgoFiftyRank(String variable) {
        this(AlgoFiftyRank.class, forVariable(variable), INITS);
    }

    public QAlgoFiftyRank(Path<? extends AlgoFiftyRank> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlgoFiftyRank(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlgoFiftyRank(PathMetadata metadata, PathInits inits) {
        this(AlgoFiftyRank.class, metadata, inits);
    }

    public QAlgoFiftyRank(Class<? extends AlgoFiftyRank> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.algorithm = inits.isInitialized("algorithm") ? new QAlgorithm(forProperty("algorithm")) : null;
        this.bootcamp = inits.isInitialized("bootcamp") ? new com.ssafy.campfire.bootcamp.domain.QBootcamp(forProperty("bootcamp")) : null;
    }

}

