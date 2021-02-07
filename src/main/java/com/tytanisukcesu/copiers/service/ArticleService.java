package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ManufacturerService manufacturerService;
    private static final Logger LOGGER = Logger.getLogger(ArticleService.class.getName());

    public List<Article> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article findById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        return articleOptional.orElse(new Article());
    }

    public Article save(Article article) {
        Optional<Article> articleOptional = articleRepository.getArticleByCatalogueNumber(article.getCatalogueNumber());
        if (articleOptional.isPresent()) {
            return articleOptional.get();
        } else {
            Article articleToSave = new Article();
            articleToSave.setName(article.getName());
            articleToSave.setManufacturer(manufacturerService.save(article.getManufacturer()));
            articleToSave.setConsumable(article.isConsumable());
            articleToSave.setCatalogueNumber(article.getCatalogueNumber());
            Article articleSaved = articleRepository.save(articleToSave);
            return articleSaved;
        }
    }

    public boolean delete(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            articleRepository.delete(articleOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Article update(Long id, Article article) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article articleToUpdate = articleOptional.get();
            articleToUpdate.setManufacturer(article.getManufacturer());
            articleToUpdate.setModels(article.getModels());
            articleToUpdate.setName(article.getName());
            articleToUpdate.setCatalogueNumber(article.getCatalogueNumber());
            articleToUpdate.setConsumable(article.isConsumable());
            return articleToUpdate;
        } else {
            return new Article();
        }
    }

}
