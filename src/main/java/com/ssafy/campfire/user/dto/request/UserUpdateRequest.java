package com.ssafy.campfire.user.dto.request;

import com.ssafy.campfire.user.domain.dto.UserUpdate;

import javax.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "닉네임은 필수입니다.")
        String nickname,
        String imgUrl,
        String bojId
) {
    public UserUpdate toDto() {
        return new UserUpdate(this.nickname, this.imgUrl, this.bojId);
    }
}
