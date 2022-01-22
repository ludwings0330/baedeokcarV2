package com.ludwings.baedeokcarv2.domain.model;

import com.ludwings.baedeokcarv2.domain.BaseEntity;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarCreateReqDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarModDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends BaseEntity {


    @Id @GeneratedValue
    @Column(name="car_id")
    private Long id;

    private String name;
    private String model;
    private String introduction;
    private int distance;
    private int price;
    private String originFileName;
    private String savedFileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member owner;

    @OneToMany(mappedBy = "reservedCar")
    private List<Reservation> reservationList = new ArrayList<>();

    public Car(CarDto carDto, Member member) {
        member.addCar(this);

        name = carDto.getName();
        model = carDto.getModel();
        introduction = carDto.getIntroduction();
        distance = carDto.getDistance();
        price = carDto.getPrice();
        originFileName = carDto.getOriginFileName();
        owner = member;
    }

    public Car(CarCreateReqDto carDto, Member member) {
        member.addCar(this);

        name = carDto.getName();
        model = carDto.getModel();
        introduction = carDto.getIntroduction();
        distance = carDto.getDistance();
        price = carDto.getPrice();
        owner = member;

        originFileName = carDto.getFile().getOriginalFilename();
        savedFileName = UUID.randomUUID().toString().concat(originFileName.substring(originFileName.lastIndexOf(".")));
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    public void modifyCarInfo(CarDto carDto) {
        name = (carDto.getName() == null) ? name : carDto.getName();
        model = (carDto.getModel() == null) ? model : carDto.getModel();
        introduction = (carDto.getIntroduction() == null) ? introduction : carDto.getIntroduction();
        distance = (carDto.getDistance() == 0) ? distance : carDto.getDistance();
        price = (carDto.getPrice() == 0) ? price : carDto.getPrice();
        originFileName = (carDto.getOriginFileName() == null) ? originFileName : carDto.getOriginFileName();
    }
}
