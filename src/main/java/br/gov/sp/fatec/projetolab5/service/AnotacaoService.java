package br.gov.sp.fatec.projetolab5.service;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import java.util.List;

public interface AnotacaoService {
  public Anotacao novaAnotacao(Anotacao anotacao);

  public List<Anotacao> buscaAnotacoes(String texto);
}
