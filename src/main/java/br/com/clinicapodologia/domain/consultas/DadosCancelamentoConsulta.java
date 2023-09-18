package br.com.clinicapodologia.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamentoConsulta motivo
) {
}
