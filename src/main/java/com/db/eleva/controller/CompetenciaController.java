package com.db.eleva.controller;

import com.db.eleva.dto.CompetenciaListDTO;
import com.db.eleva.model.Competencia;
import com.db.eleva.service.CompetenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth/competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping
    public List<CompetenciaListDTO> getAllCompetencias() {
        return competenciaService.findAll().stream()
                .map(CompetenciaListDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaListDTO> getCompetenciaById(@PathVariable Long id) {
        Competencia competencia = competenciaService.findById(id);
        return ResponseEntity.ok(new CompetenciaListDTO(competencia));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CompetenciaListDTO> createCompetencia(@Valid @RequestBody Competencia competencia) {
        Competencia novaCompetencia = competenciaService.save(competencia);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaCompetencia.getId()).toUri();
        return ResponseEntity.created(location).body(new CompetenciaListDTO(novaCompetencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaListDTO> updateCompetencia(@PathVariable Long id, @Valid @RequestBody Competencia competenciaDetails) {
        Competencia updatedCompetencia = competenciaService.update(id, competenciaDetails);
        return ResponseEntity.ok(new CompetenciaListDTO(updatedCompetencia));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompetencia(@PathVariable Long id) {
        competenciaService.delete(id);
    }
}
