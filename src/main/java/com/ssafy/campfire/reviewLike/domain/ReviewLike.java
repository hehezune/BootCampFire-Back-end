package com.ssafy.campfire.reviewLike.domain;

import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.user.domain.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "review_like")
public class ReviewLike {
    /*
     * id : pk
     * review : 좋아요를 누른 후기
     * user : 좋아요를 누른 사용자
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public ReviewLike(Review review, User user){
        this.review = review;
        this.user = user;
    }

}
