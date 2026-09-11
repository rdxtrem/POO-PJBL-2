# NOTAS — Referência Técnica do Sistema Semeando (Gestão Escolar)

> Consultar quando faltar contexto e antes de escrever código.
> Não contém senhas nem dados confidenciais.

---

## 1. Ambiente e Como Ligar
| Peça | Versão | Porta / Onde |
|---|---|---|
| **Linguagem** | Java OpenJDK 25 (LTS) | Local (release target 21 para compatibilidade) |
| **Framework** | Spring Boot 3.3.4 | Porta `8080` (configurável via `.env`) |
| **Banco de Dados** | MySQL 8.0 (Docker) / H2 em memória para testes | Porta `3306` (`semeando_db`) |
| **Build Tool** | Apache Maven 3.9.16 | Local (`pom.xml`) |
| **Front-end Web** | Thymeleaf + Bootstrap 5 + JS | Servido em `http://localhost:8080/` |

- **Como ligar tudo com um comando:** `bash start.sh`
- **Validação de rotas (Smoke test):** `bash scripts/smoke.sh`
- **Suíte de testes automatizados:** `mvn test`

---

## 2. Integrações e Credenciais (Sem os valores)
| Serviço | Para quê | Chave no `.env` | Limite de gasto | Plano B se cair |
|---|---|---|---|---|
| **MySQL Local** | Persistência relacional principal | `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` | Grátis (Docker local) | Fallback para banco H2 em memória |
| **Servidor Web** | Porta HTTP | `PORT` | Grátis | Porta padrão 8080 |

---

## 3. Scripts do Projeto
| Script | O que faz | Quando rodar |
|---|---|---|
| `start.sh` | Sobe contêiner MySQL se necessário e inicia a aplicação Spring Boot | Toda vez que for iniciar o sistema |
| `scripts/smoke.sh` | Testa as rotas vivas (`/health`, `/health/db`, `/`) com contagem OK/FALHA | Antes e depois de cada commit |

---

## 4. Banco de Dados
| Tabela | Propósito | Entidade POO |
|---|---|---|
| `perfis` | Papéis de acesso (GESTOR, SECRETARIA, PROFESSOR, ALUNO, etc.) | `Perfil.java` |
| `usuarios` | Credenciais, status e vínculo de perfil | `Usuario.java` |

*Próximas tabelas da Fase 1 (MVP): `responsaveis`, `alunos`, `pre_matriculas`, `matriculas`, `documentos`, `series`, `turmas`, `materias`, `contratos`, `cobrancas`.*

---

## 5. Mapa do Sistema (Lógica → Rota → Tela)
| Módulo | Camada Service | Controller / Rota | Template / View |
|---|---|---|---|
| **Saúde / Monitoramento** | Nativo JDBC DataSource | `HealthController` (`/health`, `/health/db`) | JSON direto |
| **Página Inicial** | — | `HomeController` (`/`) | `templates/index.html` |
| **Usuários & Perfis** | A criar na Fase 1 | `/login`, `/usuarios` | `templates/usuarios/` |

---

## 6. Erros Já Enfrentados
| Data | Sintoma | Causa Real | Lição / Solução |
|---|---|---|---|
| 2026-09-11 | Spring Boot Initializr 400 | Chamada direta da API de geração de zip bloqueada | Configurar diretamente o `pom.xml` mantendo controle total sobre as dependências |

---

## 7. Decisões Técnicas e de Arquitetura
| Data | Decisão | Motivo | Validada por |
|---|---|---|---|
| 2026-09-11 | Adoção do Spring Boot 3 + Maven | Padrão robusto para POO, injeção de dependência e persistência JPA | Rosivaldo |
| 2026-09-11 | Suporte duplo a MySQL (Docker) e H2 (Testes) | Desenvolvimento estável com MySQL real e testes rápidos em memória com H2 | Rosivaldo |
| 2026-09-11 | Arquitetura em 4 camadas (Controller -> Service -> Repository -> Model) | Separação de responsabilidades exigida em projetos acadêmicos e corporativos de POO | Rosivaldo |
