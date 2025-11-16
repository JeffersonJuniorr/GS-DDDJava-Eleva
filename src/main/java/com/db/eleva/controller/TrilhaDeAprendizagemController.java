package com.db.eleva.controller;

import com.db.eleva.dto.TrilhaListDTO;
import com.db.eleva.model.TrilhaDeAprendizagem;
import com.db.eleva.service.TrilhaDeAprendizagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth/trilhas")
public class TrilhaDeAprendizagemController {

    @Autowired
    private TrilhaDeAprendizagemService trilhasDeAprendizagemService;

    @GetMapping
    public List<TrilhaListDTO> getAllTrilhasDeAprendizagem() {
        return trilhasDeAprendizagemService.findAll().stream()
                .map(TrilhaListDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaListDTO> getTrilhaDeAprendizagemById(@PathVariable Long id) {
        TrilhaDeAprendizagem trilhasDeAprendizagem = trilhasDeAprendizagemService.findById(id);
        return ResponseEntity.ok(new TrilhaListDTO(trilhasDeAprendizagem));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrilhaListDTO> createTrilhaDeAprendizagem(@Valid @RequestBody TrilhaDeAprendizagem trilhasDeAprendizagem) {
        TrilhaDeAprendizagem novaTrilhaDeAprendizagem = trilhasDeAprendizagemService.save(trilhasDeAprendizagem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaTrilhaDeAprendizagem.getId()).toUri();
        return ResponseEntity.created(location).body(new TrilhaListDTO(novaTrilhaDeAprendizagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaListDTO> updateTrilhaDeAprendizagem(@PathVariable Long id, @Valid @RequestBody TrilhaDeAprendizagem trilhasDeAprendizagemDetails) {
        TrilhaDeAprendizagem updatedTrilhaDeAprendizagem = trilhasDeAprendizagemService.update(id, trilhasDeAprendizagemDetails);
        return ResponseEntity.ok(new TrilhaListDTO(updatedTrilhaDeAprendizagem));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrilhaDeAprendizagem(@PathVariable Long id) {
        trilhasDeAprendizagemService.delete(id);
    }
}
