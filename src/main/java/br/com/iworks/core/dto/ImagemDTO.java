package br.com.iworks.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagemDTO {

    private Integer idCadastro;
    private String url;
}
