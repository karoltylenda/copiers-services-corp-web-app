package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        Article article = convertToEntity(articleDto);
        articleService.save(article);
        return convertToDto(article);
    }

    @GetMapping
    public List<ArticleDto> getAll(){
        List<Article> articles = articleService.findAll();
        return articles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ArticleDto getById(@PathVariable("id")Long id){
        Article article = articleService.findById(id);
        return convertToDto(article);
    }

    @PutMapping(value = "/{id}")
    public ArticleDto update(@PathVariable("id")Long id,@RequestBody ArticleDto articleDto){
        Article article = convertToEntity(articleDto);
        Article articleUpdated = articleService.update(id,article);
        return convertToDto(articleUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        if(articleService.deleteById(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }


    private ArticleDto convertToDto(Article article){
        ArticleDto articleDto = modelMapper.map(article, ArticleDto.class);
        return articleDto;
    }

    private Article convertToEntity(ArticleDto articleDto){
        Article article = modelMapper.map(articleDto, Article.class);
        return article;
    }


}
