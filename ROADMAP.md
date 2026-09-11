# ROADMAP — Sistema Semeando (Gestão Escolar)

> Ordem única do trabalho. Nasce do REQUISITOS aprovado v1.0; todo item cita o requisito correspondente.
> Status: 🔵 na fila · 🟡 fazendo · ✅ feito · ⏸️ bloqueado · 🔴 urgente (fura a fila)

## Regras
1. Segue a ordem estrita das fases. Uma fase só fecha com todos os itens ✅ e testados.
2. Ideia nova entra em "Ideias novas" com data, sem desviar do plano em andamento.
3. Exceção única: bug crítico ou quebra de compilação vira 🔴 no topo.
4. Antes de marcar ✅, conferir no código em execução e validar com o Rosivaldo.

---

## Fase 0 — Fundação (Etapa 5)
| # | Item | Status | O que o usuário vê |
|---|---|---|---|
| 0.1 | Ferramentas da stack validadas (Java 25 LTS, Maven 3.9, Node) | 🔵 | Comandos respondem sem erro no terminal |
| 0.2 | Configuração de variáveis e `.env` do banco MySQL/H2 | 🔵 | `.env` configurado e seguro fora do Git |
| 0.3 | Inicialização do esqueleto Spring Boot 3 + `start.sh` | 🔵 | Página web inicial abre no navegador |
| 0.4 | Conexão com banco + primeira entidade JPA protegida + `/health` | 🔵 | Endpoint de health retornando status UP |
| 0.5 | Script de verificação contínua `scripts/smoke.sh` + `NOTAS.md` | 🔵 | Smoke test 1/1 passando verde |

---

## Fase 1 — MVP (Etapa 6)
| # | Item | Requisito | Status | O que o usuário vê |
|---|---|---|---|---|
| 1.1 | Gestão de Usuários, Perfis e Autenticação básica | RF-10, RN-08 | 🔵 | Tela de login, permissões por perfil (Gestor, Secretaria, Professor, Aluno) |
| 1.2 | Cadastro de Alunos e Responsáveis com validação anti-duplicidade | RF-01, RN-01 | 🔵 | Formulário com vínculo aluno-responsável e alerta ao repetir documento |
| 1.3 | Ficha de Pré-Matrícula e Fluxo de Aprovação/Rejeição com Auditoria | RF-02, RF-03, RN-02, RN-03 | 🔵 | Painel de solicitações; aprovação pelo gestor com log de auditoria |
| 1.4 | Estrutura de Séries (Maternal ao 5º ano), Turmas e Matérias | RF-08, RN-05 | 🔵 | Grade curricular por ano letivo organizada por série |
| 1.5 | Checklist de Documentos Obrigatórios e Efetivação da Matrícula | RF-04, RF-06, RN-04 | 🔵 | Bloqueio de matrícula com pendência documental e geração do vínculo na turma |
| 1.6 | Geração de Contrato e Módulo Financeiro de Cobranças | RF-07, RF-09, RN-06, RN-07 | 🔵 | Contrato gerado na tela e carnê de mensalidades com status aberto/pago |
| 1.7 | Portal do Aluno e Responsável responsivo | RF-13, RN-05, RN-08 | 🔵 | Acesso do aluno vendo apenas suas matérias e cobranças |
| 1.8 | Teste da Jornada Principal ponta a ponta (`scripts/e2e_jornada.sh`) | REQUISITOS §13 | 🔵 | Teste automatizado e conferência manual dos 6 passos do MVP |

---

## Fase S — Segurança e Publicação (Etapas 7 e 8)
| # | Item | Status | O que o usuário vê |
|---|---|---|---|
| S.1 | Gate de segurança completo (`SEGURANCA.md`) | 🔵 | Checklist de RBAC, senhas hash e proteção `.env` validado |
| S.2 | Empacotamento de produção (JAR executável) e documentação de entrega | 🔵 | `mvn package` sem erros e guia no `README.md` |
| S.3 | Validação final de entrega para avaliação da disciplina de POO | 🔵 | Demonstração funcional das classes POO, camadas e banco |

---

## Fase 2 — Módulos Complementares (Após o MVP)
| # | Item | Requisito | Status | O que o usuário vê |
|---|---|---|---|---|
| 2.1 | Planejamento Pedagógico e Diário de Classe com Frequência | RF-11, RF-12, RN-09 | 🔵 | Professor lançando aulas, conteúdos e presenças |
| 2.2 | Portal de Obrigações Administrativas e Alertas (SMTT / CENSO) | RF-14, RF-15, RN-10 | 🔵 | Painel de controle com prazos e alertas visuais de vencimento |
| 2.3 | Controle de Entrega de Fardas e Livros | RF-05 | 🔵 | Registro de kits de fardamento e livros entregues ao aluno |

---

## Decisões pendentes do usuário
| # | Decisão | Bloqueia |
|---|---|---|
| — | Nenhuma decisão pendente no momento | — |

---

## Ideias novas (caixa de entrada)
- _(vazio)_

---

## Histórico
| Data | Evento |
|---|---|
| 2026-09-11 | Roadmap v1.0 criado e alinhado com REQUISITOS v1.0 aprovado por Rosivaldo |
