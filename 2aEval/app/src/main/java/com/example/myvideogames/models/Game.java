package com.example.myvideogames.models;

import java.io.Serializable;

public class Game implements Serializable {
    private String id;
    private String titulo; // Cambiado a 'titulo'
    private String descripcion; // Cambiado a 'descripcion'
    private String imagen; // Cambiado a 'imagen'

    // Constructor vacío (requerido por Firebase)
    public Game() {}

    public Game(String id, String titulo, String descripcion, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
