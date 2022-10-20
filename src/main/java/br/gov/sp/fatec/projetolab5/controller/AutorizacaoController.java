package br.gov.sp.fatec.projetolab5.controller;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.service.AutorizacaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/autorizacao")
public class AutorizacaoController {

  @Autowired
  private AutorizacaoService autorizacaoService;

  @GetMapping
  public List<Autorizacao> buscarTodas() {
    return autorizacaoService.buscarTodas();
  }

  @GetMapping(value = "/{name}")
  public Autorizacao buscarPeloNome(@PathVariable("name") String nome) {
    return autorizacaoService.buscarPeloNome(nome);
  }

  @PostMapping
  public Autorizacao novaAutorizacao(@RequestBody String nome) {
    return autorizacaoService.novaAutorizacao(nome);
  }
}
