package com.ludwings.baedeokcarv2.domain.dto.Car;

import com.ludwings.baedeokcarv2.domain.model.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
    String loginId;
    String name;
    String model;
    String introduction;

    Long id;
    int distance;
    int price;

    String originFileName;

    public CarDto(Car entity) {
        loginId = entity.getOwner().getLoginId();
        name = entity.getName();
        model = entity.getModel();
        introduction = entity.getIntroduction();
        distance = entity.getDistance();
        price = entity.getPrice();

        originFileName = entity.getOriginFileName();
    }
}
