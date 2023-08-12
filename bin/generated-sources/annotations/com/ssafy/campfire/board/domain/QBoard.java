package com.ssafy.campfire.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -949915468L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.ssafy.campfire.utils.domain.QBaseEntity _super = new com.ssafy.campfire.utils.domain.QBaseEntity(this);

    public final BooleanPath anonymous = createBoolean("anonymous");

    public final com.ssafy.campfire.bootcamp.domain.QBootcamp bootcamp;

    public final com.ssafy.campfire.category.domain.QCategory category;

    public final NumberPath<Integer> commentCnt = createNumber("commentCnt", Integer.class);

    public final SetPath<com.ssafy.campfire.comment.domain.Comment, com.ssafy.campfire.comment.domain.QComment> comments = this.<com.ssafy.campfire.comment.domain.Comment, com.ssafy.campfire.comment.domain.QComment>createSet("comments", com.ssafy.campfire.comment.domain.Comment.class, com.ssafy.campfire.comment.domain.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCnt = createNumber("likeCnt", Integer.class);

    public final SetPath<com.ssafy.campfire.likes.domain.Likes, com.ssafy.campfire.likes.domain.QLikes> likes = this.<com.ssafy.campfire.likes.domain.Likes, com.ssafy.campfire.likes.domain.QLikes>createSet("likes", com.ssafy.campfire.likes.domain.Likes.class, com.ssafy.campfire.likes.domain.QLikes.class, PathInits.DIRECT2);

    public final NumberPath<Integer> maxRef = createNumber("maxRef", Integer.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final com.ssafy.campfire.user.domain.QUser user;

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bootcamp = inits.isInitialized("bootcamp") ? new com.ssafy.campfire.bootcamp.domain.QBootcamp(forProperty("bootcamp")) : null;
        this.category = inits.isInitialized("category") ? new com.ssafy.campfire.category.domain.QCategory(forProperty("category"), inits.get("category")) : null;
        this.user = inits.isInitialized("user") ? new com.ssafy.campfire.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

