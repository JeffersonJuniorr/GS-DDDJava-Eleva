package com.db.eleva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "competencias")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da competência é obrigatório")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    private String descricao;

    @ManyToMany(mappedBy = "competencias")
//    @JsonBackReference(value = "trilha-competencias")
    private Set<TrilhaDeAprendizagem> trilhas = new HashSet<>();

}
