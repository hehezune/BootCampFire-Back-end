package com.ssafy.campfire.user.dto.request;

import com.ssafy.campfire.user.domain.dto.PermissionOk;

import javax.validation.constraints.NotBlank;

public record UserPermissionRequest(
    @NotBlank(message = "부트캠프는 필수 입력입니다.")
    Long bootcampId
){
        public PermissionOk toDto () {return new PermissionOk(this.bootcampId);}
}