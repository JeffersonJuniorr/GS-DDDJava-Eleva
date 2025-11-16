package com.db.eleva.controller;

import com.db.eleva.dto.MatriculaListDTO;
import com.db.eleva.dto.MatriculaStatusUpdateDTO;
import com.db.eleva.model.Matricula;
import com.db.eleva.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public List<MatriculaListDTO> getAllMatriculas() {
        return matriculaService.findAll().stream()
                .map(MatriculaListDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaListDTO> getMatriculaById(@PathVariable Long id) {
        Matricula matricula = matriculaService.findById(id);
        return ResponseEntity.ok(new MatriculaListDTO(matricula));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaListDTO> createMatricula(@Valid @RequestBody Matricula matricula) {
        Matricula novaMatricula = matriculaService.save(matricula);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaMatricula.getId()).toUri();
        return ResponseEntity.created(location).body(new MatriculaListDTO(novaMatricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaListDTO> updateMatriculaStatus(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaStatusUpdateDTO statusDTO) {

        Matricula matriculaAtualizada = matriculaService.updateStatus(id, statusDTO);
        return ResponseEntity.ok(new MatriculaListDTO(matriculaAtualizada));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatricula(@PathVariable Long id) {
        matriculaService.delete(id);
    }
}
