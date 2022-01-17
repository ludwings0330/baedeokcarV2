package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.Car.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto registerCar(CarDto carDto);

    CarDto modifyCar(CarDto carDto);

    void deleteCar(Long carId);

    CarDto findCarByCarId(Long carId);

    Page<CarDto> findAllCar(Pageable pageable);

    Page<CarDto> findCarByMember(Pageable pageable, String loginId);
}
