package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelService modelService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final MapperService mapperService;
    private static final Logger LOGGER = Logger.getLogger(DeviceService.class.getName());

    public List<Device> findAll() {
        List<Device> devices = deviceRepository.findAll();
        return devices;
    }

    public Device findById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id, "device"));
    }

    @Transactional
    public Device save(Device device) {
        Optional<Device> deviceOptional = deviceRepository.getDeviceBySerialNumber(device.getSerialNumber());
        if (deviceOptional.isPresent()) {
            return deviceOptional.get();
        } else {
            Device deviceToSave = new Device();
            deviceToSave.setSerialNumber(device.getSerialNumber());
            deviceToSave.setModel(modelService.save(device.getModel()));
            deviceToSave.setCustomer(customerService.save(device.getCustomer()));
            deviceToSave.setContract(device.getContract());
            deviceToSave.setAddress(device.getAddress());
            LOGGER.info("A new row has been added.");
            return deviceRepository.save(deviceToSave);
        }
    }

    @Transactional
    public Device saveFromServlet(Device device) {
        Optional<Device> deviceOptional = deviceRepository.getDeviceBySerialNumber(device.getSerialNumber());
        if (deviceOptional.isPresent()) {
            return deviceOptional.get();
        } else {
            Device deviceToSave = new Device();
            deviceToSave.setSerialNumber(device.getSerialNumber());
            deviceToSave.setModel(modelService.findById(device.getModel().getId()));
            deviceToSave.setCustomer(customerService.findById(device.getCustomer().getId()));
            deviceToSave.setAddress(addressService.save(device.getAddress()));
            LOGGER.info("A new row has been added.");
            return deviceRepository.save(deviceToSave);
        }
    }

    @Transactional
    @CachePut(cacheNames = "SingleDevice", key = "#result.id")
    public Device update(Long id, Device device) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            Device deviceUpdated = deviceOptional.get();
            deviceUpdated.setModel(device.getModel());
            deviceUpdated.setSerialNumber(device.getSerialNumber());
            deviceUpdated.setAddress(device.getAddress());
            deviceUpdated.setCounters(device.getCounters());
            deviceUpdated.setCustomer(device.getCustomer());
            deviceUpdated.setContract(device.getContract());
            LOGGER.info("Device with serial number " + deviceUpdated.getSerialNumber() + " for id " + deviceUpdated.getId() + " has been updated.");
            return deviceUpdated;
        } else {
            LOGGER.warning("Device for id " + id + " has not been found");
            throw new ModelNotFoundException(id, "device");
        }
    }

    @CacheEvict(cacheNames = "SingleDevice")
    public boolean delete(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            deviceRepository.delete(deviceOptional.get());
            LOGGER.info("Device for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Device for id " + id + " has not been deleted");
            return false;
        }
    }

    public List<Device> findByCustomer(Long customerId) {
        return deviceRepository.getAllByCustomer_Id(customerId);
    }

}
