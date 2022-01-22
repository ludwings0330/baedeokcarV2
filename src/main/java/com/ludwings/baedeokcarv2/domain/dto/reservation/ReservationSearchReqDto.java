package com.ludwings.baedeokcarv2.domain.dto.reservation;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReservationSearchReqDto {
    String LoginId;
    Long carId;
}
