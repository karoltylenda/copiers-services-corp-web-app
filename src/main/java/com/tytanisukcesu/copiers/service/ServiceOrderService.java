package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final DeviceService deviceService;

    //TODO asd
    public ServiceOrder save(ServiceOrder serviceOrder) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getServiceOrderByServiceOrderNumber(serviceOrder.getServiceOrderNumber());
        if (serviceOrderOptional.isPresent()) {
            return serviceOrderOptional.get();
        } else {
            ServiceOrder serviceOrderToSave = new ServiceOrder();
            serviceOrderToSave.setDevice(deviceService.save(serviceOrder.getDevice()));
            serviceOrderToSave.setArticleOrderedSet(serviceOrder.getArticleOrderedSet());
            serviceOrderToSave.setOrderStatus(serviceOrder.getOrderStatus());
            serviceOrderToSave.setLastUpdateDate(LocalDateTime.now());
            serviceOrderToSave.setDescriptionOfTheFault(serviceOrder.getDescriptionOfTheFault());
            serviceOrderToSave.setOrderStartDate(serviceOrder.getOrderStartDate());
            serviceOrderToSave.setOrderEndDate(serviceOrder.getOrderEndDate());
            ServiceOrder serviceOrderSaved = serviceOrderRepository.save(serviceOrderToSave);
            return serviceOrderSaved;
        }
    }

    public List<ServiceOrder> findAll(){
        List<ServiceOrder> serviceOrders = serviceOrderRepository.findAll();
        return serviceOrders;
    }

    public ServiceOrder findById(Long id){
        return serviceOrderRepository.findById(id).orElse(new ServiceOrder());
    }

    public boolean delete(Long id) {
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(id);
        if (serviceOrder.isPresent()) {
            serviceOrderRepository.delete(serviceOrder.get());
            return true;
        } else {
            return false;
        }
    }

    public ServiceOrder update(Long id,ServiceOrder serviceOrder){
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(id);
        if(serviceOrderOptional.isPresent()){
            ServiceOrder serviceOrderUpdated = serviceOrderOptional.get();
            serviceOrderUpdated.setArticleOrderedSet(serviceOrder.getArticleOrderedSet());
            serviceOrderUpdated.setOrderStatus(serviceOrder.getOrderStatus());
            serviceOrderUpdated.setLastUpdateDate(LocalDateTime.now()); //*
            serviceOrderUpdated.setDevice(serviceOrder.getDevice());
            serviceOrderUpdated.setDescriptionOfTheFault(serviceOrder.getDescriptionOfTheFault());
            serviceOrderUpdated.setOrderStartDate(serviceOrder.getOrderStartDate());
            serviceOrderUpdated.setOrderEndDate(serviceOrder.getOrderEndDate());
            return serviceOrderUpdated;
        }else{
            return new ServiceOrder();
        }
    }





}
