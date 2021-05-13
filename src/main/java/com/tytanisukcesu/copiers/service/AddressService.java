package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.dto.AddressDto;
import com.tytanisukcesu.copiers.entity.Address;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.AddressRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = Logger.getLogger(AddressService.class.getName());

    public Address save(Address address) {
        Address addressToSave = addressRepository.save(address);
        LOGGER.info("A new row has been added.");
        return addressToSave;
    }

    @Transactional
    public AddressDto update(Long id, AddressDto addressDto) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address addressUpdated = addressOptional.get();
            addressUpdated.setAddressType(addressDto.getAddressType());
            addressUpdated.setCity(addressDto.getCity());
//            addressUpdated.setCustomer(addressDto.getCustomer());
//            addressUpdated.setDevice(addressDto.getDevice());
            addressUpdated.setApartmentNumber(addressDto.getApartmentNumber());
            addressUpdated.setHouseNumber(addressDto.getHouseNumber());
            addressUpdated.setPostCode(addressDto.getPostCode());
            addressUpdated.setStreet(addressDto.getStreet());
            addressUpdated.setProvince(addressDto.getProvince());
            LOGGER.info("Address " + " for id " + addressUpdated.getId() + " has been updated.");
            return convertToDto(addressUpdated);
        } else {
            LOGGER.warning("Address for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"address");
        }
    }

    public AddressDto findById(Long id) {
        return convertToDto(addressRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id,"address")));
    }

    public List<AddressDto> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            addressRepository.delete(addressOptional.get());
            LOGGER.info("Address for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Address for id " + id + " has not been deleted");
            return false;
        }
    }

    public AddressDto convertToDto(Address address){
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;
    }

    public Address convertToEntity(AddressDto addressDto){
        Address address = modelMapper.map(addressDto, Address.class);
        return address;
    }
}
