package com.tytanisukcesu.demo.servlet;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/main/models")
@RequiredArgsConstructor
public class ModelServlet {

    private final ModelService modelService;

    @GetMapping
    public String getAllModel(Model springModel){
        springModel.addAttribute("models", modelService.findAll());
        return "models";
    }

    @GetMapping(value = "/search")
    public String GetSingleModel(@RequestParam(required = false, defaultValue = "") String manufacturer,
                                       @RequestParam(required = false, defaultValue = "") String model,
                                       @RequestParam(required = false) Boolean printsInColor,
                                        Model springModel){
        if (printsInColor == null){
            springModel.addAttribute("models", modelService.getAllByParameters(manufacturer, model));
        } else {
            springModel.addAttribute("models",modelService.getAllByParameters(manufacturer,model,printsInColor));
        }
        return "models";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model springModel){
        springModel.addAttribute("model",modelService.getById(id));
        return "model";
    }



}
