package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.dto.ModelDto;
import com.tytanisukcesu.copiers.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelServlet {

    private final ModelService modelService;
    private final ModelMapper modelMapper;


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("models", modelService.findAll());
        return "pages/models";
    }

    private ModelDto convertToDto(Model model) {
        ModelDto modelDto = modelMapper.map(model, ModelDto.class);
        return modelDto;
    }

    private Model convertToEntity(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        return model;
    }


}
