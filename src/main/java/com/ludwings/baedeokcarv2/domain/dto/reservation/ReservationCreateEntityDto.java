package com.ludwings.baedeokcarv2.domain.dto.reservation;

import com.ludwings.baedeokcarv2.domain.model.Car;
import com.ludwings.baedeokcarv2.domain.model.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ReservationCreateEntityDto {
    Member member;
    Car car;
    String title;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    public ReservationCreateEntityDto(ReservationCreateReqServiceDto reservationCreateReqServiceDto) {

    }

    public ReservationCreateEntityDto(ReservationCreateReqServiceDto reqDto, Member findMember, Car findCar) {
        member = findMember;
        car = findCar;

        title = reqDto.getTitle();
        content = reqDto.getContent();
        startDate = reqDto.getStartDate();
        endDate = reqDto.getEndDate();
    }
}
