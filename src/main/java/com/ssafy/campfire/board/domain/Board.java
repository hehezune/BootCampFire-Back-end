package com.ssafy.campfire.board.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    /*
     * id : pk
     * user : 글쓴이
     * category : 글 카테고리
     * title : 글 제목
     * content : 글 내용
     * anonymous : 익명
     * commentCnt : 댓글 수
     * likeCnt : 좋아요 수
     * views : 조회수
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean anonymous;

    private Integer commentCnt;

    private Integer likeCnt;

    private Integer view;

    public Board(User user, String title, String content, Boolean anonymous){
        this.user = user;
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
        this.commentCnt = 0;
        this.likeCnt = 0;
        this.view = 0;
    }

    public void setCategory(User user, Category category) {
        this.category = category;
        this.bootcamp = user.getBootcamp();
    }
}
