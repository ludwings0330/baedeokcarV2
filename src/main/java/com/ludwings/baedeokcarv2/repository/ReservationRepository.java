package com.ludwings.baedeokcarv2.repository;

import com.ludwings.baedeokcarv2.domain.model.Car;
import com.ludwings.baedeokcarv2.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.reservedCar.id=:carId")
    List<Reservation> findAllReservationByCarId(@Param("carId") Long carId);

    @Query("select r from Reservation r where r.reservedMember.loginId=:loginId")
    List<Reservation> findAllReservationByLoginId(@Param("loginId") String loginId);
}
