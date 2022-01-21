package com.ludwings.baedeokcarv2.domain.dto.Car;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CarModReqDto {
    String loginId;
    String name;
    String model;
    String introduction;

    int distance;
    int price;

    MultipartFile file;
}
