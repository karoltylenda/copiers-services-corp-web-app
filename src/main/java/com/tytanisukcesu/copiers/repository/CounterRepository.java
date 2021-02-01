package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {


    Optional<Counter> getFirstByDevice_SerialNumberOrderByCounterDateDesc(String serialNumber);




}
