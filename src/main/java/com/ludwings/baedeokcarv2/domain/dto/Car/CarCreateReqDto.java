package com.ludwings.baedeokcarv2.domain.dto.Car;

import lombok.Data;

@Data
public class CarCreateReqDto {
    String loginId;
    String name;
    String model;
    String introduction;

    int distance;
    int price;

    String originFileName;
}
