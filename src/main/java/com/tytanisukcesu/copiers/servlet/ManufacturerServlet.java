package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/manufacturer")
public class ManufacturerServlet {

    private final ManufacturerService manufacturerService;
//
//    @PostMapping("/save")
//    public RedirectView save(@ModelAttribute ManufacturerDto manufacturerDto) {
//        manufacturerService.save(manufacturerDto);
//        return new RedirectView("/main/addManufacturerForm");
//    }
}
