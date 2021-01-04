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

    public Address provideEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .apartmentNumber(addressDto.getApartmentNumber())
                .customers(addressDto.getCustomers())
                .houseNumber(addressDto.getHouseNumber())
                .postCode(addressDto.getPostCode())
                .city(addressDto.getCity())
                .province(addressDto.getProvince())
                .street(addressDto.getStreet())
                .build();
    }

    public AddressDto provideDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .apartmentNumber(address.getApartmentNumber())
                .customers(address.getCustomers())
                .houseNumber(address.getHouseNumber())
                .postCode(address.getPostCode())
                .city(address.getCity())
                .province(address.getProvince())
                .street(address.getStreet())
                .build();
    }


    public AddressDto save(AddressDto addressDto) {
        Address address = provideEntity(addressDto);
        addressRepository.save(address);
        return provideDto(address);
    }

    public boolean delete(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.delete(address.get());
            return true;
        } else {
            return false;
        }
    }

    public List<AddressDto> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public AddressDto update(Long id, AddressDto addressDto) {
        Address address = addressRepository.findById(id).orElseThrow();
        Address addressUpdated = provideEntity(addressDto);
        address.setApartmentNumber(addressUpdated.getApartmentNumber());
        address.setCity(addressUpdated.getCity());
        address.setCustomers(addressUpdated.getCustomers());
        address.setHouseNumber(addressUpdated.getHouseNumber());
        address.setPostCode(addressUpdated.getPostCode());
        address.setProvince(addressUpdated.getProvince());
        address.setStreet(addressUpdated.getStreet());
        return provideDto(address);
    }

    public Optional<AddressDto> findAddressIfExists(AddressDto addressDto){
        Address addressToReturn = new Address();
        List<Address> addresses = addressRepository.findAll();
        Address addressToFind = provideEntity(addressDto);
        for(Address address:addresses){
            if(address.equals(addressToFind)){
                addressToReturn = address;
                break;
            } else {
                addressToReturn = provideEntity(addressDto);
            }
        }
        return Optional.ofNullable(provideDto(addressToReturn));
    }


}
