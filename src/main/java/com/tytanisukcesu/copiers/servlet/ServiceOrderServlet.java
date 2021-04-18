package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.dto.ServiceOrderDto;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/serviceOrders")
public class ServiceOrderServlet {

    private final ServiceOrderService serviceOrderService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(final Model model){
        model.addAttribute("serviceOrders", serviceOrderService.findAll());
        return ("pages/serviceOrders");
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
