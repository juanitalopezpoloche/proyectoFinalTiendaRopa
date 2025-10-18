package com.uniremington.proyectoFinalTiendaRopa.controller;

import com.uniremington.proyectoFinalTiendaRopa.model.AtributoProducto;
import com.uniremington.proyectoFinalTiendaRopa.model.Categoria;
import com.uniremington.proyectoFinalTiendaRopa.model.Producto;
import com.uniremington.proyectoFinalTiendaRopa.repository.AtributoProductoRepository;
import com.uniremington.proyectoFinalTiendaRopa.repository.ProductoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atributos_producto")
public class AtributoProductoController{
    private final AtributoProductoRepository atributoProductoRepository;
    private final ProductoRepository productoRepository;

    public AtributoProductoController(AtributoProductoRepository atributoProductoRepository, ProductoRepository productoRepository){
        this.atributoProductoRepository = atributoProductoRepository;
        this.productoRepository = productoRepository;
    }
    
    @GetMapping
    public List<AtributoProducto> list(){
        return atributoProductoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtributoProducto> get(@PathVariable Integer id){
        return atributoProductoRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AtributoProducto atributoProducto){
        if(atributoProducto.getAtributo() == null || atributoProducto.getAtributo().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El atributo obligatorio.");
        }

        if(atributoProducto.getCaracteristicaAtributo() == null || atributoProducto.getCaracteristicaAtributo().trim().isEmpty()){
            return ResponseEntity.badRequest().body("La caracteristica del atributo obligatoria.");
        }

        if(atributoProducto.getProducto() <= 0){
            return ResponseEntity.badRequest().body("El id del producto no es válido.");
        }
    
        Producto producto = productoRepository.findById(atributoProducto.getProducto()).orElse(null);
    
        if(producto == null){
            return ResponseEntity.badRequest().body("Error: el producto ingresado no existe.");
        }

        atributoProducto.setProductoId(producto);
        atributoProductoRepository.save(atributoProducto);
    
        return ResponseEntity.ok("El atributo del producto fue creado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AtributoProducto atributoProducto){
        if(!atributoProductoRepository.existsById(id)){
            return ResponseEntity.badRequest().body("Error: El atributo del producto con ID " + id + " no existe.");
        }
        
        if(atributoProducto.getAtributo() == null || atributoProducto.getAtributo().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre del atributo obligatorio.");
        }

        if(atributoProducto.getProducto() <= 0){
            return ResponseEntity.badRequest().body("El id del producto no es válido.");
        }
    
        Producto producto = productoRepository.findById(atributoProducto.getProducto()).orElse(null);
    
        if(producto == null){
            return ResponseEntity.badRequest().body("Error: el producto ingresado no existe.");
        }

        return atributoProductoRepository.findById(id).map(existing -> {
            existing.setProductoId(producto);
            existing.setAtributo(atributoProducto.getAtributo());
            existing.setCaracteristicaAtributo(atributoProducto.getCaracteristicaAtributo());
            atributoProductoRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return atributoProductoRepository.findById(id).map(existing -> {
            atributoProductoRepository.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}