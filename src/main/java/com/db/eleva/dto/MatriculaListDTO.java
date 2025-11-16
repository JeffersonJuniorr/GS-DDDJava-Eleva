package com.db.eleva.dto;

import com.db.eleva.model.Matricula;
import com.db.eleva.model.StatusMatricula;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatriculaListDTO {

    private Long id;
    private LocalDate dataInscricao;
    private StatusMatricula status;
    private Long usuarioId;
    private String usuarioNome;
    private Long trilhaId;
    private String trilhaNome;

    public MatriculaListDTO(Matricula matricula) {
        this.id = matricula.getId();
        this.dataInscricao = matricula.getDataInscricao();
        this.status = matricula.getStatus();

        if (matricula.getUsuario() != null) {
            this.usuarioId = matricula.getUsuario().getId();
            this.usuarioNome = matricula.getUsuario().getNome();
        }

        if (matricula.getTrilha() != null) {
            this.trilhaId = matricula.getTrilha().getId();
            this.trilhaNome = matricula.getTrilha().getNome();
        }
    }
}
