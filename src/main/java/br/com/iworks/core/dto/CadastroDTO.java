package br.com.iworks.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroDTO {

    private Integer idCadastro;
    private String nome;
    private Integer idade;
}
