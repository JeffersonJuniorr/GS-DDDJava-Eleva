package com.db.eleva.dto;

import com.db.eleva.model.Competencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetenciaListDTO {

    private Long id;
    private String nome;
    private String categoria;
    private String descricao;

    public CompetenciaListDTO(Competencia competencia) {
        this.id = competencia.getId();
        this.nome = competencia.getNome();
        this.categoria = competencia.getCategoria();
        this.descricao = competencia.getDescricao();
    }
}
