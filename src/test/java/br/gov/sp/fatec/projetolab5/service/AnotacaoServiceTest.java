package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AnotacaoServiceTest {

  @Autowired
  private AnotacaoService service;

  @MockBean
  private AnotacaoRepository anotacaoRepo;

  @BeforeEach
  public void setUp() {
    //mock usuario
    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setNome("teste usuario");
    usuario.setSenha("senha usuario");

    //mock anotação 1
    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    //mock anotação 2
    Anotacao anotacao2 = new Anotacao();
    anotacao2.setId(2L);
    anotacao2.setTexto("asd");
    anotacao2.setDataHora(new Date());
    anotacao2.setUsuario(usuario);

    //mock lista de todas anotações
    List<Anotacao> todasAnotacoes = new ArrayList<Anotacao>();
    todasAnotacoes.add(anotacao);
    todasAnotacoes.add(anotacao2);

    //mock lista de uma anotação
    List<Anotacao> umaAnotacao = new ArrayList<Anotacao>();
    umaAnotacao.add(anotacao);

    Mockito.when(anotacaoRepo.findAll()).thenReturn(todasAnotacoes);
    Mockito.when(anotacaoRepo.findByTextoContains(any())).thenReturn(umaAnotacao);
    Mockito.when(anotacaoRepo.save(any())).thenReturn(anotacao);
  }

  @Test
  public void buscaAnotacoesTestOkTextoNull() {
    assertEquals(2, service.buscaAnotacoes(null).size());
  }

  @Test
  public void buscaAnotacoesTestOkTextoVazio() {
    assertEquals(2, service.buscaAnotacoes("").size());
  }

  @Test
  public void buscaAnotacoesTestOkComTexto() {
    assertEquals(1, service.buscaAnotacoes("teste").size());
  }

  @Test
  public void novaAnotacaoTestOkTextoNull() {
    Usuario usuario = new Usuario();
    usuario.setId(1L);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto(null);
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    assertThrows(
      AnotacaoException.class,
      () -> {
        service.novaAnotacao(anotacao);
      }
    );
  }

  @Test
  public void novaAnotacaoTestOkTextoVazio() {
    Usuario usuario = new Usuario();
    usuario.setId(1L);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    assertThrows(
      AnotacaoException.class,
      () -> {
        service.novaAnotacao(anotacao);
      }
    );
  }

  @Test
  public void novaAnotacaoTestOkUsuarioNulo() {
    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(null);

    assertThrows(
      AnotacaoException.class,
      () -> {
        service.novaAnotacao(anotacao);
      }
    );
  }

  @Test
  public void novaAnotacaoTestOkUsuarioIdNulo() {
    Usuario usuario = new Usuario();
    usuario.setId(null);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    assertThrows(
      AnotacaoException.class,
      () -> {
        service.novaAnotacao(anotacao);
      }
    );
  }

  @Test
  public void novaAnotacaoTestOkUsuarioNaoEncontrado() {
    Usuario usuario = new Usuario();
    usuario.setId(9999L);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    assertThrows(
      AnotacaoException.class,
      () -> {
        service.novaAnotacao(anotacao);
      }
    );
  }

  @Test
  public void novaAnotacaoTestOkDataHoraNula() {
    Usuario usuario = new Usuario();
    usuario.setId(1L);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(null);
    anotacao.setUsuario(usuario);

    Anotacao anotacaoRetornada = service.novaAnotacao(anotacao);
    assertNotNull(anotacaoRetornada.getDataHora());
  }

  @Test
  public void novaAnotacaoTestOk() {
    Usuario usuario = new Usuario();
    usuario.setId(1L);

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(null);
    anotacao.setUsuario(usuario);

    assertEquals("teste anotacao", service.novaAnotacao(anotacao).getTexto());
  }
}
