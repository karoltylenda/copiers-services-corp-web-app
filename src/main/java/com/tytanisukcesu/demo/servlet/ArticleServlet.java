package com.tytanisukcesu.demo.servlet;

import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.service.ArticleService;
import com.tytanisukcesu.demo.service.ManufacturerService;
import com.tytanisukcesu.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/main/articles")
@RequiredArgsConstructor
public class ArticleServlet {

    private final ArticleService articleService;
    private final ManufacturerService manufacturerService;

    @GetMapping
    public String findAll(Model springModel){
        springModel.addAttribute("articles",articleService.findAll());
        return "articles";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id")Long id,Model springModel){
        springModel.addAttribute("article",articleService.getById(id));
        return "article";
    }

    @GetMapping(value = "/search")
    public String getAllByParameters(@RequestParam(required = false, defaultValue = "") String name,
                                     @RequestParam(required = false, defaultValue = "") String catalogueNumber,
                                     @RequestParam(required = false) Boolean isConsumable,
                                     @RequestParam(required = false) Boolean isAlternative,
                                     @RequestParam(required = false, defaultValue = "") String manufacturerName,
                                     Model springModel){
        if(isConsumable == null && isAlternative == null){
            springModel.addAttribute("articles",articleService.getAllByParameters(name,catalogueNumber,manufacturerName));
        }else{
            springModel.addAttribute("articles",articleService.getAllByParameters(name,catalogueNumber,isConsumable,isAlternative,manufacturerName))
        }
        return "articles";
    }

    @PostMapping("/save")
    public RedirectView save(@RequestParam String manufacturerName, @ModelAttribute ArticleDto articleDto){
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        if(manufacturerService.getByName(manufacturerName).isEmpty()){
            manufacturerDto.setName(manufacturerName);
            manufacturerService.save(manufacturerDto)
        }else{
            manufacturerDto = manufacturerService.getByName(manufacturerName).get(0);
            manufacturerService.update(manufacturerDto.getId(),manufacturerDto);
        }
        articleDto.setManufacturer(manufacturerService.provideEntity(manufacturerDto));
        articleService.save(articleDto);
        return new RedirectView("/main");


    }

    
}
