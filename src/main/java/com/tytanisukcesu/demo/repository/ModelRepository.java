package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.entity.PrintingFormat;
import org.dom4j.rule.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {



    List<Model> getAllByNameContains(String name);

    List<Model> getAllByPrintsInColor(boolean printsInColor);

    List<Model> getAllByPrintingSpeedGreaterThanEqual(Integer speed);

    List<Model> getAllByManufacturerName(String manufacturer);

    List<Model> getAllByPrintingFormatEquals(PrintingFormat printingFormat);

    List<Model> getAllByPrintsInColorNot(boolean printsInColor);

    List<Model> getAllByManufacturerNameContainsAndNameContainsAndPrintsInColor(String manufacturerName,String modelName,boolean printsInColor);

}
