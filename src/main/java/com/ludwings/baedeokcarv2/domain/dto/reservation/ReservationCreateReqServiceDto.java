package com.ludwings.baedeokcarv2.domain.dto.reservation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationCreateReqServiceDto {
    String loginId;
    Long carId;
    String title;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    public ReservationCreateReqServiceDto(ReservationCreateReqDto reqDto) {
        this.loginId = reqDto.getLoginId();
        this.carId = reqDto.getCarId();
        this.title = reqDto.getTitle();
        this.content = reqDto.getContent();
        this.startDate = reqDto.getStartDate();
        this.endDate = reqDto.getEndDate();
    }
}
