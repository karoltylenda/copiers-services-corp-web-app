package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> getOptionalByName(String name);

}
