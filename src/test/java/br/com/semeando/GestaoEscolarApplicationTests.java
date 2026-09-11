package br.com.semeando;

import br.com.semeando.model.Perfil;
import br.com.semeando.model.Usuario;
import br.com.semeando.repository.PerfilRepository;
import br.com.semeando.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class GestaoEscolarApplicationTests {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Contexto carrega com sucesso e perfis padrão são criados")
    void contextLoads() {
        assertThat(perfilRepository.count()).isGreaterThanOrEqualTo(5);
        assertThat(perfilRepository.findByNome("GESTOR")).isPresent();
        assertThat(perfilRepository.findByNome("SECRETARIA")).isPresent();
    }

    @Test
    @DisplayName("Usuário admin inicial é criado com perfil GESTOR")
    void adminUserInitialized() {
        assertThat(usuarioRepository.findByLogin("admin")).isPresent();
        Usuario admin = usuarioRepository.findByLogin("admin").get();
        assertThat(admin.getPerfil().getNome()).isEqualTo("GESTOR");
        assertThat(admin.isAtivo()).isTrue();
    }
}
