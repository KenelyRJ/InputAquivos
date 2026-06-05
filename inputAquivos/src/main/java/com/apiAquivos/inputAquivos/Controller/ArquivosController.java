package com.apiAquivos.inputAquivos.Controller;
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
        try {
            String conteudo = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
//remove os espaços
            return conteudo.replaceAll("\\s+", "");
        } catch (Exception e) {
            return "Erro ao processar o arquivo" + e.getMessage();

        }
    }

    }


