package com.ludwings.baedeokcarv2.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {

    @Id @GeneratedValue
    @Column(name="reservation_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservationList")
    Car reservedCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservationList")
    Member reservedMember;

    LocalDate start;
    LocalDate end;

    public Reservation createReservation(Member member, Car car, LocalDate start, LocalDate end) {
        return Reservation.builder()
                .reservedMember(member)
                .reservedCar(car)
                .start(start)
                .end(end)
                .build();
    }

    public void modifyReservation() {
        // Reservation Dto 사용
    }
}
