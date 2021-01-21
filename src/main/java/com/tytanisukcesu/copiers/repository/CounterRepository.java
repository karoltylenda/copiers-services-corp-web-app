package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
}
