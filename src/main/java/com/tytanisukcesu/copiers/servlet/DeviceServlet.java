package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.service.CustomerService;
import com.tytanisukcesu.copiers.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/devices")
public class DeviceServlet {

    private final DeviceService deviceService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("devices", deviceService.findAll());
        model.addAttribute("cumstomers", customerService.findAll());
        return "pages/devices";
    }

    @PostMapping
    public RedirectView save(DeviceDto deviceDto){
        System.out.println(deviceDto.toString());
        return new RedirectView("/devices");
    }
}
