package com.desafio.cursos.Service;

import com.desafio.cursos.Repository.CursosRepository;
import com.desafio.cursos.model.Cursos;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursosService {

    @Autowired
    private CursosRepository cursosRepository;


    // Post
    public Cursos criarCurso() {
        Cursos curso = new Cursos();
        return cursosRepository.save(curso);
    }
    
    // get
    public List<Cursos> listarCursos() {
        return cursosRepository.findAll();
    }

    //get by id
    public Cursos obterCursoPorId(UUID id) {
        return cursosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + id));
    }

    // put
    public Cursos atualizarCurso(UUID id, Cursos cursoAtualizado) {
        Cursos cursoExistente = obterCursoPorId(id);
        if (cursoExistente != null) {
            cursoExistente.setName(cursoAtualizado.getName());
            cursoExistente.setCategory(cursoAtualizado.getCategory());
            cursoExistente.setActive(cursoAtualizado.isActive());
            return cursosRepository.save(cursoExistente);
        }
        return null;
    }

    // delete
    public void deletarCurso(UUID id) {
        var cursoExistente = obterCursoPorId(id);
        if (cursoExistente != null) {
            cursosRepository.deleteById(id);
        }
    }
    
}
