package br.com.clinicapodologia.domain.consultas.validacoes;

import br.com.clinicapodologia.domain.consultas.DadosAgendamentoConsulta;
import br.com.clinicapodologia.domain.medico.MedicoRepository;
import br.com.clinicapodologia.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta  {
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }

//        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
//        if (!medicoEstaAtivo){
//            throw new ValidacaoException(("Consulta não pode ser agendada com médico inativo!"));
//        }
    }
}
