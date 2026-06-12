package com.apiAquivos.inputAquivos.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;

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

    @NotBlank
    String conteudo;



}

