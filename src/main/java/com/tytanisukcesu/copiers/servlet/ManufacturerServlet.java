package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.ManufacturerDto;
import com.tytanisukcesu.copiers.dto.ModelDto;
import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manufacturers")
public class ManufacturerServlet {

    private final ManufacturerService manufacturerService;
    private ModelMapper modelMapper;

    @PostMapping
    public RedirectView save(ManufacturerDto manufacturerDto){
        manufacturerService.save(convertToEntity(manufacturerDto));
        return new RedirectView("/models");
    }

    private Manufacturer convertToEntity(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = modelMapper.map(manufacturerDto, Manufacturer.class);
        return manufacturer;
    }

}
