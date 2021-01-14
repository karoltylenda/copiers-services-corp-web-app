package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.AddressDto;
import com.tytanisukcesu.demo.entity.Address;
import com.tytanisukcesu.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public boolean deleteById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Address findById(Long id){
        return addressRepository.findById(id).orElse(new Address());
    }

    public Address update(Long id, Address address) {
        Address addressToUpdate = addressRepository.findById(id).orElse(new Address());
        addressToUpdate.setStreet(address.getStreet());
        addressToUpdate.setProvince(address.getProvince());
        addressToUpdate.setPostCode(address.getPostCode());
        addressToUpdate.setHouseNumber(address.getHouseNumber());
        addressToUpdate.setCity(address.getCity());
        addressToUpdate.setApartmentNumber(address.getApartmentNumber());
        addressToUpdate.setCustomer(address.getCustomer());
        addressToUpdate.setInvoice(address.isInvoice());
        return addressToUpdate;
    }


}
