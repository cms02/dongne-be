package com.dongne.dongnebe.domain.category.sub_category.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubCategory is a Querydsl query type for SubCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubCategory extends EntityPathBase<SubCategory> {

    private static final long serialVersionUID = 1239777748L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubCategory subCategory = new QSubCategory("subCategory");

    public final ListPath<com.dongne.dongnebe.domain.board.entity.Board, com.dongne.dongnebe.domain.board.entity.QBoard> boardList = this.<com.dongne.dongnebe.domain.board.entity.Board, com.dongne.dongnebe.domain.board.entity.QBoard>createList("boardList", com.dongne.dongnebe.domain.board.entity.Board.class, com.dongne.dongnebe.domain.board.entity.QBoard.class, PathInits.DIRECT2);

    public final com.dongne.dongnebe.domain.category.main_category.entity.QMainCategory mainCategory;

    public final StringPath name = createString("name");

    public final NumberPath<Long> subCategoryId = createNumber("subCategoryId", Long.class);

    public QSubCategory(String variable) {
        this(SubCategory.class, forVariable(variable), INITS);
    }

    public QSubCategory(Path<? extends SubCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubCategory(PathMetadata metadata, PathInits inits) {
        this(SubCategory.class, metadata, inits);
    }

    public QSubCategory(Class<? extends SubCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainCategory = inits.isInitialized("mainCategory") ? new com.dongne.dongnebe.domain.category.main_category.entity.QMainCategory(forProperty("mainCategory")) : null;
    }

}

