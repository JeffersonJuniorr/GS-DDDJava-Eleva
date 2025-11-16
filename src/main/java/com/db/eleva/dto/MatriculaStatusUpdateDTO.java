package com.db.eleva.dto;

import com.db.eleva.model.StatusMatricula;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaStatusUpdateDTO {

    @NotNull(message = "O status não pode ser nulo")
    private StatusMatricula status;
}
