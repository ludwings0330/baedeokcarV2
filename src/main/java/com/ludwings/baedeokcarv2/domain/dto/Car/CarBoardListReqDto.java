package com.ludwings.baedeokcarv2.domain.dto.Car;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class CarBoardListReqDto {
    String type;
    String keyword;
    Pageable pageable;

    public CarBoardListReqDto(String type, String keyword, Pageable pageable) {
        this.type = type;
        this.keyword = keyword;
        this.pageable = pageable;
    }
}
