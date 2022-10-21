package br.gov.sp.fatec.projetolab5.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.service.AutorizacaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioController.class)
public class AutorizacaoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private AutorizacaoService service;

  @Test
  public void novaAutorizacaoTestOk() throws Exception {
    Autorizacao autorizacao = new Autorizacao("ROLE_USER");
    autorizacao.setId(1L);
    Mockito.when(service.novaAutorizacao(any())).thenReturn(autorizacao);

    mvc
      .perform(
        post("/autorizacao").content("ROLE_USER").contentType(MediaType.TEXT_PLAIN_VALUE)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1L));
  }
}
