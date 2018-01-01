package com.carSharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carSharing.model.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {

}
