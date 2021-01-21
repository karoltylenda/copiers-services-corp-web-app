package com.tytanisukcesu.copiers.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/models")
@RequiredArgsConstructor
public class ModelServlet {

//    private final ModelService modelService;
//    private final ManufacturerService manufacturerService;
//
//    @GetMapping
//    public String findAll(Model springModel){
//        springModel.addAttribute("models", modelService.findAll());
//        return "models";
//    }
//
//    @GetMapping(value = "/search")
//    public String getAllByParameters(@RequestParam(required = false, defaultValue = "") String manufacturer,
//                                       @RequestParam(required = false, defaultValue = "") String model,
//                                       @RequestParam(required = false) Boolean printsInColor,
//                                        Model springModel){
//        if (printsInColor == null){
//            springModel.addAttribute("models", modelService.getAllByParameters(manufacturer, model));
//        } else {
//            springModel.addAttribute("models",modelService.getAllByParameters(manufacturer,model,printsInColor));
//        }
//        return "models";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String getById(@PathVariable("id") Long id, Model springModel){
//        springModel.addAttribute("model",modelService.getById(id));
//        return "model";
//    }
//
//    //FIXME - zmien na postMapping
//    @GetMapping("/save")
//    public RedirectView save(@RequestParam String manufacturerName,  @ModelAttribute ModelDto modelDto) {
//        ManufacturerDto manufacturerDto = new ManufacturerDto();
//        if (manufacturerService.getByName(manufacturerName).isEmpty()) {
//            manufacturerDto.setName(manufacturerName);
//            manufacturerService.save(manufacturerDto);
//        } else {
//            manufacturerDto = manufacturerService.getByName(manufacturerName).get(0);
//            manufacturerService.update(manufacturerDto.getId(), manufacturerDto);
//        }
//        modelDto.setManufacturer(manufacturerService.provideEntity(manufacturerDto));
//        modelService.save(modelDto);
//        return new RedirectView("/main/addModelForm");
//    }


}
