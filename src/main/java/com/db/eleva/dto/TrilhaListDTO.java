package com.db.eleva.dto;

import com.db.eleva.model.Nivel;
import com.db.eleva.model.TrilhaDeAprendizagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrilhaListDTO {

    private Long id;
    private String nome;
    private Nivel nivel;
    private Integer cargaHoraria;
    private String focoPrincipal;

    public TrilhaListDTO(TrilhaDeAprendizagem trilha) {
        this.id = trilha.getId();
        this.nome = trilha.getNome();
        this.nivel = trilha.getNivel();
        this.cargaHoraria = trilha.getCargaHoraria();
        this.focoPrincipal = trilha.getFocoPrincipal();
    }
}
