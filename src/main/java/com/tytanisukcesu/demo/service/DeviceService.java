package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.DeviceRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelRepository modelRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device findById(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.orElse(new Device());
    }

    public Device save(Device device) {
        Model model = device.getModel();
        Device deviceSaved = new Device();
        Optional<Model> modelOptional = modelRepository.getByNameAndManufacturerName(model.getName(), model.getManufacturer().getName());
        if (modelOptional.isPresent()){
            device.setModel(new Model());
            deviceSaved = deviceRepository.save(device);
            model.getDevices().add(deviceSaved);
        } else {
            deviceSaved = deviceRepository.save(device);
        }
        return deviceRepository.findById(deviceSaved.getId()).get();
    }
}
