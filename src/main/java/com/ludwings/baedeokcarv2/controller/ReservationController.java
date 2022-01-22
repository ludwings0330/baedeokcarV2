package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.reservation.*;
import com.ludwings.baedeokcarv2.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @DeleteMapping("/reservation/{id}")
    public @ResponseBody String deleteReservation(@ModelAttribute ReservationDeleteReqDto reqDto) {

        reservationService.deleteReservation(reqDto);

        return "DELETE";
    }

    @PostMapping("/reservation")
    public @ResponseBody String saveReservation(@ModelAttribute ReservationCreateReqDto reqDto) {

        reservationService.createReservation(reqDto.toServiceDto());

        return "SAVE";
    }

    @GetMapping("/reservation/car/{carId}")
    public @ResponseBody List<ReservationSearchResDto> findReservationsByCarId(@ModelAttribute ReservationSearchReqDto reqDto) {

        List<ReservationSearchResDto> reservationList = reservationService.searchReservationByCar(reqDto);

        return reservationList;
    }

    @GetMapping("/reservation/member/{loginId}")
    public @ResponseBody List<ReservationSearchResDto> findReservationsByLoginId(@ModelAttribute ReservationSearchReqDto reqDto) {

        List<ReservationSearchResDto> reservationList = reservationService.searchReservationByMember(reqDto);

        return reservationList;
    }

    @PatchMapping("/reservation/{id}")
    public @ResponseBody
    String modifyReservationById(@ModelAttribute ReservationModifyReqDto reqDto) {

        reservationService.modifyReservation(reqDto);

        return "UPDATE";
    }

}
