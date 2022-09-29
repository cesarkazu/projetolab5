package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SegurancaServiceTest {

  @Autowired
  private SegurancaService service;

  @MockBean
  private UsuarioRepository usuarioRepo;

  @BeforeEach
  public void setUp() {
    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setNome("Teste");
    usuario.setSenha("Senha");
    Optional<Usuario> usuarioOp = Optional.of(usuario);
    Mockito.when(usuarioRepo.findById(any())).thenReturn(usuarioOp);
    Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);
    List<Usuario> usuarios = Arrays.asList(usuario);
    Mockito.when(usuarioRepo.findAll()).thenReturn(usuarios);
  }

  @Test
  public void buscarUsuarioPorIdTestOk() {
    assertEquals(service.buscarUsuarioPorId(1L).getNome(), "Teste");
  }

  @Test
  public void novoUsuarioTestNOkNomeNull() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        service.novoUsuario(null, "Senha");
      }
    );
  }

  @Test
  public void novoUsuarioTestOk() {
    assertDoesNotThrow(() -> {
      service.novoUsuario("Teste", "Senha");
    });
  }

  @Test
  public void todosUsuariosTestOk() {
    assertEquals(service.todosUsuarios().size(), 1);
  }
}
