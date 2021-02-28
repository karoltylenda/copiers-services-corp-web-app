package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.repository.ArticleOrderedRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArticleOrderedService {

    private final ArticleOrderedRepository articleOrderedRepository;
    private final ArticleService articleService;
    private static final Logger LOGGER = Logger.getLogger(ArticleOrderedService.class.getName());

    @Transactional
    public ArticleOrdered save(ArticleOrdered articleOrdered){
        ArticleOrdered articleOrderedToSave = new ArticleOrdered();
        articleOrderedToSave.setTotalPrice(articleOrdered.getPrice().multiply(BigDecimal.valueOf(articleOrdered.getQuantity())));
        articleOrderedToSave.setArticle(articleService.save(articleOrdered.getArticle()));
        articleOrderedToSave.setQuantity(articleOrdered.getQuantity());
        articleOrderedToSave.setPrice(articleOrdered.getPrice());
        LOGGER.info("A new row has been added.");
        return articleOrderedRepository.save(articleOrderedToSave);
    }

    public ArticleOrdered findById(Long id){
        return articleOrderedRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"article ordered"));
    }

    public boolean delete(Long id) {
        Optional<ArticleOrdered> articleOrdered = articleOrderedRepository.findById(id);
        if (articleOrdered.isPresent()) {
            articleOrderedRepository.delete(articleOrdered.get());
            LOGGER.info("Article ordered for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Article ordered for id " + id + " has not been deleted");
            return false;
        }
    }

    public List<ArticleOrdered> findAll(){
        List<ArticleOrdered> articleOrderedList = articleOrderedRepository.findAll();
        return articleOrderedList;
    }

    @Transactional
    public ArticleOrdered update(Long id,ArticleOrdered articleOrdered){
        Optional<ArticleOrdered> articleOrderedOptional = articleOrderedRepository.findById(id);
        if(articleOrderedOptional.isPresent()){
            ArticleOrdered articleOrderedUpdated = articleOrderedOptional.get();
            articleOrderedUpdated.setArticle(articleOrdered.getArticle());
            articleOrderedUpdated.setPrice(articleOrdered.getPrice());
            articleOrderedUpdated.setQuantity(articleOrdered.getQuantity());
            articleOrderedUpdated.setServiceOrder(articleOrdered.getServiceOrder());
            articleOrderedUpdated.setTotalPrice(articleOrderedUpdated.getPrice().multiply(BigDecimal.valueOf(articleOrderedUpdated.getQuantity())));
            LOGGER.info("Article ordered for id " + articleOrderedUpdated.getId() + " has been updated.");
            return articleOrderedUpdated;
        }else{
            LOGGER.warning("Article ordered for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"article ordered");
        }
    }
}
