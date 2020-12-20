package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {


    List<Manufacturer> getAllByNameContains(String name);




}
