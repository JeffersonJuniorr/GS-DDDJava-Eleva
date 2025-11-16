package com.db.eleva.service;

import com.db.eleva.exception.ResourceNotFoundException;
import com.db.eleva.model.Competencia;
import com.db.eleva.model.TrilhaDeAprendizagem;
import com.db.eleva.repository.CompetenciaRepository;
import com.db.eleva.repository.TrilhaDeAprendizagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrilhaDeAprendizagemService {

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaDeAprendizagemRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public List<TrilhaDeAprendizagem> findAll() {
        return trilhaDeAprendizagemRepository.findAll();
    }

    public TrilhaDeAprendizagem findById(Long id) {
        return trilhaDeAprendizagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrado com id: " + id));
    }

    public TrilhaDeAprendizagem save(TrilhaDeAprendizagem trilhaDeAprendizagem) {
        return trilhaDeAprendizagemRepository.save(trilhaDeAprendizagem);
    }

    @Transactional
    public TrilhaDeAprendizagem update(Long id, TrilhaDeAprendizagem trilhaDetails) {
        TrilhaDeAprendizagem trilha = findById(id);

        trilha.setNome(trilhaDetails.getNome());
        trilha.setDescricao(trilhaDetails.getDescricao());
        trilha.setNivel(trilhaDetails.getNivel());
        trilha.setCargaHoraria(trilhaDetails.getCargaHoraria());
        trilha.setFocoPrincipal(trilhaDetails.getFocoPrincipal());
        trilha.getCompetencias().clear();

        if (trilhaDetails.getCompetencias() != null) {
            for (Competencia compDetalhe : trilhaDetails.getCompetencias()) {
                Competencia competencia = competenciaRepository.findById(compDetalhe.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada com id: " + compDetalhe.getId()));
                trilha.getCompetencias().add(competencia);
            }
        }

        return trilhaDeAprendizagemRepository.save(trilha);
    }

    @Transactional
    public void delete(Long id) {
        TrilhaDeAprendizagem trilha = findById(id);
        trilha.getCompetencias().clear();
        trilhaDeAprendizagemRepository.delete(trilha);
    }
}
