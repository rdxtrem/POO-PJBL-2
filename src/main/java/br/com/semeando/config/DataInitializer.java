package br.com.semeando.config;

import br.com.semeando.model.Perfil;
import br.com.semeando.model.Usuario;
import br.com.semeando.repository.PerfilRepository;
import br.com.semeando.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    public DataInitializer(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        // Inicializar Perfis padrão do sistema
        List<String> perfisPadrao = Arrays.asList("GESTOR", "SECRETARIA", "PROFESSOR", "ALUNO", "RESPONSAVEL", "PRESTADOR");
        for (String nomePerfil : perfisPadrao) {
            if (perfilRepository.findByNome(nomePerfil).isEmpty()) {
                perfilRepository.save(new Perfil(nomePerfil, "Acesso padrão para perfil " + nomePerfil));
            }
        }

        // Criar usuário inicial administrador/gestor se não existir
        if (usuarioRepository.findByLogin("admin").isEmpty()) {
            Perfil perfilGestor = perfilRepository.findByNome("GESTOR")
                    .orElseThrow(() -> new IllegalStateException("Perfil GESTOR não encontrado"));
            
            Usuario admin = new Usuario("admin", "admin123", perfilGestor);
            admin.setUltimoAcesso(LocalDateTime.now());
            usuarioRepository.save(admin);
        }
    }
}
