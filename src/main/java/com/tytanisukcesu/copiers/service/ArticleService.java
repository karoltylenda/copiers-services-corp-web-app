package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.ArticleRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private static final Logger LOGGER = Logger.getLogger(ArticleService.class.getName());

    public List<Article> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"article"));
    }

    @Transactional
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
            Set<Model> modelsList = new HashSet<>();
            for (Model model: article.getModels()) {
                modelsList.add(modelService.save(model));
            }
            articleToSave.setModels(modelsList);
            Article articleSaved = articleRepository.save(articleToSave);
            LOGGER.info("A new row has been added.");
            return articleSaved;
        }
    }

    @Transactional
    public Article saveFromServlet(Article article) {
        Optional<Article> articleOptional = articleRepository.getArticleByCatalogueNumber(article.getCatalogueNumber());
        if (articleOptional.isPresent()) {
            return articleOptional.get();
        } else {
            Article articleToSave = new Article();
            articleToSave.setName(article.getName());
            articleToSave.setManufacturer(manufacturerService.findById(article.getManufacturer().getId()));
            articleToSave.setConsumable(article.isConsumable());
            articleToSave.setCatalogueNumber(article.getCatalogueNumber());
            Set<Model> modelsList = new HashSet<>();
            for (Model model: article.getModels()) {
                modelsList.add(modelService.findById(model.getId()));
            }
            articleToSave.setModels(modelsList);
            Article articleSaved = articleRepository.save(articleToSave);
            LOGGER.info("A new row has been added.");
            return articleSaved;
        }
    }

    public boolean delete(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            articleRepository.delete(articleOptional.get());
            LOGGER.info("Article for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Article for id " + id + " has not been deleted");
            return false;
        }
    }

    @Transactional
    public Article update(Long id, Article article) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article articleUpdated = articleOptional.get();
            articleUpdated.setName(article.getName());
            articleUpdated.setCatalogueNumber(article.getCatalogueNumber());
            articleUpdated.setConsumable(article.isConsumable());
            LOGGER.info(articleUpdated.getName() + " for id " + articleUpdated.getId() + " has been updated.");
            return articleUpdated;
        } else {
            LOGGER.warning("Article for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"article");        }
    }

}
