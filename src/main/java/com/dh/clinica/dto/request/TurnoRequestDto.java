package com.dh.clinica.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequestDto {
    private Integer pacienteId;
    private Integer odontologoId;
    private String fecha;
}


