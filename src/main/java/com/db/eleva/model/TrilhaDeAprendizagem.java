package com.db.eleva.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "trilhas")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class TrilhaDeAprendizagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da trilha é obrigatório")
    @Size(min = 5, max = 150)
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O nível é obrigatório")
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @NotNull(message = "A carga horária é obrigatória")
    @Min(value = 1, message = "A carga horária deve ser de no mínimo 1 hora")
    private Integer cargaHoraria;

    @NotBlank(message = "O foco principal é obrigatório")
    private String focoPrincipal;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trilha_competencia",
            joinColumns = @JoinColumn(name = "trilha_id"),
            inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )

//    @JsonManagedReference(value = "trilha-competencias")
    private Set<Competencia> competencias = new HashSet<>();

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference(value = "trilha-matriculas")
    private Set<Matricula> matriculas = new HashSet<>();

}
