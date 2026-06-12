package com.apiAquivos.inputAquivos.service;

import com.apiAquivos.inputAquivos.dto.ArquivoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class ArquivosService {

    private ArquivoDto ultimoArquivo;
    private String diretorio = "C:\\Users\\kenel\\Documents\\processo";

    public ArquivoDto processaArquivo(MultipartFile file) {

        try {
            String conteudo = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .map(linha -> linha.replaceAll("([^<]*?)([^<]*?)\\s*$", "$2"))
                    .filter(linha -> !linha.isEmpty())
                    .collect(Collectors.joining("\n"));

            ultimoArquivo = new ArquivoDto(file.getOriginalFilename(), file.getContentType(), conteudo);

            /// verifica se a pasta existe
            File pasta = new File(diretorio);
            if (!pasta.exists()) {
                boolean criado = pasta.mkdirs();
                //cria a pasta
                if (!criado) {
                    throw new IOException("Não foi possível criar o diretório: " + diretorio);
                }

            }

            File caminhoExiste = new File(diretorio, file.getOriginalFilename());
            if (caminhoExiste.exists()) {
                throw new IOException("Aquivo ja existe" + caminhoExiste.getAbsolutePath());
            }


            // Define o arquivo de destino dentro da pasta
            //Instancia classe file e passa como parametro os dados processados e criados(pasta)
            File caminhoPasta = new File(pasta, file.getOriginalFilename());
            // Grava o conteúdo processado usando BufferedOutputStream que é melhor que o FileReader
            try (
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(caminhoPasta))) {
                bos.write(conteudo.getBytes(StandardCharsets.UTF_8));
            }
            return ultimoArquivo;

        } catch (IOException e) {
            // trata erros de I/O (arquivo, diretório, escrita)
            throw new RuntimeException("" +
                    "ja existe esse arquivo no diretório", e);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar arquivo", e);
        }
    }

    public String listarUltimoArquivo() {
        if (ultimoArquivo == null) {
            throw new IllegalStateException("Arquivo nulo");
        }
        return ultimoArquivo.getConteudo();

    }

    public String validarArquivo(String file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("arquivo enviado é inválido");
        }
        return "invalido";
    }

}






