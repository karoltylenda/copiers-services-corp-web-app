package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

//    List<Article> getAllByNameContainsAndCatalogueNumberContainsAndIsConsumableAndIsAlternativeAndManufacturerNameContains(String name, String catalogueNumber, Boolean isConsumable, Boolean isAlternative, String manufacturerName);
//
//    List<Article> getAllByNameContainsAndCatalogueNumberContainsAndManufacturerNameContains(String name, String catalogueNumber, String manufacturerName);

}
