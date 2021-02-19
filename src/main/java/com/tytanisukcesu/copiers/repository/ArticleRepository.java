package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> getArticleByCatalogueNumber(String catalogueNumber);


}
