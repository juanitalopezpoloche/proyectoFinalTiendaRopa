package com.uniremington.proyectoFinalTiendaRopa.controller;

import com.uniremington.proyectoFinalTiendaRopa.model.Producto;
import com.uniremington.proyectoFinalTiendaRopa.model.Categoria;
import com.uniremington.proyectoFinalTiendaRopa.repository.ProductoRepository;
import com.uniremington.proyectoFinalTiendaRopa.repository.CategoriaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Producto> list(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Integer id){
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto){
        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre es obligatorio.");
        }

        if(producto.getStock() < 0){
            return ResponseEntity.badRequest().body("El stock ingresado no es válido");
        }

        if(producto.getCategoria() <= 0){
            return ResponseEntity.badRequest().body("La categoría válida.");
        }

        if(producto.getPrecio() <= 0){
            return ResponseEntity.badRequest().body("El precio ingresado no es válido");
        }
    
        Categoria categoria = categoriaRepository.findById(producto.getCategoria()).orElse(null);
    
        if(categoria == null){
            return ResponseEntity.badRequest().body("Error: la categoría ingresada no existe.");
        }

        producto.setCategoriaId(categoria);

        Producto saved = productoRepository.save(producto);
    
        return ResponseEntity.ok("Producto creado correctamente con el ID: " + saved.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Producto producto){
        if(!productoRepository.existsById(id)){
            return ResponseEntity.badRequest().body("Error: El producto con ID " + id + " no existe.");
        }

        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre es obligatorio.");
        }

        if(producto.getStock() < 0){
            return ResponseEntity.badRequest().body("El stock ingresado no es válido");
        }

        if(producto.getCategoria() <= 0){
            return ResponseEntity.badRequest().body("La categoría válida.");
        }

        if(producto.getPrecio() <= 0){
            return ResponseEntity.badRequest().body("El precio ingresado no es válido");
        }

        Categoria categoria = categoriaRepository.findById(producto.getCategoria()).orElse(null);

        if(categoria == null){
            return ResponseEntity.badRequest().body("Error: la categoría ingresada no existe.");
        }

        return productoRepository.findById(id).map(existing -> {
            existing.setNombre(producto.getNombre());
            existing.setCategoriaId(categoria);
            existing.setStock(producto.getStock());
            existing.setPrecio(producto.getPrecio());
            existing.setEstado(producto.getEstado());
            productoRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return productoRepository.findById(id).map(existing -> {
            productoRepository.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}