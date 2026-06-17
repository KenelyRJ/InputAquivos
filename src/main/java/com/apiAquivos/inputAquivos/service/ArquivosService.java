package com.apiAquivos.inputAquivos.service;
import com.apiAquivos.inputAquivos.dto.ArquivoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ArquivosService {

    private ArquivoDto ultimoArquivo;
    private Path diretorioBkp;
    private Path diretorioDestino;

    public ArquivosService(@Value("${app.upload.dir}") String diretorioConfigurado,
                           @Value("${app.bkp.dir}") String diretorioBkpConfigurado) {
        this.diretorioDestino = Paths.get(diretorioConfigurado);
        this.diretorioBkp = Paths.get(diretorioBkpConfigurado);
    }

    public ArquivoDto processaArquivo(MultipartFile file) {

        try {
            String conteudo = new BufferedReader(new InputStreamReader(file.getInputStream(),
                    StandardCharsets.UTF_8)).lines()
                    .map(linha -> linha.replaceAll("([^<]*?)([^<]*?)\\s*$", "$2"))
                    .filter(linha -> !linha.isEmpty())
                    .collect(Collectors.joining("\n"));

            ultimoArquivo = new ArquivoDto(file.getOriginalFilename(), file.getContentType(), conteudo);

            /// verifica se a pasta existe
            File pasta = new File(diretorioDestino.toString());
            if (!pasta.exists()) {
                boolean criado = pasta.mkdirs();
                //cria a pasta
                if (!criado) {
                    throw new IOException("Não foi possível criar o diretório: " + diretorioDestino);
                }

            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            String nomeOriginal = file.getOriginalFilename();
            String novoNome = timestamp + "_" + nomeOriginal;


            File caminhoPasta = new File(pasta, novoNome);
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(caminhoPasta))) {
                bos.write(conteudo.getBytes(StandardCharsets.UTF_8));
            }

            return ultimoArquivo;

        } catch (IOException e) {
            // trata erros de I/O (arquivo, diretório, escrita)
            throw new RuntimeException("" + "ja existe esse arquivo no diretório", e);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar arquivo", e);
        }
    }

    public void scheduleRemoveArq() throws IOException {
        try (Stream<Path> arquivos = Files.list(diretorioDestino)) {

            arquivos.forEach(arquivo -> {
                try {
                    Path destino = diretorioBkp.resolve( arquivo.getFileName());

                    Files.move(arquivo,destino,StandardCopyOption.REPLACE_EXISTING);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

    public String listarUltimoArquivo() {
        if (ultimoArquivo == null) {
            throw new IllegalStateException("Arquivo nulo");
        }
        return ultimoArquivo.getConteudo();

    }

    public ArquivoDto validarArquivo(MultipartFile file) {
        long maxSize = 10 * 1024 * 1024; // 10MB

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo enviado é inválido (nulo ou vazio)");
        }

        if (file.getSize() > maxSize) {
            throw new RuntimeException ("Arquivo enviado excede o tamanho máximo permitido de 10MB");
        }
        return ultimoArquivo;
    }

}






