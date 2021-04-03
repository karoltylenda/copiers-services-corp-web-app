package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.Address;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.service.AddressService;
import com.tytanisukcesu.copiers.service.CustomerService;
import com.tytanisukcesu.copiers.service.DeviceService;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final ManufacturerService manufacturerService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("devices", deviceService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "pages/devices";
    }

    @PostMapping
    public RedirectView save(DeviceDto deviceDto){
        deviceService.saveFromServlet(convertToEntity(deviceDto));
        return new RedirectView("/devices");
    }

    @PostMapping(value = "/update")
    public RedirectView update(DeviceDto deviceDto){
        Device device = convertToEntity(deviceDto);
        addressService.update(device.getAddress().getId(), device.getAddress());
        return new RedirectView("/devices");
    }

    @PostMapping(value = "/delete")
    public RedirectView delete (DeviceDto deviceDto){
        Device device = convertToEntity(deviceDto);
        deviceService.delete(device.getId());
        return new RedirectView("/devices");
    }

    private Device convertToEntity(DeviceDto deviceDto){
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }
}
