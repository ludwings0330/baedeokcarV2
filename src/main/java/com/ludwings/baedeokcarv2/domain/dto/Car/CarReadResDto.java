package com.ludwings.baedeokcarv2.domain.dto.Car;

import com.ludwings.baedeokcarv2.domain.model.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarReadResDto {
    Long id;
    String name;
    String owner;
    String model;
    String introduction;

    int distance;
    int price;

    String originFileName;
    String savedFileName;

    public CarReadResDto(Car entity) {
        id = entity.getId();
        name = entity.getName();
        owner = entity.getOwner().getLoginId();
        model = entity.getModel();
        introduction = entity.getIntroduction();

        distance = entity.getDistance();
        price = entity.getPrice();

        originFileName = entity.getOriginFileName();
        savedFileName = entity.getSavedFileName();
    }
}
