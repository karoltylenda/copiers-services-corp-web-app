package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> getAllByNameContains(String name);

    List<Article> getAllByCatalogueNumberContains(String catalogNumber);

    //TODO
    //wyszukiwanie po cenie, po zamiennikach, czy ekspl, po manu itd..




}
