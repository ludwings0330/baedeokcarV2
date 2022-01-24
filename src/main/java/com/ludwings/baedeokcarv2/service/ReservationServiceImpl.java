package com.ludwings.baedeokcarv2.service;

import com.ludwings.baedeokcarv2.domain.dto.reservation.*;
import com.ludwings.baedeokcarv2.domain.model.Car;
import com.ludwings.baedeokcarv2.domain.model.Member;
import com.ludwings.baedeokcarv2.domain.model.Reservation;
import com.ludwings.baedeokcarv2.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberService memberService;
    private final CarService carService;

    @Override
    @Transactional
    public void createReservation(ReservationCreateReqServiceDto reqDto) {
        Member findMember = memberService.findMemberByLoginId(reqDto.getLoginId());
        Car findCar = carService.findCarEntityByCarId(reqDto.getCarId());

        ReservationCreateEntityDto entityDto = new ReservationCreateEntityDto(reqDto, findMember, findCar);
        Reservation reservation = new Reservation(entityDto);

        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public void deleteReservation(ReservationDeleteReqDto reqDto) {
        if (reservationRepository.existsById(reqDto.getId())) {
            reservationRepository.deleteById(reqDto.getId());
        }
    }

    @Override
    @Transactional
    public void modifyReservation(ReservationModifyReqDto reqDto) {
        Optional<Reservation> findReservation = reservationRepository.findById(reqDto.getId());

        if (findReservation.isEmpty()) {
            return;
        }

        Reservation reservation = findReservation.get();
        reservation.modifyReservation(reqDto);
    }

    @Override
    public List<ReservationSearchResDto> searchReservationByMember(ReservationSearchReqDto reqDto) {
        List<Reservation> reservationList = reservationRepository.findAllReservationByLoginId(reqDto.getLoginId());

        return reservationList.stream().map(r -> new ReservationSearchResDto(r)).collect(Collectors.toList());
    }

    @Override
    public List<ReservationSearchResDto> searchReservationByCar(ReservationSearchReqDto reqDto) {
        List<Reservation> reservationList = reservationRepository.findAllReservationByCarId(reqDto.getCarId());

        return reservationList.stream().map(r -> new ReservationSearchResDto(r)).collect(Collectors.toList());
    }
}
