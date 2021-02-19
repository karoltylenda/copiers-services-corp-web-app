package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.ArticleDto;
import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

//    @GetMapping(value = "/search")
//    public List<ArticleDto> getByAllParameters(@RequestParam(required = false, defaultValue = "") String name,
//                                             @RequestParam(required = false, defaultValue = "") String catalogueNumber,
//                                             @RequestParam boolean isConsumable,
//                                             @RequestParam(defaultValue = "false") boolean isAlternative,
//                                             @RequestParam(required = false, defaultValue = "") String manufacturerName){
//        return articleService.getAllByParameters(name,catalogueNumber,isConsumable,isAlternative,manufacturerName);
//    }

    @PostMapping
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        Article article = convertToEntity(articleDto);
        Article articleSaved = articleService.save(article);
        return convertToDto(articleSaved);
    }

    @GetMapping(value = "/{id}")
    public ArticleDto getById(@PathVariable("id") Long id){
        Article article = articleService.findById(id);
        return convertToDto(article);
    }

    @PutMapping(value = "/{id}")
    public ArticleDto update(@PathVariable("id")Long id, @RequestBody ArticleDto articleDto){
        Article article = convertToEntity(articleDto);
        Article articleUpdated = articleService.update(id,article);
        return convertToDto(articleUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        if(articleService.delete(id)){
            return ResponseEntity.accepted().build();
        }else {
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
