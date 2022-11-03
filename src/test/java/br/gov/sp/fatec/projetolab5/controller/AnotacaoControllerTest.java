package br.gov.sp.fatec.projetolab5.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.service.AnotacaoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AnotacaoController.class)
public class AnotacaoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private AnotacaoService service;

  @Test
  public void buscaAnotacoesTestOk() throws Exception {
    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setNome("teste usuario");
    usuario.setSenha("senha usuario");

    Anotacao anotacao = new Anotacao();
    anotacao.setId(1L);
    anotacao.setTexto("teste anotacao");
    anotacao.setDataHora(new Date());
    anotacao.setUsuario(usuario);

    List<Anotacao> anotacoes = new ArrayList<Anotacao>();
    anotacoes.add(anotacao);

    Mockito.when(service.buscaAnotacoes("teste")).thenReturn(anotacoes);

    mvc
      .perform(get("/anotacao/{1}", "teste").contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").value(1L));
  }
}
