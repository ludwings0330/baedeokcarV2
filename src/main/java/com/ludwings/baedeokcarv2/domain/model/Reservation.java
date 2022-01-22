package com.ludwings.baedeokcarv2.domain.model;

import com.ludwings.baedeokcarv2.domain.BaseEntity;
import com.ludwings.baedeokcarv2.domain.dto.reservation.ReservationCreateEntityDto;
import com.ludwings.baedeokcarv2.domain.dto.reservation.ReservationModifyReqDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="reservation_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car_id")
    private Car reservedCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member reservedMember;

    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(ReservationCreateEntityDto reqDto) {
        title = reqDto.getTitle();
        content = reqDto.getContent();
        reservedMember = reqDto.getMember();
        reservedCar = reqDto.getCar();
        startDate = reqDto.getStartDate();
        endDate = reqDto.getEndDate();

        reqDto.getMember().addReservation(this);
        reqDto.getCar().addReservation(this);
    }

    public void modifyReservation(ReservationModifyReqDto reqDto) {
        this.title = reqDto.getTitle();
        this.content = reqDto.getContent();
        this.startDate = reqDto.getStartDate();
        this.endDate = reqDto.getEndDate();
    }
}
