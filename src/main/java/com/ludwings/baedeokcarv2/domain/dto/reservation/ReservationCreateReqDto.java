package com.ludwings.baedeokcarv2.domain.dto.reservation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ReservationCreateReqDto {
    String loginId;
    Long carId;
    String title;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    public ReservationCreateReqServiceDto toServiceDto() {
        return new ReservationCreateReqServiceDto(this);
    }
}
