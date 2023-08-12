package com.ssafy.campfire.bootcamp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBootLanguage is a Querydsl query type for BootLanguage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBootLanguage extends EntityPathBase<BootLanguage> {

    private static final long serialVersionUID = -686825207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBootLanguage bootLanguage = new QBootLanguage("bootLanguage");

    public final QBootcamp bootcamp;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLanguage language;

    public QBootLanguage(String variable) {
        this(BootLanguage.class, forVariable(variable), INITS);
    }

    public QBootLanguage(Path<? extends BootLanguage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBootLanguage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBootLanguage(PathMetadata metadata, PathInits inits) {
        this(BootLanguage.class, metadata, inits);
    }

    public QBootLanguage(Class<? extends BootLanguage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bootcamp = inits.isInitialized("bootcamp") ? new QBootcamp(forProperty("bootcamp")) : null;
        this.language = inits.isInitialized("language") ? new QLanguage(forProperty("language")) : null;
    }

}

