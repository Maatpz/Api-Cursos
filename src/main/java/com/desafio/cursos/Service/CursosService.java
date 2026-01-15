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
    public Cursos criarCurso(Cursos curso, String name, String category, boolean active, String professor) {
        curso.setName(name);
        curso.setCategory(category);
        curso.setActive(active);
        curso.setProfessor(professor);
        return cursosRepository.save(curso);
    }

    // get
    public List<Cursos> listarCursos() {
        return cursosRepository.findAll();
    }

    public List<Cursos> listarCursosPorNameandCategory(String name, String category) {

    if (name != null && category != null) {
        return cursosRepository
            .findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category);
    } 
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

            if (cursoAtualizado.getName() != null) {
                cursoExistente.setName(cursoAtualizado.getName());
            }
            if (cursoAtualizado.getCategory() != null) {
                cursoExistente.setCategory(cursoAtualizado.getCategory());
            }
            if (cursoAtualizado.getActive() != null) {
                cursoExistente.setActive(cursoAtualizado.getActive());
            }
            if (cursoAtualizado.getProfessor() != null) {
                cursoExistente.setProfessor(cursoAtualizado.getProfessor());
            }
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

    //patch

    public Cursos atualizarStatusCurso(UUID id, boolean active) {
        Cursos cursoExistente = obterCursoPorId(id);
        if (cursoExistente != null) {
            cursoExistente.setActive(active);
            return cursosRepository.save(cursoExistente);
        }
        return null;
    }

}
