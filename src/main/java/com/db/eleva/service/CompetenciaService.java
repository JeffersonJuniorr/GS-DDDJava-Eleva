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
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    public List<Competencia> findAll() {
        return competenciaRepository.findAll();
    }

    public Competencia findById(Long id) {
        return competenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrado com id: " + id));
    }

    public Competencia save(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    @Transactional
    public Competencia update(Long id, Competencia competenciaDetails) {
        Competencia competencia = findById(id);

        competencia.setNome(competenciaDetails.getNome());
        competencia.setCategoria(competenciaDetails.getCategoria());
        competencia.setDescricao(competenciaDetails.getDescricao());

        competencia.getTrilhas().clear();
        if (competenciaDetails.getTrilhas() != null) {
            for (TrilhaDeAprendizagem trilhaDetalhe : competenciaDetails.getTrilhas()) {
                TrilhaDeAprendizagem trilha = trilhaRepository.findById(trilhaDetalhe.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com id: " + trilhaDetalhe.getId()));
                competencia.getTrilhas().add(trilha);
            }
        }

        return competenciaRepository.save(competencia);
    }

    @Transactional
    public void delete(Long id) {
        Competencia competencia = findById(id);
        for(TrilhaDeAprendizagem trilha : competencia.getTrilhas()) {
            trilha.getCompetencias().remove(competencia);
        }
        competenciaRepository.delete(competencia);
    }
}
