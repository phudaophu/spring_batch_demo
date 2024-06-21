package com.example.spring_batch_demo.repository;

import com.example.spring_batch_demo.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightPrimaryRepository extends JpaRepository<FlightEntity,Integer> {
}
