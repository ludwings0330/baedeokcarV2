package com.ludwings.baedeokcarv2.repository;

import com.ludwings.baedeokcarv2.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
