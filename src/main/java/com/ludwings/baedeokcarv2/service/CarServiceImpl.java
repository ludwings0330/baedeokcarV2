package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.Car.CarCreateReqDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarDto;
import com.ludwings.baedeokcarv2.domain.model.Car;
import com.ludwings.baedeokcarv2.domain.model.Member;
import com.ludwings.baedeokcarv2.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MemberService memberService;

    private final FileUploadService fileUploadService;

    @Override
    @Transactional
    public CarDto saveCar(CarDto carDto) {
        Member findMember = memberService.findMemberByLoginId(carDto.getLoginId());
        Car car = new Car(carDto, findMember);

        carRepository.save(car);

        return new CarDto(car);
    }

    @Override
    @Transactional
    public void saveCar(CarCreateReqDto reqDto) {
        Member findMember = memberService.findMemberByLoginId(reqDto.getLoginId());
        Car car = new Car(reqDto, findMember);

        carRepository.save(car);
        fileUploadService.saveImageFileToServer(reqDto.getFile(), car.getOriginFileName(), car.getSavedFileName());
    }

    @Override
    @Transactional
    public CarDto modifyCar(CarDto carDto) {
        Optional<Car> findCar = carRepository.findById(carDto.getId());
        if (findCar.isPresent()) {
            Car car = findCar.get();
            car.modifyCarInfo(carDto);

            return new CarDto(car);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteCar(Long carId) {
        Optional<Car> findCar = carRepository.findById(carId);
        if (findCar.isPresent()) {
            carRepository.delete(findCar.get());
        } else {
            // throw exception..
        }
    }

    @Override
    public CarDto findCarByCarId(Long carId) {
        Optional<Car> findCar = carRepository.findById(carId);
        if (findCar.isPresent()) {
            return new CarDto(findCar.get());
        } else {
            return null;
        }
    }

    @Override
    public Page<CarDto> findAllCar(Pageable pageable) {
        Page<Car> findCar = carRepository.findAll(pageable);
        Page<CarDto> all = findCar.map(c -> new CarDto(c));
        return all;
    }

    @Override
    public Page<CarDto> findCarByMember(Pageable pageable, String loginId) {
        Member findMember = memberService.findMemberByLoginId(loginId);
        Page<Car> carList = carRepository.findCarByOwner(pageable, findMember);
        Page<CarDto> all = carList.map(c -> new CarDto(c));

        return all;
    }
}
