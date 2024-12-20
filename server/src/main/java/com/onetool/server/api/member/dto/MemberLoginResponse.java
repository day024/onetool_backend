package com.onetool.server.api.member.dto;

import lombok.Builder;

public record MemberLoginResponse(
        String accessToken,
        String refreshToken
) {

    @Builder
    public MemberLoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}