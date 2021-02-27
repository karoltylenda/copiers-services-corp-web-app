package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.repository.ArticleOrderedRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public ArticleOrdered save(ArticleOrdered articleOrdered){
        ArticleOrdered articleOrderedToSave = new ArticleOrdered();
        articleOrderedToSave.setTotalPrice(articleOrdered.getPrice().multiply(BigDecimal.valueOf(articleOrdered.getQuantity())));
        articleOrderedToSave.setArticle(articleService.save(articleOrdered.getArticle()));
        articleOrderedToSave.setQuantity(articleOrdered.getQuantity());
        articleOrderedToSave.setPrice(articleOrdered.getPrice());
        return articleOrderedRepository.save(articleOrderedToSave);
    }

    public ArticleOrdered findById(Long id){
        return articleOrderedRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"article ordered"));
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
