package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        Optional<Address> addressOpt = addressRepository.findById(id);
        return addressOpt.orElse(null);
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setName(addressDTO.getName());
        address.setEmail(addressDTO.getEmail());
        address.setPhone(addressDTO.getPhone());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, AddressDTO addressDTO) {
        Optional<Address> addressOpt = addressRepository.findById(id);
        if (addressOpt.isPresent()) {
            Address address = addressOpt.get();
            address.setName(addressDTO.getName());
            address.setEmail(addressDTO.getEmail());
            address.setPhone(addressDTO.getPhone());
            return addressRepository.save(address);
        }
        return null; // or throw exception
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}