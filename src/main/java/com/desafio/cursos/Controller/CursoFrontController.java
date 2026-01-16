package com.desafio.cursos.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.desafio.cursos.model.Cursos;

@Controller
@RequestMapping("/cursos-web")
public class CursoFrontController {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL = "http://localhost:8080/api/cursos";

    @GetMapping("/lista")
    public String listar(Model model) {
        Cursos[] cursos = restTemplate.getForObject(API_URL, Cursos[].class);
        model.addAttribute("cursos", cursos);
        return "cursos-web/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("curso", new Cursos());
        return "cursos-web/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cursos curso) {
        if (curso.getActive() == null) {
            curso.setActive(false);
        }
        restTemplate.postForObject(API_URL + "/", curso, Cursos.class);
        return "redirect:/cursos-web/lista";
    }

    @GetMapping("/detalhes/{id}")
    public String visualizar(@PathVariable UUID id, Model model) {
        Cursos curso = restTemplate.getForObject(API_URL + "/" + id, Cursos.class);
        model.addAttribute("curso", curso);
        return "cursos-web/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable UUID id, Model model) {
        Cursos curso = restTemplate.getForObject(API_URL + "/" + id, Cursos.class);
        model.addAttribute("curso", curso);
        return "cursos-web/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable UUID id, @ModelAttribute Cursos curso) {
        if (curso.getActive() == null) {
            curso.setActive(false);
        }
        HttpEntity<Cursos> request = new HttpEntity<>(curso);
        restTemplate.exchange(API_URL + "/" + id, HttpMethod.PUT, request, Cursos.class);
        return "redirect:/cursos-web/detalhes/" + id;
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        restTemplate.delete(API_URL + "/" + id);
        return "redirect:/cursos-web/lista";
    }

    @PostMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable UUID id) {
        Cursos curso = restTemplate.getForObject(API_URL + "/" + id, Cursos.class);
        if (curso != null) {
            curso.setActive(!curso.getActive());
            HttpEntity<Cursos> request = new HttpEntity<>(curso);
            restTemplate.exchange(API_URL + "/" + id, HttpMethod.PUT, request, Cursos.class);
        }
        return "redirect:/cursos-web/detalhes/" + id;
    }
}
