package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> getAllByNameContains(String name);

    List<Model> getAllByPrintsInColor();

    List<Model> getAllByPrintingSpeedGreaterThanEqual(Integer speed);

    List<Model> getAllByManufacturer(String manufacturer);

    List<Model> getAllByPrintingFormatEquals(String printingFormat);

    List<Model> getAllByPrintsInColorNot();



}
