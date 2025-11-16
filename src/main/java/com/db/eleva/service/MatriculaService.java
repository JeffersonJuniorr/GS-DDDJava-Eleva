package com.db.eleva.service;

import com.db.eleva.dto.MatriculaStatusUpdateDTO;
import com.db.eleva.exception.ResourceNotFoundException;
import com.db.eleva.model.Matricula;
import com.db.eleva.model.TrilhaDeAprendizagem;
import com.db.eleva.model.Usuario;
import com.db.eleva.repository.MatriculaRepository;
import com.db.eleva.repository.TrilhaDeAprendizagemRepository;
import com.db.eleva.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    public Matricula findById(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrado com id: " + id));
    }

    @Transactional
    public Matricula save(Matricula matricula) {

        Usuario usuario = usuarioRepository.findById(matricula.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrado com id: " + matricula.getUsuario().getId()));

        TrilhaDeAprendizagem trilha = trilhaRepository.findById(matricula.getTrilha().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com id: " + matricula.getTrilha().getId()));

        matricula.setUsuario(usuario);
        matricula.setTrilha(trilha);

        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula update(Long id, Matricula matriculaDetails) {
        Matricula matricula = findById(id);

        if (matriculaDetails.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(matriculaDetails.getUsuario().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrado com id: "
                            + matriculaDetails.getUsuario().getId()));
            matricula.setUsuario(usuario);
        }

        if (matriculaDetails.getTrilha() != null) {
            TrilhaDeAprendizagem trilha = trilhaRepository.findById(matriculaDetails.getTrilha().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com id: "
                            + matriculaDetails.getTrilha().getId()));
            matricula.setTrilha(trilha);
        }
        matricula.setStatus(matriculaDetails.getStatus());
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula updateStatus(Long id, MatriculaStatusUpdateDTO statusDTO) {
        Matricula matricula = findById(id);
        matricula.setStatus(statusDTO.getStatus());
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public void delete(Long id) {
        Matricula matricula = findById(id);
        matriculaRepository.delete(matricula);
    }
}
