package br.com.clinicapodologia.domain.consultas;

import br.com.clinicapodologia.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade,
        MotivoCancelamentoConsulta motivoCancelamentoConsulta
) {
}
