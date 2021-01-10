package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    List<Manufacturer> getAllByNameContains(String name);

    Manufacturer getByName(String name);

    //fixme
    Optional<Manufacturer> getOptionalByName(String name);



}
