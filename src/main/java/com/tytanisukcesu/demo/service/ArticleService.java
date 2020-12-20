package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    private Article provideEntity(ArticleDto articleDto){
        Article article = new Article();
        article.setName(articleDto.getName());
        article.setCatalogueNumber(articleDto.getCatalogueNumber());
        article.setConsumable(articleDto.getConsumable());
        article.setAlternative(articleDto.getAlternative());
        article.setPurchasePrice(articleDto.getPurchasePrice());
        article.setSalePrice(articleDto.getSalePrice());
        article.setManufacturer(articleDto.getManufacturer());
        article.setYield(articleDto.getYield());
        article.setSetOfModels(articleDto.getSetOfModels());
        return article;
    }

    private ArticleDto provideDto(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setName(article.getName());
        articleDto.setCatalogueNumber(article.getCatalogueNumber());
        articleDto.setConsumable(article.getConsumable());
        articleDto.setAlternative(article.getAlternative());
        articleDto.setPurchasePrice(article.getPurchasePrice());
        articleDto.setSalePrice(article.getSalePrice());
        articleDto.setManufacturer(article.getManufacturer());
        articleDto.setYield(article.getYield());
        articleDto.setSetOfModels(article.getSetOfModels());
        return articleDto;
    }

    public ArticleDto save(ArticleDto articleDto){
        Article article = provideEntity(articleDto);
        articleRepository.save(article);
        return provideDto(article);
    }

    public boolean delete(Long id){
        Optional<Article> article = articleRepository.findById(id);
        if(article.isPresent()){
            articleRepository.delete(article.get());
            return true;
        }else{
            return false;
        }
    }

    public List<ArticleDto> findAll(){
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public ArticleDto update(Long id, ArticleDto articleDto){
        Article article = articleRepository.findById(id).get();
        Article articleUpdated = provideEntity(articleDto);
        article.setName(articleUpdated.getName());
        article.setCatalogueNumber(articleUpdated.getCatalogueNumber());
        article.setConsumable(articleUpdated.getConsumable());
        article.setAlternative(articleUpdated.getAlternative());
        article.setPurchasePrice(articleUpdated.getPurchasePrice());
        article.setSalePrice(articleUpdated.getSalePrice());
        article.setManufacturer(articleUpdated.getManufacturer());
        article.setYield(articleUpdated.getYield());
        article.setSetOfModels(articleUpdated.getSetOfModels());
        articleRepository.save(article);
        return provideDto(article);
    }

    public ArticleDto getById(Long id){
        Article article = articleRepository.findById(id).get();
        return provideDto(article);
    }

    public List<ArticleDto> getAllByName(String name){
        List<Article> articles = articleRepository.getAllByNameContains(name);
        return articles.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> getAllByCatalogNumber(String catalogNumber){
        List<Article> articles = articleRepository.getAllByCatalogueNumberContains(catalogNumber);
        return articles.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }







}
