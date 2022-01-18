package com.ludwings.baedeokcarv2.domain.dto.Car;

import com.ludwings.baedeokcarv2.domain.model.Car;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CarCreateReqDto {
    String loginId;
    String name;
    String model;
    String introduction;

    int distance;
    int price;

    MultipartFile file;

    public Car toEntity() {
        String originFileName = file.getOriginalFilename();
        String savedFileName = UUID.randomUUID().toString().concat(originFileName.substring(originFileName.lastIndexOf('.')));

        return Car.builder()
                .name(name)
                .model(model)
                .introduction(introduction)
                .distance(distance)
                .price(price)
                .build();
    }
}
