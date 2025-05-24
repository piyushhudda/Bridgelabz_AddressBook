package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    
 // Convert Address entity to DTO
    private AddressDTO convertToDTO(Address address) {
        return new AddressDTO(address.getName(), address.getEmail(), address.getPhone());
    }

    // Convert DTO to Address entity
    private Address convertToEntity(AddressDTO dto) {
        Address address = new Address();
        address.setName(dto.getName());
        address.setEmail(dto.getEmail());
        address.setPhone(dto.getPhone());
        return address;
    }

 // GET all addresses
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> dtos = addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        return addressRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new address
    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(convertToDTO(savedAddress));
    }

    // Update existing address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return addressRepository.findById(id).map(address -> {
            address.setName(addressDetails.getName());
            address.setPhone(addressDetails.getPhone());
            address.setEmail(addressDetails.getEmail());
            address.setAddressLine(addressDetails.getAddressLine());
            Address updatedAddress = addressRepository.save(address);
            return ResponseEntity.ok(updatedAddress);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}