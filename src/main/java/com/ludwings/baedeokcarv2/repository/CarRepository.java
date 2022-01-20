package com.ludwings.baedeokcarv2.repository;

import com.ludwings.baedeokcarv2.domain.model.Car;
import com.ludwings.baedeokcarv2.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findCarByOwner(Pageable pageable, Member member);

    Page<Car> findCarByPriceLessThan(Pageable pageable, int price);

    Page<Car> findCarByNameContains(Pageable pageable, String name);
}
