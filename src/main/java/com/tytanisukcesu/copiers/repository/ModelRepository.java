package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> getModelByName(String modelName);
    Optional<Model> getModelByNameAndManufacturerName(String modelName, String manufacturerName);

}
