package br.gov.sp.fatec.projetolab5.service;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.repository.AutorizacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

  @Autowired
  private AutorizacaoRepository autorizacaoRepo;

  @Override
  public Autorizacao buscarPeloNome(String nome) {
    return autorizacaoRepo.getByNome(nome);
  }

  @Override
  public List<Autorizacao> buscarTodas() {
    return autorizacaoRepo.findAll();
  }

  @Override
  public Autorizacao novaAutorizacao(String nome) {
    if (nome == null || nome.trim().isEmpty()) {
      throw new IllegalArgumentException("Parâmetros inválidos.");
    }
    Autorizacao autorizacao = new Autorizacao();
    autorizacao.setNome(nome);
    try {
      autorizacaoRepo.save(autorizacao);
    } catch (IllegalArgumentException exception) {
      throw new RuntimeException("Erro ao salvar autorização.", exception);
    }
    return autorizacao;
  }
}
