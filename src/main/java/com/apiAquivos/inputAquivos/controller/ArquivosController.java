package com.apiAquivos.inputAquivos.controller;
/*package com.apiAquivos*/

import com.apiAquivos.inputAquivos.dto.ArquivoDto;
import com.apiAquivos.inputAquivos.service.ArquivosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Arquivos")
//@CrossOrigin(origins = "http://localhost:3000")
public class ArquivosController {

    private final ArquivosService arquivosService;

    public ArquivosController(ArquivosService arquivosService) {
        this.arquivosService = arquivosService;
    }

    @PostMapping("/arquivos")
    @Operation(summary = "Upload de arquivos xml ou txt", description = "Recebe e processa os arquivos via multipart")
    public ArquivoDto processaArquivo(@RequestPart("file") MultipartFile file) {
        return arquivosService.processaArquivo(file);
    }

    public String validaArquiv(MultipartFile file) {
        return arquivosService.validarArquivo(file.getOriginalFilename());
    }

    @GetMapping(value = "/arquivos/mostrar",produces = "text/plain")
    @Operation(summary = "Mostrar conteúdo do arquivo", description = "Retorna o conteudo do ultimo arquivo enviado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conteudo retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum arquivo encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")})
    public String listarUltimoArquivo() {
        return arquivosService.listarUltimoArquivo();

    }
}





