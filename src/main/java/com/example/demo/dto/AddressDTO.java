package com.example.demo.dto;

public class AddressDTO {
 private String name;
 private String email;
 private String phone;

 // Default constructor
 public AddressDTO() {}

 // Constructor with fields
 public AddressDTO(String name, String email, String phone) {
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
}