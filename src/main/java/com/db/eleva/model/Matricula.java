package com.db.eleva.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "matriculas")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
//    @JsonBackReference(value = "usuario-matriculas")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "trilha_id")
//    @JsonBackReference(value = "trilha-matriculas")
    private TrilhaDeAprendizagem trilha;

//    @NotNull
    private LocalDate dataInscricao;

//    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    @PrePersist
    public void prePersist() {
        dataInscricao = LocalDate.now();
        status = StatusMatricula.CURSANDO;
    }
}
