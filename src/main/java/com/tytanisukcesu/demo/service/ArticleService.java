package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Article save(Article article) {
        Optional<Article> articleOptional = articleRepository.getOptionalByName(article.getName());
        if (articleOptional.isPresent()) {
            return articleOptional.get();
        } else {
            return articleRepository.save(article);
        }
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(new Article());
    }

    public boolean deleteById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return true;
        } else {
            return false;
        }
    }

    public Article update(Long id, Article article) {
        Article articleToUpdate = articleRepository.findById(id).orElse(new Article());
        articleToUpdate.setYield(article.getYield());
        articleToUpdate.setManufacturer(article.getManufacturer());
        articleToUpdate.setSalePrice(article.getSalePrice());
        articleToUpdate.setPurchasePrice(article.getPurchasePrice());
        articleToUpdate.setCatalogueNumber(article.getCatalogueNumber());
        articleToUpdate.setName(article.getName());
        articleToUpdate.setAlternative(article.isAlternative());
        articleToUpdate.setConsumable(article.isConsumable());
        article.setModels(article.getModels());
        return articleToUpdate;
    }


}
