package br.com.clinicapodologia.domain.consultas.cancelamento;

import br.com.clinicapodologia.domain.consultas.ConsultaRepository;
import br.com.clinicapodologia.domain.consultas.DadosCancelamentoConsulta;
import br.com.clinicapodologia.domain.consultas.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.clinicapodologia.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;


    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var horaConsulta = consulta.getData();
        var horaAtual = LocalDateTime.now();
        var horasDeAntecedencia = Duration.between(horaAtual, horaConsulta).toHours();

        if (horasDeAntecedencia < 24) {
            throw new ValidacaoException("Consulta só poderá ser cancelada com antecedência mínima de 24 horas!");
        }

    }
}


