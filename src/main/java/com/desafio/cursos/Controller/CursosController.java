package com.desafio.cursos.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.desafio.cursos.model.Cursos;

import com.desafio.cursos.Service.CursosService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/cursos")
public class CursosController {
    
    @Autowired
    private CursosService cursoService;


    @PostMapping("/")
    public ResponseEntity<Cursos> criarCurso(@RequestBody @Valid Cursos curso) {
        Cursos novoCurso = cursoService.criarCurso(curso, curso.getName(), curso.getCategory(), curso.getActive(), curso.getProfessor());
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Cursos>> listarCursos() {
        return new ResponseEntity<>(cursoService.listarCursos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursos> obterCursoPorId(@PathVariable("id") UUID id) {
        Cursos curso = cursoService.obterCursoPorId(id);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Cursos>> listarCursosPorNameandCategory(
        @RequestParam String name, 
        @RequestParam String category) {
        return ResponseEntity.ok(
        cursoService.listarCursosPorNameandCategory(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizarCurso(
        @PathVariable("id") UUID id,
        @RequestBody Cursos cursoAtualizado) {
        Cursos curso = cursoService.atualizarCurso(id, cursoAtualizado);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable("id") UUID id) {
        cursoService.deletarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Cursos> ativarCurso(@PathVariable("id") UUID id) {
        Cursos cursoAtivado = cursoService.atualizarStatusCurso(id, true);
        return new ResponseEntity<>(cursoAtivado, HttpStatus.OK);
    }
    
}
