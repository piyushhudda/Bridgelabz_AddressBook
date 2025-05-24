package com.example.demo.controller;

import com.example.demo.dto.AddressDTO;
import com.example.demo.model.Address;
import com.example.demo.service.AddressService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Convert Address entity to DTO
    private AddressDTO convertToDTO(Address address) {
        return new AddressDTO(address.getId(), address.getName(), address.getEmail(), address.getPhone());
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
        List<Address> addresses = addressService.getAllAddresses();
        List<AddressDTO> dtos = addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address createdAddress = addressService.createAddress(addressDTO);
        return ResponseEntity.ok(convertToDTO(createdAddress));
    }

 // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
        Address address = addressService.getAddressById(id); // throws if not found
        return ResponseEntity.ok(convertToDTO(address));
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
        Address updatedAddress = addressService.updateAddress(id, addressDTO);
        // Assuming your update method also throws if not found
        return ResponseEntity.ok(convertToDTO(updatedAddress));
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        // This will throw if address not found
        addressService.getAddressById(id);
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}