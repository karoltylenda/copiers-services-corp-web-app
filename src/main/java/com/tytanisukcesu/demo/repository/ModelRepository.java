package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> getAllByNameContains(String name);

    Optional<Model> getBySerialNumber(String serialNumber);


}
