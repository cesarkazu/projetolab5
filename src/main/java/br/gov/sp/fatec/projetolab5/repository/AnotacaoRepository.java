package br.gov.sp.fatec.projetolab5.repository;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
  public List<Anotacao> findByTextoContains(String texto);
}
