package com.example.myvideogames.models;

// Clase modelo que representa un usuario en la aplicación
public class User {
    private String fullName; // Nombre completo del usuario
    private String email; // Correo electrónico del usuario
    private String phone; // Número de teléfono del usuario
    private String address; // Dirección del usuario

    // Constructor para inicializar los atributos del usuario
    public User(String fullName, String email, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters y Setters para acceder y modificar los atributos
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
