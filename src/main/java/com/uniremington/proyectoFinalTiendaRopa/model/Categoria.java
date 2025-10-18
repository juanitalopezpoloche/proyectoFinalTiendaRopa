package com.uniremington.proyectoFinalTiendaRopa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private boolean estado;

    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public String nombreEstado() {
        return this.estado ? "Activo" : "Bloqueado";
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
