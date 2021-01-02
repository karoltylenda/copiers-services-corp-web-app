package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.dto.ModelDto;
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

    @GetMapping(value = "/search")
    public List<ArticleDto> getByAllParameters(@RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false, defaultValue = "") String catalogueNumber,
                                             @RequestParam boolean isConsumable,
                                             @RequestParam(defaultValue = "true") boolean isAlternative,
                                             @RequestParam(required = false, defaultValue = "") String manufacturerName){
        return articleService.getAllByParameters(name,catalogueNumber,isConsumable,isAlternative,manufacturerName);
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
