package com.ssafy.campfire.review.domain;

import com.ssafy.campfire.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review_like")
public class ReviewLike {
    /*
     * id : pk
     * review : 좋아요를 누른 후기
     * user : 좋아요를 누른 사용자
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
