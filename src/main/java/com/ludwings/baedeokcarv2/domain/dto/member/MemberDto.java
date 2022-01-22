package com.ludwings.baedeokcarv2.domain.dto.member;

import com.ludwings.baedeokcarv2.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public
    MemberDto(Member entity) {
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
    }
}
