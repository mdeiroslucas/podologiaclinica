package br.com.clinicapodologia.domain.consultas;

import br.com.clinicapodologia.domain.consultas.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.clinicapodologia.domain.medico.Medico;
import br.com.clinicapodologia.domain.medico.MedicoRepository;
import br.com.clinicapodologia.domain.paciente.PacienteRepository;
import br.com.clinicapodologia.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    public void agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v-> v.validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);


    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados){
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return null;

//        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informada não existe!");
        }

        if (dados.motivo() == null){
            throw new ValidacaoException("É Obrigatório informar o motivo do cancelamento da consulta!");
        }

        var consulta = consultaRepository.findById(dados.idConsulta()).get();

//        if (!podeCancelarConsulta(consulta.getData())) {
//            throw new ValidacaoException("Consulta só poderá ser cancelada com antecedência mínima de 24 horas!");
//        }

        consulta.cancelar(dados.motivo());
    }

    private Boolean podeCancelarConsulta(LocalDateTime horaConsulta){
        var horaAtual = LocalDateTime.now();
        var horasDeAntecedencia = ChronoUnit.HOURS.between(horaAtual, horaConsulta);

        if (horasDeAntecedencia >= 24) return true; else return false;

    }
}
