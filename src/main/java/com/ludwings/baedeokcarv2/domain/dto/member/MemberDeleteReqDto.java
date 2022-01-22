package com.ludwings.baedeokcarv2.domain.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberDeleteReqDto {
    String loginId;
}