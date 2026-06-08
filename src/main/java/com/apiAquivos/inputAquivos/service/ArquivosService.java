package com.apiAquivos.inputAquivos.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class ArquivosService {
    @PostMapping("/arquivos")
    @Operation(summary = "arquivos xml ou txt", description = "metodo para a entrada de arquivos")
    @ApiResponse(responseCode = "200", description = "inserção de arquivos com sucesso")
    public String processaArquivo(@RequestPart("file") MultipartFile file) {
        try {//O processamento do arquivo é lido como UTF-8
            String conteudo = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .map(linha -> linha.replaceAll(">(\\s*)([^<]*?)(\\s*)<", ">$2<"))
                    .collect(Collectors.joining("\n"));

            return conteudo;
        } catch (Exception e) {
            return "Erro ao processar o arquivo" + e.getMessage();


        }
    }
}
