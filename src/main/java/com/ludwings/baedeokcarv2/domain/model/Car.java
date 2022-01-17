package com.ludwings.baedeokcarv2.domain.model;

import com.ludwings.baedeokcarv2.domain.dto.Car.CarCreateReqDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarModDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id @GeneratedValue
    @Column(name="car_id")
    private Long id;

    String name;
    String model;
    String introduction;
    int distance;
    int price;
    String originFileName;
    String savedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carList")
    Member owner;

    @OneToMany(mappedBy = "reservedCar")
    List<Reservation> reservationList = new ArrayList<>();

    public Car createCar(CarCreateReqDto carDto, Member member) {
        member.addCar(this);

        return Car.builder()
                .owner(member)
                .build();
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    public void modifyCarInfo(CarModDto carDto) {

    }

}
