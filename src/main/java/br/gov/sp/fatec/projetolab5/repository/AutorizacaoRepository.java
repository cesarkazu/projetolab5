package br.gov.sp.fatec.projetolab5.repository;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
  public Autorizacao getByNome(String nome);
}
