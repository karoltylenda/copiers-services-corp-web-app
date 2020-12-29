package com.tytanisukcesu.demo.servlet;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/manufacturer")
public class ManufacturerServlet {

    private final ManufacturerService manufacturerService;

    @PostMapping("/save")
    public RedirectView save(@ModelAttribute ManufacturerDto manufacturerDto) {
        manufacturerService.save(manufacturerDto);
        return new RedirectView("/main/addManufacturerForm");
    }
}
