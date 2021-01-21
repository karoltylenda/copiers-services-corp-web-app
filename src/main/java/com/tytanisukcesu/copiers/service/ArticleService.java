package com.tytanisukcesu.copiers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

//    private final ArticleRepository articleRepository;
//
//    private Article provideEntity(ArticleDto articleDto){
//        Article article = new Article();
//        article.setId(articleDto.getId());
//        article.setName(articleDto.getName());
//        article.setCatalogueNumber(articleDto.getCatalogueNumber());
//        article.setConsumable(articleDto.isConsumable());
//        article.setAlternative(articleDto.isAlternative());
//        article.setPurchasePrice(articleDto.getPurchasePrice());
//        article.setSalePrice(articleDto.getSalePrice());
//        article.setManufacturer(articleDto.getManufacturer());
//        article.setYield(articleDto.getYield());
//        article.setModels(articleDto.getModels());
//        return article;
//    }
//
//    private ArticleDto provideDto(Article article){
//        ArticleDto articleDto = new ArticleDto();
//        articleDto.setId(article.getId());
//        articleDto.setName(article.getName());
//        articleDto.setCatalogueNumber(article.getCatalogueNumber());
//        articleDto.setConsumable(article.isConsumable());
//        articleDto.setAlternative(article.isAlternative());
//        articleDto.setPurchasePrice(article.getPurchasePrice());
//        articleDto.setSalePrice(article.getSalePrice());
//        articleDto.setManufacturer(article.getManufacturer());
//        articleDto.setYield(article.getYield());
//        articleDto.setModels(article.getModels());
//        return articleDto;
//    }
//
//    public ArticleDto save(ArticleDto articleDto){
//        Article article = provideEntity(articleDto);
//        articleRepository.save(article);
//        return provideDto(article);
//    }
//
//    public boolean delete(Long id){
//        Optional<Article> article = articleRepository.findById(id);
//        if(article.isPresent()){
//            articleRepository.delete(article.get());
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    public List<ArticleDto> findAll(){
//        List<Article> articles = articleRepository.findAll();
//        return articles.stream()
//                .map(this::provideDto)
//                .collect(Collectors.toList());
//    }
//
//    public ArticleDto update(Long id, ArticleDto articleDto){
//        Article article = articleRepository.findById(id).get();
//        Article articleUpdated = provideEntity(articleDto);
//        article.setName(articleUpdated.getName());
//        article.setCatalogueNumber(articleUpdated.getCatalogueNumber());
//        article.setConsumable(articleUpdated.isConsumable());
//        article.setAlternative(articleUpdated.isAlternative());
//        article.setPurchasePrice(articleUpdated.getPurchasePrice());
//        article.setSalePrice(articleUpdated.getSalePrice());
//        article.setManufacturer(articleUpdated.getManufacturer());
//        article.setYield(articleUpdated.getYield());
//        article.setModels(articleUpdated.getModels());
//        articleRepository.save(article);
//        return provideDto(article);
//    }
//
//    public ArticleDto getById(Long id){
//        Article article = articleRepository.findById(id).orElse(new Article());
//        return provideDto(article);
//    }
//
//
//    public List<ArticleDto> getAllByParameters(String name, String catalogueNumber, Boolean isConsumable, Boolean isAlternative, String manufacturerName){
//        List<Article> articles = articleRepository.getAllByNameContainsAndCatalogueNumberContainsAndIsConsumableAndIsAlternativeAndManufacturerNameContains(name,catalogueNumber,isConsumable,isAlternative,manufacturerName);
//        return articles.stream()
//                .map(this::provideDto)
//                .collect(Collectors.toList());
//    }
//
//    public List<ArticleDto> getAllByParameters(String name, String catalogueNumber, String manufacturerName){
//        List<Article> articles = articleRepository.getAllByNameContainsAndCatalogueNumberContainsAndManufacturerNameContains(name,catalogueNumber,manufacturerName);
//        return articles.stream()
//                .map(this::provideDto)
//                .collect(Collectors.toList());
//    }

}