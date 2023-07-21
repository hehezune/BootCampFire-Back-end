package com.ssafy.campfire.board.domain;

import com.ssafy.campfire.category.domain.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user join 필요
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id")
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean anonymous;

    private Integer commentCnt;

    private Integer likeCnt;

    private Integer view;

    public Board(String title, String content, Boolean anonymous){
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
    }

    public void setCategory(Category category){
        this.category = category;
    }

}
