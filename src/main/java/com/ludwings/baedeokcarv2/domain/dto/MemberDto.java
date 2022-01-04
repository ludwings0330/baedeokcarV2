package com.ludwings.baedeokcarv2.domain.dto;

import com.ludwings.baedeokcarv2.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDto {
    @Builder.Default
    private String loginId = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private String name = "";

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .build();
    }
}
