package com.ludwings.baedeokcarv2.domain.dto.Car;

import com.ludwings.baedeokcarv2.domain.model.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarReadResDto {
    String name;
    String owner;
    String model;
    String introduction;

    int distance;
    int price;

    String originFileName;
    String savedFileName;

    public CarReadResDto(Car entity) {
        name = entity.getName();
        owner = entity.getOwner().getName();
        model = entity.getModel();
        introduction = entity.getIntroduction();

        distance = entity.getDistance();
        price = entity.getPrice();

        originFileName = entity.getOriginFileName();
        savedFileName = entity.getSavedFileName();
    }
}
