package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.Car.*;
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
    public Car findCarEntityByCarId(Long carId) {
        Optional<Car> findCar = carRepository.findById(carId);

        if (findCar.isPresent()) {
            return findCar.get();
        }

        return null;
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
    public CarReadResDto findCarByCarId(Long carId) {
        Optional<Car> findCar = carRepository.findById(carId);
        if (findCar.isPresent()) {
            return new CarReadResDto(findCar.get());
        } else {
            return null;
        }
    }

    @Override
    public Page<CarBoardListResDto> findAllCar(Pageable pageable) {
        Page<Car> findCar = carRepository.findAll(pageable);
        Page<CarBoardListResDto> all = findCar.map(c -> new CarBoardListResDto(c));

        return all;
    }

    @Override
    public Page<CarBoardListResDto> findAllCar(CarBoardListReqDto reqDto) {
        // loginId, name, price
        Page<Car> findCar;

        switch (reqDto.getType()) {
            case "loginId":
                findCar = findCarByLoginId(reqDto.getPageable(), reqDto.getKeyword());
                break;
            case "name":
                findCar = findCarByCarName(reqDto.getPageable(), reqDto.getKeyword());
                break;
            case "price":
                findCar = findCarByPrice(reqDto.getPageable(), Integer.parseInt(reqDto.getKeyword()));
                break;
            default:
                findCar = carRepository.findAll(reqDto.getPageable());
        }

        Page<CarBoardListResDto> resDto = findCar.map(c -> new CarBoardListResDto(c));
        return resDto;
    }

    @Override
    public Page<CarDto> findCarByMember(Pageable pageable, String loginId) {
        Member findMember = memberService.findMemberByLoginId(loginId);
        Page<Car> carList = carRepository.findCarByOwner(pageable, findMember);
        Page<CarDto> all = carList.map(c -> new CarDto(c));

        return all;
    }

    public Page<Car> findCarByLoginId(Pageable pageable, String loginId) {
        Member findMember = memberService.findMemberByLoginId(loginId);
        return carRepository.findCarByOwner(pageable, findMember);
    }

    public Page<Car> findCarByCarName(Pageable pageable, String Name) {
        return carRepository.findCarByNameContains(pageable, Name);
    }

    public Page<Car> findCarByPrice(Pageable pageable, int price) {
        return carRepository.findCarByPriceLessThan(pageable, price);
    }
}
