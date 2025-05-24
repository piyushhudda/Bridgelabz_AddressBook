package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AddressDTO {
	long id;
	@NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name must contain only letters and spaces")
 private String name;
 private String email;
 private String phone;

 // Default constructor
 public AddressDTO() {}

 // Constructor with fields
 public AddressDTO(long id, String name, String email, String phone) {
	 this.id = id;
     this.name = name;
     this.email = email;
     this.phone = phone;
 }

 // Getters and Setters
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public String getPhone() {
     return phone;
 }

 public void setPhone(String phone) {
     this.phone = phone;
 }
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 
}