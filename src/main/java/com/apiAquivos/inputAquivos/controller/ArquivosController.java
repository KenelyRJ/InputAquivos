package com.apiAquivos.inputAquivos.controller;

import com.apiAquivos.inputAquivos.dto.ArquivoDto;
import com.apiAquivos.inputAquivos.service.ArquivosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Arquivos")
public class ArquivosController {

    private final ArquivosService arquivosService;

    public ArquivosController(ArquivosService arquivosService) {
        this.arquivosService = arquivosService;
    }

    @PostMapping("/arquivos")
    public ArquivoDto processaArquivo(@RequestPart("file") MultipartFile file) {
        arquivosService.validarArquivo(file);
        return arquivosService.processaArquivo(file);
    }

    @GetMapping(value = "/arquivos/mostrar",produces = "text/plain")
       public String listarUltimoArquivo() {
        return arquivosService.listarUltimoArquivo();

    }
}





