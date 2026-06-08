package com.apiAquivos.inputAquivos.controller;
/*package com.apiAquivos*/
import com.apiAquivos.inputAquivos.service.ArquivosService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArquivosController {

    private final ArquivosService arquivosService;

    public ArquivosController(ArquivosService arquivosService) {
        this.arquivosService = arquivosService;
    }

    @PostMapping("/arquivos")
    public String processaArquivo(@RequestPart("file") MultipartFile file) {
        return arquivosService.processaArquivo(file);
    }
}


