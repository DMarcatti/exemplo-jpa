package br.com.marcatti.vo;

import br.com.marcatti.enun.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaVo {

    private UUID uuid;

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

