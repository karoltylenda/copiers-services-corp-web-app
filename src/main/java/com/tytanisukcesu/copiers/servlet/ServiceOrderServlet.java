package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.dto.ServiceOrderDto;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.service.DeviceService;
import com.tytanisukcesu.copiers.service.ServiceOrderService;
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
@RequestMapping(value = "/serviceOrders")
public class ServiceOrderServlet {

    private final ServiceOrderService serviceOrderService;
    private final DeviceService deviceService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("serviceOrders", serviceOrderService.findAll());
        model.addAttribute("devices", deviceService.findAll());
        return ("pages/serviceOrders");
    }

    @PostMapping
    public RedirectView save(ServiceOrderDto serviceOrderDto){
        serviceOrderService.save(convertToEntity(serviceOrderDto));
        return new RedirectView("/serviceOrders");
    }

    private ServiceOrderDto convertToDto(ServiceOrder serviceOrder){
        ServiceOrderDto serviceOrderDto = modelMapper.map(serviceOrder, ServiceOrderDto.class);
        return serviceOrderDto;
    }

    private ServiceOrder convertToEntity(ServiceOrderDto serviceOrderDto){
        ServiceOrder serviceOrder = modelMapper.map(serviceOrderDto,ServiceOrder.class);
        return serviceOrder;
    }


}
