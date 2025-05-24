package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddressById(Long id);
    Address createAddress(AddressDTO addressDTO);
    Address updateAddress(Long id, AddressDTO addressDTO);
    void deleteAddress(Long id);
}