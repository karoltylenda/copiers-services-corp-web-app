package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.entity.PrintingFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> getAllByNameContains(String name);

    List<Model> getAllByPrintsInColor(boolean printsInColor);

    List<Model> getAllByPrintingSpeedGreaterThanEqual(Integer speed);

    List<Model> getAllByManufacturerName(String manufacturer);

    List<Model> getAllByPrintingFormatEquals(PrintingFormat printingFormat);

    List<Model> getAllByPrintsInColorNot(boolean printsInColor);

    List<Model> getAllByManufacturerNameContainsAndNameContainsAndPrintsInColor(String manufacturerName,String modelName,boolean printsInColor);

    List<Model> getAllByManufacturerNameContainsAndNameContains(String manufacturerName, String modelName);

    List<Model> getAllByNameAndManufacturerName(String modelName,String manufacturerName);


    Model getByNameContains(String name);


}
