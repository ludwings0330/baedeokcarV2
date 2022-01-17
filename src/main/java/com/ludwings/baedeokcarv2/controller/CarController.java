package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.Car.CarDto;
import com.ludwings.baedeokcarv2.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * 차량 등록
     * @param carDto
     * @return
     */
    @PostMapping("/car")
    public String registerCar(@ModelAttribute CarDto carDto, Model model) {
        CarDto createdCar = carService.registerCar(carDto);

        model.addAttribute("car", createdCar);
        return "/";
    }

    /**
     * 차량 조회 (단건)
     * @param carId
     */
    @GetMapping("/car/{carId}")
    public void findCar(@PathVariable Long carId, Model model) {
        CarDto findCarDto = carService.findCarByCarId(carId);
        model.addAttribute("car", findCarDto);
    }

    /**
     * 차량 삭제 (단건)
     * @param carId
     */
    @DeleteMapping("/car/{carId}")
    public String deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return "redirect:/";
    }

    /**
     * 차량 정보 수정
     * @param carId
     * @param carDto
     */
    @PatchMapping("/car/{carId}")
    public void modifyCar(@PathVariable Long carId, @ModelAttribute CarDto carDto) {
        carDto.setId(carId);
        carService.modifyCar(carDto);
    }


    /**
     * 페이징 이용 차량 전체 조회
     */
    @GetMapping("/car")
    public String findAllCar(Model model,
                           @PageableDefault(size=18, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<CarDto> cars = carService.findAllCar(pageable);

        model.addAttribute("cars", cars.getContent());
        model.addAttribute("page", cars);

        return "/";
    }

    @GetMapping("/car/member/{loginId}")
    public String findAllCarOwnedByMember(@PathVariable String loginId, Model model,
                                          @PageableDefault(size=18, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<CarDto> cars = carService.findCarByMember(pageable, loginId);

        model.addAttribute("cars", cars.getContent());
        model.addAttribute("page", cars);

        return "/";
    }
}
