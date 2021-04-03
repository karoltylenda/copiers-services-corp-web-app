package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.service.ArticleService;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleServlet {

    private final ArticleService articleService;
    private final ManufacturerService manufacturerService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "pages/articles";
    }
}
