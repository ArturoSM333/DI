package com.example.myvideogames.models;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private String titulo;
    private String descripcion;
    private String imagen;

    public Game(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Game() {
        // Deja este constructor vacío, ya que es requerido por Firebase
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(titulo, game.titulo) &&
                Objects.equals(descripcion, game.descripcion) &&
                Objects.equals(imagen, game.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descripcion, imagen);
    }
}
