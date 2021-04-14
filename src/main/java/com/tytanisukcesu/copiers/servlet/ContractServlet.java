package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.dto.ContractDto;
import com.tytanisukcesu.copiers.entity.Contract;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.service.ContractService;
import com.tytanisukcesu.copiers.service.CustomerService;
import com.tytanisukcesu.copiers.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/contracts")
public class ContractServlet {

    private final ContractService contractService;
    private final CustomerService customerService;
    private final DeviceService deviceService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return ("pages/contracts");
    }

    @PostMapping
    public RedirectView saveContract(ContractDto contractDto){
        Contract contract = convertToEntity(contractDto);
        contract.setDevice(deviceService.findById(contractDto.getDevice().getId()));
        contractService.save(contract);
        return new RedirectView("/contracts");
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteContract(ContractDto contractDto){
        contractService.delete(contractDto.getId());
        return new RedirectView("/contracts");
    }

    private Contract convertToEntity(ContractDto contractDto){
        Contract contract = modelMapper.map(contractDto, Contract.class);
        return contract;
    }
}
