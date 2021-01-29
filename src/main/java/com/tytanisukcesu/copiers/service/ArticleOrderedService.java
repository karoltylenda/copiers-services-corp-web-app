package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.repository.ArticleOrderedRepository;
import liquibase.pro.packaged.B;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleOrderedService {

    private final ArticleOrderedRepository articleOrderedRepository;
    private final ArticleService articleService;

    public ArticleOrdered save(ArticleOrdered articleOrdered){
        ArticleOrdered articleOrderedToSave = new ArticleOrdered();
        articleOrderedToSave.setTotalPrice(articleOrdered.getPrice().multiply(BigDecimal.valueOf(articleOrdered.getQuantity())));
        articleOrderedToSave.setArticle(articleService.save(articleOrdered.getArticle()));
        articleOrderedToSave.setQuantity(articleOrdered.getQuantity());
        articleOrderedToSave.setPrice(articleOrdered.getPrice());
        return articleOrderedRepository.save(articleOrderedToSave);
    }

    public ArticleOrdered findById(Long id){
        Optional<ArticleOrdered> articleOrderedOptional = articleOrderedRepository.findById(id);
        return articleOrderedOptional.orElse(new ArticleOrdered());
    }

    public boolean delete(Long id) {
        Optional<ArticleOrdered> articleOrdered = articleOrderedRepository.findById(id);
        if (articleOrdered.isPresent()) {
            articleOrderedRepository.delete(articleOrdered.get());
            return true;
        } else {
            return false;
        }
    }

    public List<ArticleOrdered> findAll(){
        List<ArticleOrdered> articleOrderedList = articleOrderedRepository.findAll();
        return articleOrderedList;
    }








}
