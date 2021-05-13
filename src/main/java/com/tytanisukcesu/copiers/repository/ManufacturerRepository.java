package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> getManufacturerByName(String manucaturerName);
    List<Manufacturer> getAllByName(String name);


}
