package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.entity.PrintingFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> getAllByNameContains(String name);

    List<Model> getAllByPrintsInColor(Boolean printsInColor);

    List<Model> getAllByPrintingSpeedGreaterThanEqual(Integer speed);

    List<Model> getAllByManufacturerName(String manufacturer);

    List<Model> getAllByPrintingFormatEquals(PrintingFormat printingFormat);

    List<Model> getAllByPrintsInColorNot(Boolean printsInColor);



}
