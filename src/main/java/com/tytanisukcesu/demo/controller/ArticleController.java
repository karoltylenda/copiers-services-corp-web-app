package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    //TODO z 2 parametrami
    @GetMapping
    public List<ArticleDto> getByParameter(@RequestParam String name,
                                           @RequestParam String catalogNumber){
        if(name!=null){
            return articleService.getAllByName(name);
        }else if(catalogNumber!=null){
            return articleService.getAllByCatalogNumber(catalogNumber);
        }else{
            return articleService.findAll();
        }
    }

    @PostMapping
    public ArticleDto save(@RequestBody ArticleDto articleDto){
        return articleService.save(articleDto);
    }

    @GetMapping(value = "/{id}")
    public ArticleDto getById(@PathVariable("id") Long id){
        return articleService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ArticleDto update(@PathVariable("id")Long id, @RequestBody ArticleDto articleDto){
        return articleService.update(id,articleDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        if(articleService.delete(id)){
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

}
