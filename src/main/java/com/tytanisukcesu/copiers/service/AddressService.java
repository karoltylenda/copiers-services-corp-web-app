package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Address;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        Address addressToSave = addressRepository.save(address);
        return addressToSave;
    }

    public Address update(Long id, Address address) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address addressUpdated = addressOptional.get();
            addressUpdated.setAddresType(address.getAddresType());
            addressUpdated.setCity(address.getCity());
            addressUpdated.setCustomer(address.getCustomer());
            addressUpdated.setDevice(address.getDevice());
            addressUpdated.setApartmentNumber(address.getApartmentNumber());
            addressUpdated.setHouseNumber(address.getHouseNumber());
            addressUpdated.setPostCode(address.getPostCode());
            addressUpdated.setStreet(address.getStreet());
            addressUpdated.setProvince(address.getProvince());
            return addressUpdated;
        } else {
            return new Address();
        }
    }

    public Address findById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElse(new Address());
    }

    public List<Address> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public boolean delete(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            addressRepository.delete(addressOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
