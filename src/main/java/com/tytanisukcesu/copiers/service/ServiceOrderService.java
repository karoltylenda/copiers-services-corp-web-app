package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.repository.ServiceOrderRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final DeviceService deviceService;
    private final ArticleOrderedService articleOrderedService;
    private static final Logger LOGGER = Logger.getLogger(CounterService.class.getName());

    //TODO co ma być logowane

    @Transactional
    public ServiceOrder save(ServiceOrder serviceOrder) {
        if (!checkIfServiceOrderExists(serviceOrder.getDevice().getSerialNumber()) || serviceOrder.getOrderType().equals(ServiceOrderType.CONSUMABLE_DELIVERY)) {
            ServiceOrder serviceOrderToSave = new ServiceOrder();
            serviceOrderToSave.setDevice(deviceService.save(serviceOrder.getDevice()));
            Set<ArticleOrdered> articleOrderedSet = new HashSet<>();
            for (ArticleOrdered articleOrdered: serviceOrder.getArticleOrderedSet()) {
                articleOrderedSet.add(articleOrderedService.save(articleOrdered));
            }
            serviceOrderToSave.setArticleOrderedSet(articleOrderedSet);
            serviceOrderToSave.setOrderStatus(ServiceOrderStatus.NEW);
            serviceOrderToSave.setLastUpdateDate(LocalDateTime.now());
            serviceOrderToSave.setOrderType(serviceOrder.getOrderType());
            serviceOrderToSave.setOrderCreationDate(LocalDateTime.now());
            serviceOrderToSave.setServiceOrderNumber(generateOrderNumber(serviceOrderToSave.getOrderCreationDate()));
            serviceOrderToSave.setDescriptionOfTheFault(serviceOrder.getDescriptionOfTheFault());
            serviceOrderToSave.setOrderStartDate(serviceOrder.getOrderStartDate());
            serviceOrderToSave.setOrderEndDate(serviceOrder.getOrderEndDate());
            ServiceOrder serviceOrderSaved = serviceOrderRepository.save(serviceOrderToSave);
            articleOrderedSet.forEach(articleOrdered -> {
                articleOrdered.setServiceOrder(serviceOrderSaved);
            });
            return serviceOrderSaved;
        } else {
            return new ServiceOrder();
        }
    }


    //TODO
    private String generateOrderNumber(LocalDateTime localDateTime) {
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonth().getValue());
        String generatedValue = generatedValue(localDateTime);
        return year + "/" + month + "/" + generatedValue;
    }

    //znajdz wszystkie ordery w danym miesiaciu - od 1 do 30
    public List<ServiceOrder> getOrderForRequestedMonth(LocalDateTime localDateTime) {
        return serviceOrderRepository.findByOrderCreationDateBetweenOrderByOrderCreationDateDesc(localDateTime.with(TemporalAdjusters.firstDayOfMonth()), localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public String generatedValue(LocalDateTime localDateTime) {
        List<ServiceOrder> serviceOrders = getOrderForRequestedMonth(localDateTime);
        if (serviceOrders.isEmpty()) {
            return "1";
        } else {
            return String.valueOf(serviceOrders.size() + 1);
        }
    }


    public List<ServiceOrder> findAll() {
        List<ServiceOrder> serviceOrders = serviceOrderRepository.findAll();
        return serviceOrders;
    }

    public ServiceOrder findById(Long id) {
        return serviceOrderRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"service order"));
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

    public ServiceOrder update(Long id, ServiceOrder serviceOrder) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.findById(id);
        if (serviceOrderOptional.isPresent() && serviceOrderOptional.get().getOrderStatus() != ServiceOrderStatus.COMPLETED) {
            ServiceOrder serviceOrderUpdated = serviceOrderOptional.get();
            serviceOrderUpdated.setArticleOrderedSet(serviceOrder.getArticleOrderedSet());
            serviceOrderUpdated.setOrderStatus(serviceOrder.getOrderStatus());
            serviceOrderUpdated.setLastUpdateDate(LocalDateTime.now());
            serviceOrderUpdated.setDevice(serviceOrder.getDevice());
            serviceOrderUpdated.setDescriptionOfTheFault(serviceOrder.getDescriptionOfTheFault());
            serviceOrderUpdated.setOrderStartDate(serviceOrder.getOrderStartDate());
            serviceOrderUpdated.setOrderEndDate(serviceOrder.getOrderEndDate());
            return serviceOrderUpdated;
        } else {
            return new ServiceOrder();
        }
    }

    //tylko nowe i tylko technical review + repair
    public boolean checkIfServiceOrderExists(String serialNumber) {
        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getFirstByDeviceSerialNumberAndOrderStatusAndOrderTypeNot(serialNumber, ServiceOrderStatus.NEW, ServiceOrderType.CONSUMABLE_DELIVERY);

        return serviceOrderOptional.isPresent();
    }


}
