package br.com.clinicapodologia.controller;

import br.com.clinicapodologia.domain.consultas.DadosAgendamentoConsulta;
import br.com.clinicapodologia.domain.consultas.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @PostMapping
    @Transactional
    public ResponseEntity agender(@RequestBody @Valid DadosAgendamentoConsulta dados){
        System.out.println(dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));

    }
}
