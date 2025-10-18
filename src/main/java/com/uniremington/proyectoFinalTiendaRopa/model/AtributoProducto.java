package com.uniremington.proyectoFinalTiendaRopa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atributos_producto")
public class AtributoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String atributo;
    private int producto;

    @Column(name = "caracteristica_atributo")
    private String caracteristicaAtributo;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto productoId;

    public AtributoProducto() {
    }
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getCaracteristicaAtributo() {
        return caracteristicaAtributo;
    }

    public void setCaracteristicaAtributo(String caracteristicaAtributo) {
        this.caracteristicaAtributo = caracteristicaAtributo;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Producto getProductoId() {
        return productoId;
    }
    
    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }    
}
