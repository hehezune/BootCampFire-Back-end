package com.ssafy.campfire.review.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -193194660L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    public final NumberPath<Integer> backUp = createNumber("backUp", Integer.class);

    public final StringPath bad = createString("bad");

    public final com.ssafy.campfire.bootcamp.domain.QBootcamp bootcamp;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> curriculum = createNumber("curriculum", Integer.class);

    public final StringPath good = createString("good");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRecommend = createBoolean("isRecommend");

    public final NumberPath<Integer> likeCnt = createNumber("likeCnt", Integer.class);

    public final NumberPath<Integer> management = createNumber("management", Integer.class);

    public final NumberPath<Integer> mood = createNumber("mood", Integer.class);

    public final NumberPath<Integer> potential = createNumber("potential", Integer.class);

    public final SetPath<com.ssafy.campfire.reviewLike.domain.ReviewLike, com.ssafy.campfire.reviewLike.domain.QReviewLike> reviewLikeSet = this.<com.ssafy.campfire.reviewLike.domain.ReviewLike, com.ssafy.campfire.reviewLike.domain.QReviewLike>createSet("reviewLikeSet", com.ssafy.campfire.reviewLike.domain.ReviewLike.class, com.ssafy.campfire.reviewLike.domain.QReviewLike.class, PathInits.DIRECT2);

    public final NumberPath<Double> score = createNumber("score", Double.class);

    public final StringPath tip = createString("tip");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.ssafy.campfire.user.domain.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bootcamp = inits.isInitialized("bootcamp") ? new com.ssafy.campfire.bootcamp.domain.QBootcamp(forProperty("bootcamp")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.campfire.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

