package com.desafio.cursos.Repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cursos.model.Cursos;

public interface CursosRepository extends JpaRepository <Cursos, UUID> {
    
    List<Cursos> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(
        String name, String category);
}
