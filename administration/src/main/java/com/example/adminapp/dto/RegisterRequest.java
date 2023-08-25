package com.example.adminapp.dto;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String phoneNumber;
    private String email;
    private Boolean isAccountConfirmed;
    private String password;
    private String location;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName,
                           String lastName,
                           String username,
                           String role,
                           String phoneNumber,
                           String email,
                           Boolean isAccountConfirmed,
                           String password,
                           String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isAccountConfirmed = isAccountConfirmed;
        this.password = password;
        this.location = location;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isAccountConfirmed=" + isAccountConfirmed +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAccountConfirmed() {
        return isAccountConfirmed;
    }

    public void setAccountConfirmed(Boolean accountConfirmed) {
        isAccountConfirmed = accountConfirmed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
