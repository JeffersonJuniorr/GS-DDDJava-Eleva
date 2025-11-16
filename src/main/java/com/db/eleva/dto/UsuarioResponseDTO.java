package com.db.eleva.dto;

import com.db.eleva.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String areaAtuacao;
    private LocalDate dataCadastro;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.areaAtuacao = usuario.getAreaAtuacao();
        this.dataCadastro = usuario.getDataCadastro();
    }
}
