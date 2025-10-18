package com.uniremington.proyectoFinalTiendaRopa.repository;

import com.uniremington.proyectoFinalTiendaRopa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{}