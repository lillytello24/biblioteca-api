package com.lilly.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lilly.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
