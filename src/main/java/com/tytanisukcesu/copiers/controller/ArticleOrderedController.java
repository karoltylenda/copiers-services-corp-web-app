package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.ArticleOrderedDto;
import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.service.ArticleOrderedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/article-ordered")
@RequiredArgsConstructor
public class ArticleOrderedController {

    private final ModelMapper modelMapper;
    private final ArticleOrderedService articleOrderedService;

    @GetMapping
    public List<ArticleOrderedDto> getAll(){
        List<ArticleOrdered> articleOrderedList = articleOrderedService.findAll();
        return articleOrderedList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ArticleOrderedDto getById(@PathVariable("id") Long id){
        ArticleOrdered articleOrdered = articleOrderedService.findById(id);
        return convertToDto(articleOrdered);
    }

    @PostMapping
    public ArticleOrderedDto save(@RequestBody ArticleOrderedDto articleOrderedDto){
        ArticleOrdered articleOrdered = convertToEntity(articleOrderedDto);
        ArticleOrdered articleOrderedSaved = articleOrderedService.save(articleOrdered);
        return convertToDto(articleOrderedSaved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(articleOrderedService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

//    @PutMapping(value = "/{id}")
//    public ArticleOrderedDto update(@PathVariable("id") Long id, @RequestBody ArticleOrderedDto articleOrderedDto){
//        ArticleOrdered articleOrdered = convertToEntity(articleOrderedDto);
//        ArticleOrdered articleOrderedSaved = articleOrderedService.update(id,contract);
//        return convertToDto(contractSaved);
//    }

    private ArticleOrderedDto convertToDto(ArticleOrdered articleOrdered) {
        ArticleOrderedDto articleOrderedDto = modelMapper.map(articleOrdered, ArticleOrderedDto.class);
        return articleOrderedDto;
    }

    private ArticleOrdered convertToEntity(ArticleOrderedDto articleOrderedDto){
        ArticleOrdered articleOrdered = modelMapper.map(articleOrderedDto, ArticleOrdered.class);
        return articleOrdered;
    }

}
