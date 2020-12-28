package com.tytanisukcesu.demo.servlet;

import com.tytanisukcesu.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/models")
@RequiredArgsConstructor
public class ModelServlet {

    private final ModelService modelService;

    @GetMapping
    public String getAllModel(Model model){
        model.addAttribute("models", modelService.findAll());
        return "models";
    }
}
