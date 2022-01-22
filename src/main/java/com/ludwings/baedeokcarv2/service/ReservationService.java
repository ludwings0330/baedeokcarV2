package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.reservation.*;

import java.util.List;

public interface ReservationService {
    void createReservation(ReservationCreateReqServiceDto reqDto);

    void deleteReservation(ReservationDeleteReqDto reqDto);

    void modifyReservation(ReservationModifyReqDto reqDto);

    List<ReservationSearchResDto> searchReservationByMember(ReservationSearchReqDto reqDto);

    List<ReservationSearchResDto> searchReservationByCar(ReservationSearchReqDto reqDto);
}
