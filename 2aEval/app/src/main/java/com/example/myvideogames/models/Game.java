package com.example.myvideogames.models;

import java.io.Serializable;
import java.util.Objects;

// Clase modelo que representa un videojuego
public class Game implements Serializable {
    private String titulo; // Título del videojuego
    private String descripcion; // Descripción del videojuego
    private String imagen; // URL de la imagen del videojuego

    // Constructor con parámetros para inicializar los atributos
    public Game(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Constructor vacío requerido por Firebase
    public Game() {
    }

    // Getters y Setters
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

    // Método para comparar dos objetos Game basándose en sus atributos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(titulo, game.titulo) &&
                Objects.equals(descripcion, game.descripcion) &&
                Objects.equals(imagen, game.imagen);
    }

    // Método para generar un código hash basado en los atributos
    @Override
    public int hashCode() {
        return Objects.hash(titulo, descripcion, imagen);
    }
}
