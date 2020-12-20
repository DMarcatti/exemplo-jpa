package br.com.marcatti.entity;

import br.com.marcatti.enun.TipoDocumento;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Entity
@Table(name = "pessoa")
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name="id", column=@Column(name = "uuid"))
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "nome", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String nome;

    @Column(name = "email",  unique = true)
    @Size(max = 255)
    private String email;

    @Column(name = "nascimento", columnDefinition = "DATE" , nullable = false)
    private LocalDate nascimento;

    @Column(name = "tipoDocumento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "documento", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String documento;


}
