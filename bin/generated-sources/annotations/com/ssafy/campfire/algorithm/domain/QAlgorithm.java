package com.ssafy.campfire.algorithm.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlgorithm is a Querydsl query type for Algorithm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlgorithm extends EntityPathBase<Algorithm> {

    private static final long serialVersionUID = 248853716L;

    public static final QAlgorithm algorithm = new QAlgorithm("algorithm");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath link = createString("link");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QAlgorithm(String variable) {
        super(Algorithm.class, forVariable(variable));
    }

    public QAlgorithm(Path<? extends Algorithm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlgorithm(PathMetadata metadata) {
        super(Algorithm.class, metadata);
    }

}

