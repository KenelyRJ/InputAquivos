package com.apiAquivos.inputAquivos;

import com.apiAquivos.inputAquivos.dto.ArquivoDto;
import com.apiAquivos.inputAquivos.service.ArquivosService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InputAquivosApplicationTests {
/*
	private final ArquivosService arquivosService = new ArquivosService();

	@Test
	void testVrfEspacos() throws Exception {
		//simula um arquivo com conteudo
		System.out.println("Implementando Junit");
		String conteudo = "  Removendo os espaços no final da linha        \n Respeitando qubra de linha\n" +
				"e Mostrando ao usuario      \n    ";
		MockMultipartFile file = new MockMultipartFile("file", "teste.txt", "txt", conteudo.getBytes());
		ArquivosService service = new ArquivosService();
		ArquivoDto dto = service.processArquivo(file);
		//valida que o retorno nao é nulo
		assertNotNull(dto);

// Executa o método
		ArquivoDto resultado = arquivosService.processArquivo(file);

// Verifica se os espaços finais foram removidos
		String esperado = "Removendo os espaços no final da linha\nRespeitando qubra de linha\ne Mostrando ao usuario";

		assertEquals(esperado, resultado);

	}

}
*/
}
