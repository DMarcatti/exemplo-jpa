package br.com.marcatti.dto;

import br.com.marcatti.enun.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;

    @Size(max = 255)
    private String email;

    @NotNull(message  = "está Null")
    private LocalDate nascimento;

    @NotNull(message  = "está Null")
    private TipoDocumento tipoDocumento;

    @NotNull(message  = "está Null")
    @Size(max = 255)
    private String documento;
}
