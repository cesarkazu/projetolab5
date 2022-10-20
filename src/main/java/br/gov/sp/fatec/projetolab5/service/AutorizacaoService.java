package br.gov.sp.fatec.projetolab5.service;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import java.util.List;

public interface AutorizacaoService {
  public Autorizacao buscarPeloNome(String nome);

  public List<Autorizacao> buscarTodas();

  public Autorizacao novaAutorizacao(String nome);
}
