package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.ArticleDto;
import com.tytanisukcesu.copiers.dto.ModelDto;
import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.service.ArticleService;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import com.tytanisukcesu.copiers.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleServlet {

    private final ArticleService articleService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("articles", articleService.findAll());
        model.addAttribute("manufacturers",manufacturerService.findAll());
        model.addAttribute("models",modelService.findAll());
        return "pages/articles";
    }

    @PostMapping
    public RedirectView save(ArticleDto articleDto,ArrayList<Integer> modelsList){
        articleService.save(convertToEntity(articleDto));
        return new RedirectView("/articles");
    }

    @GetMapping(value = "/")
    public String findByCustomer(@RequestParam Long id, Model model){
        model.addAttribute("models", articleService.findById(id).getModels());
        return "pages/articles";
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
