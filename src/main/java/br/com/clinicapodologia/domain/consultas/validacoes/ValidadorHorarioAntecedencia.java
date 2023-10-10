package br.com.clinicapodologia.domain.consultas.validacoes;

import br.com.clinicapodologia.domain.consultas.DadosAgendamentoConsulta;
import br.com.clinicapodologia.infra.exception.ValidacaoException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public static final int ANTECEDENCIA_MINIMA = 30;

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < ANTECEDENCIA_MINIMA){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
