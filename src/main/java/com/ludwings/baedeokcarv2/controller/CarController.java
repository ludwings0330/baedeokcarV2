package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.Car.CarCreateReqDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarDto;
import com.ludwings.baedeokcarv2.domain.dto.Car.CarReadResDto;
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

    @PostMapping("/car")
    public String saveCar(@ModelAttribute CarCreateReqDto reqDto, Model model) {
        carService.saveCar(reqDto);

        return "redirect:/";
    }

    /**
     * 차량 조회 (단건)
     * @param carId
     */
    @GetMapping("/car/{carId}")
    public String findCar(@PathVariable Long carId, Model model) {
        CarReadResDto findCarDto = carService.findCarByCarId(carId);
        model.addAttribute("car", findCarDto);

        return "car/car-form";
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
    public String modifyCar(@PathVariable Long carId, @ModelAttribute CarDto carDto) {
        carDto.setId(carId);
        carService.modifyCar(carDto);

        return "redirect:/";
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

        return "car/car-board";

    }

    /**
     * 해당 멤버가 보유한 차량 조회?
     * @param loginId
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/car/member/{loginId}")
    public String findAllCarOwnedByMember(@PathVariable String loginId, Model model,
                                          @PageableDefault(size=18, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<CarDto> cars = carService.findCarByMember(pageable, loginId);

        model.addAttribute("cars", cars.getContent());
        model.addAttribute("page", cars);

        return "redirect:/";
    }
}
