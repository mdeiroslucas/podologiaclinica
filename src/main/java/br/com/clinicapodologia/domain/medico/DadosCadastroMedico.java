package br.com.clinicapodologia.domain.medico;

import br.com.clinicapodologia.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank(message = "O campo nome não pode ser vazio.")
        String nome,
        @NotBlank(message = "O campo email não pode ser vazio.")
        @Email
        String email,

        @NotBlank(message = "O campo telefone não pode ser vazio.")
        String telefone,

        @NotBlank(message = "O campo crm não pode ser vazio.")
        @Pattern(regexp= "\\d{4,6}", message = "Formato do CRM é inválido")
        String crm,

        @NotNull(message = "O campo especialidade não pode ser vazio.")
        Especialidade especialidade,

        @NotNull @Valid
        DadosEndereco endereco
) {
}
