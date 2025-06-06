package com.lilly.biblioteca.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lilly.biblioteca.model.Libro;
import com.lilly.biblioteca.repository.LibroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // 1. Obtener todos los libros
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // 2. Obtener uno por Id
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Crear
    @PostMapping
    public ResponseEntity<Libro> createLibro(@Valid @RequestBody Libro libro) {
        Libro savedLibro = libroRepository.save(libro);
        return new ResponseEntity<>(savedLibro, HttpStatus.CREATED);
    }

    // 4. Actualizar completo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @Valid @RequestBody Libro libroDetalles) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroDetalles.getTitulo());
            libro.setAutor(libroDetalles.getAutor());
            libro.setIsbn(libroDetalles.getIsbn());
            Libro updatedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(updatedLibro);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. Actualizar parcial (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Libro> partialUpdateLibro(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return libroRepository.findById(id).map(libro -> {
            if (updates.containsKey("titulo")) {
                libro.setTitulo((String) updates.get("titulo"));
            }
            if (updates.containsKey("autor")) {
                libro.setAutor((String) updates.get("autor"));
            }
            if (updates.containsKey("isbn")) {
                libro.setIsbn((String) updates.get("isbn"));
            }
            Libro updatedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(updatedLibro);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 6. Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        return libroRepository.findById(id).map(libro -> {
            libroRepository.delete(libro);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
