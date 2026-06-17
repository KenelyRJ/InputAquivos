package com.apiAquivos.inputAquivos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    String nome;
    @NotBlank
    String tipo;
    @NotEmpty
    String conteudo;




}

