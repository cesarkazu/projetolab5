package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.repository.AutorizacaoRepository;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AutorizacaoServiceTest {

  @Autowired
  private AutorizacaoService service;

  @MockBean
  private AutorizacaoRepository autorizacaoRepo;

  @BeforeEach
  public void setUp() {
    Autorizacao autorizacao = new Autorizacao();
    autorizacao.setId(1L);
    autorizacao.setNome("ROLE_USER");
    Mockito.when(autorizacaoRepo.getByNome(any())).thenReturn(autorizacao);
    Mockito.when(autorizacaoRepo.save(any())).thenReturn(autorizacao);
    //List<Autorizacao> autorizacoes = Arrays.asList(autorizacao);
    List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
    autorizacoes.add(autorizacao);
    Mockito.when(autorizacaoRepo.findAll()).thenReturn(autorizacoes);
  }

  @Test
  public void buscarAutorizacaoPorNomeTestOk() {
    assertEquals(service.buscarPeloNome("ROLE_USER").getNome(), "ROLE_USER");
  }

  @Test
  public void novaAutorizacaoTestNOkNomeNull() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        service.novaAutorizacao(null);
      }
    );
  }

  @Test
  public void novaAutorizacaoTestOk() {
    assertDoesNotThrow(() -> {
      service.novaAutorizacao("ROLE_USER");
    });
  }

  @Test
  public void buscarTodasTestOk() {
    assertEquals(service.buscarTodas().size(), 1);
  }
}
