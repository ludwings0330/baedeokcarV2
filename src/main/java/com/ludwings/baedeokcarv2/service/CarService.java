package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.Car.*;
import com.ludwings.baedeokcarv2.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto saveCar(CarDto carDto);

    void saveCar(CarCreateReqDto reqDto);

    CarDto modifyCar(CarDto carDto);

    Car findCarEntityByCarId(Long carId);

    void deleteCar(Long carId);

    CarReadResDto findCarByCarId(Long carId);

    Page<CarBoardListResDto> findAllCar(Pageable pageable);

    Page<CarBoardListResDto> findAllCar(CarBoardListReqDto reqDto);

    Page<CarDto> findCarByMember(Pageable pageable, String loginId);
}
