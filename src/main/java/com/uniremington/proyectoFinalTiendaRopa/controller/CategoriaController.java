package com.uniremington.proyectoFinalTiendaRopa.controller;

import com.uniremington.proyectoFinalTiendaRopa.model.Categoria;
import com.uniremington.proyectoFinalTiendaRopa.repository.CategoriaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController{
    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }
    
    @GetMapping
    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> get(@PathVariable Integer id){
        return categoriaRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Categoria categoria){
        if(categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre del atributo obligatorio.");
        }

        categoriaRepository.save(categoria);

        return ResponseEntity.ok("La categoría fue creada correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        if(categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()){
            return ResponseEntity.badRequest().body("El nombre del atributo obligatorio.");
        }

        return categoriaRepository.findById(id).map(existing -> {
            existing.setNombre(categoria.getNombre());
            existing.setEstado(categoria.getEstado());
            categoriaRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        return categoriaRepository.findById(id).map(existing -> {
            categoriaRepository.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
