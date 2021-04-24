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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

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
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("models", modelService.findAll());
        return "pages/articles";
    }

    //wyrzuc do oddzielnej metody - najlepiej w serwisie
    @PostMapping
    public RedirectView save(ArticleDto articleDto, Integer[] modelsArray) {
        Set<com.tytanisukcesu.copiers.entity.Model> models = new HashSet<>();
        for (Integer integer : modelsArray) {
            Long modelId = Long.valueOf(integer);
            models.add(modelService.findById(modelId));
        }
        Article article = convertToEntity(articleDto);
        article.setModels(models);
        articleService.saveFromServlet(article);
        return new RedirectView("/articles");
    }

//    @GetMapping(value = "/")
//    public String findByCustomer(@RequestParam Long id, Model model) {
//        model.addAttribute("models", articleService.findById(id).getModels());
//        return "pages/articles";
//    }

    @PostMapping(value = "/update")
    public RedirectView update(Long id,ArticleDto articleDto){
        articleService.update(id,convertToEntity(articleDto));
        return new RedirectView("/articles");
    }

    @PostMapping(value = "/delete")
    public RedirectView delete (ArticleDto articleDto){
        Article article = convertToEntity(articleDto);
        articleService.delete(article.getId());
        return new RedirectView("/articles");
    }

    private ArticleDto convertToDto(Article article) {
        ArticleDto articleDto = modelMapper.map(article, ArticleDto.class);
        return articleDto;
    }

    private Article convertToEntity(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        return article;
    }


}
