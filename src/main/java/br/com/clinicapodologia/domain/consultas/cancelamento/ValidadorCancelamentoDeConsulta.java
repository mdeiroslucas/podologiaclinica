package br.com.clinicapodologia.domain.consultas.cancelamento;

import br.com.clinicapodologia.domain.consultas.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
