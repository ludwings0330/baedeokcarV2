package com.ludwings.baedeokcarv2.controller;

import com.ludwings.baedeokcarv2.domain.dto.Car.*;
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
    public String findAllCar(
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size=6, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        CarBoardListReqDto reqDto = new CarBoardListReqDto(type, keyword, pageable);
        Page<CarBoardListResDto> cars = carService.findAllCar(reqDto);

        model.addAttribute("cars", cars.getContent());
        model.addAttribute("page", cars);
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "car/car-board";
    }
}
