package br.gov.sp.fatec.projetolab5.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class AnotacaoControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void novaAnotacaoTestOk() throws Exception {
    mvc
      .perform(
        post("/anotacao")
          .content("{\"texto\":\"anotacao mineda\", \"usuario\":{\"id\":1}}")
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").exists());
  }

  //@Test
  public void buscarPeloIdTestOk() throws Exception {
    mvc
      .perform(get("/usuario/{1}", 1L).accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.nome").value("admin"));
    //.andExpect(status().is(500))
  }
}
