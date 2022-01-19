package com.ludwings.baedeokcarv2.domain.dto.Car;

import com.ludwings.baedeokcarv2.domain.model.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarBoardListResDto {
    Long id;
    String name;
    String loginId;
    String model;

    int distance;
    int price;

    String originFileName;
    String savedFileName;

    public CarBoardListResDto(Car entity) {
        id = entity.getId();
        name = entity.getName();
        loginId = entity.getOwner().getLoginId();
        model = entity.getModel();

        distance = entity.getDistance();
        price = entity.getPrice();

        originFileName = entity.getOriginFileName();
        savedFileName = entity.getSavedFileName();
    }
}
