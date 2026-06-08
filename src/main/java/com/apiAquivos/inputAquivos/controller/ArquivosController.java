package com.apiAquivos.inputAquivos.controller;
/*package com.apiAquivos*/
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@RestController
public class ArquivosController {

    @PostMapping("/arquivos")
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


