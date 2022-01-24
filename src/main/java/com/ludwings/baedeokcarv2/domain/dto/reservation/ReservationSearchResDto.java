package com.ludwings.baedeokcarv2.domain.dto.reservation;

import com.ludwings.baedeokcarv2.domain.model.Reservation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ReservationSearchResDto {
    Long reservationId;
    String loginId;
    String carName;
    Long carId;
    String title;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    public ReservationSearchResDto(Reservation r) {
        reservationId = r.getId();
        loginId = r.getReservedMember().getLoginId();
        carName = r.getReservedCar().getName();
        carId = r.getReservedCar().getId();
        title = r.getTitle();
        content = r.getContent();
        startDate = r.getStartDate();
        endDate = r.getEndDate();
    }
}
