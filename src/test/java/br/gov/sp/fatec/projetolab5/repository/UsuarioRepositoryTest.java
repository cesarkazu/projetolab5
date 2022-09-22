package br.gov.sp.fatec.projetolab5.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepo;

  @Test
  public void novoUsuarioTest() {
    Usuario usuario = new Usuario();
    usuario.setNome("Teste");
    usuario.setSenha("123");
    usuario = usuarioRepo.save(usuario);
    assertNotNull(usuario.getId());
  }

  @Test
  public void novoUsuarioNomeNull() {
    Usuario usuario = new Usuario();
    usuario.setNome(null);
    usuario.setSenha("123");
    assertThrows(
      DataIntegrityViolationException.class,
      () -> {
        usuarioRepo.save(usuario);
      }
    );
  }

  @Test
  public void novoUsuarioSenhaNull() {
    Usuario usuario = new Usuario();
    usuario.setNome("Teste");
    usuario.setSenha(null);
    assertThrows(
      DataIntegrityViolationException.class,
      () -> {
        usuarioRepo.save(usuario);
      }
    );
  }

  @Test
  public void novoUsuarioNomeEmpty() {
    Usuario usuario = new Usuario();
    usuario.setNome("");
    usuario.setSenha("123");
    usuario = usuarioRepo.save(usuario);
    assertNotNull(usuario.getId());
  }

  @Test
  public void novoUsuarioSenhaEmpty() {
    Usuario usuario = new Usuario();
    usuario.setNome("Teste");
    usuario.setSenha("");
    usuario = usuarioRepo.save(usuario);
    assertNotNull(usuario.getId());
  }
}
