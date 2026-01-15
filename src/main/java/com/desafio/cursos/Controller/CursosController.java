package com.desafio.cursos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.desafio.cursos.model.Cursos;

import com.desafio.cursos.Service.CursosService;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    
    @Autowired
    private CursosService cursoService;


    @PostMapping("/cursos/")
    public ResponseEntity<Cursos> criarCurso(@Valid @RequestBody Cursos curso) {
        Cursos novoCurso = cursoService.criarCurso( );
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    //Get


    //Put


    //Delete
}
