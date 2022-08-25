package br.gov.sp.fatec.projetolab5.repository;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
