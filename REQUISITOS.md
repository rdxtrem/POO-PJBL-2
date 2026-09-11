# REQUISITOS — Sistema Semeando (Gestão Escolar)

| Status | ☐ RASCUNHO · ☐ EM REVISÃO · ☒ APROVADO |
|---|---|
| Versão | 1.0 |
| Aprovado por / em | Rosivaldo em 2026-09-11 |

> Documento derivado da especificação do cliente (Poo 2.0.docx). Nenhum código antes de APROVADO.

---

## 1. Visão

| Pergunta | Resposta |
|---|---|
| **Problema (a dor)** | Controles manuais dispersos, risco de perda de documentos obrigatórios, perda de prazos regulatórios (SMTT, Censo), atrasos em cobranças e falta de visibilidade centralizada entre secretaria, professores e responsáveis. |
| **Para quem** | Gestores, secretárias, professores, alunos e pais/responsáveis da instituição de ensino (Educação Infantil e Ensino Fundamental do Maternal ao 5º ano). |
| **Como é resolvido hoje e o que é ruim** | Papéis físicos, anotações descentralizadas e planilhas avulsas, gerando retrabalho, perda de informações e falta de segurança nos dados. |
| **Objetivo** | "Permitir que a escola gerencie de ponta a ponta o ciclo de vida do aluno (da pré-matrícula à gestão pedagógica e financeira) de forma integrada, auditável e segura, sem controles manuais vulneráveis." |
| **Como saberemos que deu certo (número)** | Zero duplicidade de cadastros de alunos, 100% de matrículas com conferência de documentos obrigatórios e redução a zero de perda de prazos de obrigações regulatórias. |

---

## 2. Usuários

| Perfil | Quem é | O que precisa fazer | Quantos |
|---|---|---|---|
| **Administrador / Gestor** | Direção e coordenação geral da escola | Configurar o sistema, gerenciar usuários/perfis, aprovar/rejeitar pré-matrículas, acompanhar indicadores gerais e consultar auditoria. | 2 a 5 |
| **Administrativo / Secretaria** | Equipe de atendimento e secretaria escolar | Cadastrar alunos e responsáveis, conferir documentos, gerar contratos, efetivar matrículas, lançar cobranças e registrar pagamentos. | 2 a 10 |
| **Professor** | Corpo docente da instituição | Acessar suas turmas e componentes curriculares, criar planos de aula e registrar frequência e conteúdo no diário de classe. | 10 a 50 |
| **Aluno / Responsável** | Alunos matriculados e seus pais/tutores legais | Consultar matérias da série, acompanhar notas/frequência, acessar contratos e consultar/baixar cobranças e boletos. | Centenas |
| **Prestador de Serviços** | Parceiros externos ou profissionais de apoio | Acessar tarefas, manutenções ou relatórios específicos autorizados pela direção. | Pontuais |

---

## 3. Escopo

**Faz:**
- Cadastro único de alunos com relacionamento a um ou mais responsáveis.
- Ficha de pré-matrícula com acompanhamento de status (*Pendente, Em análise, Aprovada, Rejeitada, Cancelada*).
- Fluxo formal de aprovação/rejeição com justificativa obrigatória na rejeição e auditoria (usuário + data/hora).
- Efetivação de matrícula vinculada a ano letivo, série/turma e situação financeira da taxa de matrícula.
- Checklist e conferência de documentos obrigatórios por série/etapa antes da conclusão da matrícula.
- Cadastro de séries (Maternal 1 e 2, Infantil 1 e 2, 1º ao 5º ano) e matriz curricular de matérias por ano letivo.
- Geração e controle de contratos com status (*Rascunho, Gerado, Assinado, Vigente, Encerrado, Cancelado*).
- Gestão financeira: geração de cobranças periódicas, controle de vencimentos, multas, juros, descontos e registro de pagamentos.
- Planejamento pedagógico e diário de classe com controle de conteúdo e frequência.
- Portal do Aluno/Responsável responsivo com visão acadêmica e financeira restrita ao seu perfil.
- Portal de Obrigações Administrativas para acompanhamento de prazos legais e institucionais (SMTT, CENSO Escolar, presenças).
- Painel de notificações automáticas de pendências, cobranças e prazos a vencer ou atrasados.
- Controle de acesso rigoroso por perfil (RBAC) e logs de auditoria de operações críticas.

**Não faz (neste MVP inicial):**
- Gateway bancário real em produção para registro de boletos ou cobrança Pix (será mockado/simulado com as regras do Cora).
- Módulo complexo de compras, cotações e controle de almoxarifado de uniformes/livros (apenas registro de entrega e identificação).
- Aplicativo mobile nativo para lojas App Store/Google Play (será Web Responsivo para mobile e desktop).

---

## 4. Funções e Requisitos Funcionais

| ID | História | Prioridade | Critério de Aceite (Dado / Quando / Então) |
|---|---|---|---|
| **RF-01** | Como **Secretaria**, quero cadastrar alunos vinculados a responsáveis, para manter histórico único sem duplicações. | **Essencial** | **Dado** um novo aluno, **quando** cadastrado com CPF/certidão já existente, **então** o sistema bloqueia duplicidade e emite alerta. |
| **RF-02** | Como **Responsável/Secretaria**, quero registrar uma pré-matrícula, para iniciar o processo seletivo de ingresso. | **Essencial** | **Dado** que os dados do candidato foram preenchidos, **quando** enviados, **então** cria solicitação com status *Pendente* e anota data/responsável. |
| **RF-03** | Como **Gestor**, quero aprovar ou rejeitar uma pré-matrícula, para controlar quem entra na instituição. | **Essencial** | **Dado** uma pré-matrícula em análise, **quando** rejeitada sem motivo, **então** o sistema exige justificativa e salva data/hora e o usuário. |
| **RF-04** | Como **Secretaria**, quero efetivar a matrícula de uma pré-matrícula aprovada, para formalizar o vínculo acadêmico. | **Essencial** | **Dado** uma pré-matrícula aprovada, **quando** os documentos e taxa de matrícula forem validados, **então** gera matrícula no ano letivo e série. |
| **RF-05** | Como **Secretaria**, quero registrar a entrega de farda e livros, para manter controle dos materiais dos alunos. | **Desejável** | **Dado** um aluno matriculado, **quando** receber farda ou livros (próprios ou da escola), **então** registra quantidade, tamanho e situação. |
| **RF-06** | Como **Secretaria**, quero conferir a lista de documentos obrigatórios, para não matricular alunos com pendências legais. | **Essencial** | **Dado** um aluno sem documento obrigatório conferido, **quando** tentar concluir matrícula, **então** o sistema bloqueia salvo autorização administrativa expressa. |
| **RF-07** | Como **Secretaria**, quero gerar contratos vinculados ao aluno/responsável, para formalizar a prestação de serviços educacionais. | **Essencial** | **Dado** um aluno com matrícula efetivada, **quando** emitido o contrato da sua etapa (Infantil/Fundamental), **então** gera documento com status *Gerado* e termos preenchidos. |
| **RF-08** | Como **Gestor**, quero cadastrar as séries (Maternal ao 5º ano) e suas matérias, para alimentar a matriz do Portal do Aluno. | **Essencial** | **Dado** um ano letivo, **quando** configurada a grade curricular da série, **então** as matérias ficam visíveis apenas para os alunos daquela série. |
| **RF-09** | Como **Secretaria**, quero gerar cobranças mensais, para controlar a receita e acompanhar inadimplência. | **Essencial** | **Dado** o contrato vigente, **quando** gerada a competência mensal, **então** cria cobrança com vencimento, valor, encargos calculados e status *Aberta*. |
| **RF-10** | Como **Administrador**, quero gerenciar usuários e perfis de acesso, para manter a segurança do sistema. | **Essencial** | **Dado** um usuário desativado, **quando** tentar realizar login, **então** o acesso é sumariamente bloqueado. |
| **RF-11** | Como **Professor**, quero registrar o planejamento pedagógico, para cumprir o calendário letivo. | **Importante** | **Dado** uma matéria sob minha responsabilidade, **quando** cadastrar plano de aula, **então** vincula data, objetivos e conteúdo ministrado. |
| **RF-12** | Como **Professor**, quero preencher o diário de classe e frequência, para registrar a rotina dos alunos. | **Importante** | **Dado** uma aula realizada, **quando** lançar presenças e faltas, **então** os dados ficam gravados e bloqueados contra alterações após o fechamento. |
| **RF-13** | Como **Aluno/Responsável**, quero acessar o portal do aluno, para ver matérias, situação financeira e comunicados. | **Essencial** | **Dado** o login do responsável, **quando** acessar o portal, **então** exibe somente os dados dos alunos vinculados a ele, sem misturar com outros. |
| **RF-14** | Como **Secretaria/Gestão**, quero cadastrar obrigações com prazos, para receber alertas automáticos antes do vencimento. | **Importante** | **Dado** uma obrigação cadastrada (ex.: SMTT, Censo), **quando** atingir a data limite, **então** gera notificação em destaque de atraso. |
| **RF-15** | Como **Gestor**, quero acompanhar as obrigações institucionais (SMTT em março, Censo em junho, controle Cora), para evitar penalidades. | **Importante** | **Dado** o período de controle de cada órgão, **quando** o prazo se aproximar, **então** o sistema emite alerta para o responsável designado. |

---

## 5. Regras de Negócio (RN)

| ID | Regra | Fonte | Fixa ou Flexível? |
|---|---|---|---|
| **RN-01** | Cada aluno deverá possuir um cadastro principal único, sem duplicidade por documento identificador. | Documento do Cliente (§5) | Fixa |
| **RN-02** | Toda pré-matrícula deverá passar obrigatoriamente por análise antes da efetivação. | Documento do Cliente (§5) | Fixa |
| **RN-03** | Toda aprovação ou rejeição deverá registrar obrigatoriamente o usuário e a data/hora da decisão. | Auditoria / Especificação | Fixa |
| **RN-04** | Documentos obrigatórios devem ser conferidos antes de concluir a matrícula (salvo permissão expressa). | Secretaria Escolar | Fixa |
| **RN-05** | As matérias exibidas no Portal do Aluno devem corresponder estritamente à etapa/série e ano letivo do aluno. | Projeto Pedagógico | Fixa |
| **RN-06** | Cobranças devem estar vinculadas ao aluno, responsável financeiro e à respectiva competência (mês/ano). | Financeiro | Fixa |
| **RN-07** | Uma cobrança marcada como *Paga* não pode ser revertida para *Aberta* sem registro formal de estorno com justificativa e autorização. | Integridade Financeira | Fixa |
| **RN-08** | Usuários devem visualizar e operar apenas os dados permitidos pelo seu perfil de acesso (RBAC). | Segurança / LGPD | Fixa |
| **RN-09** | Registros acadêmicos em períodos fechados não podem ser alterados por professores sem autorização da coordenação. | Normas Escolares | Fixa |
| **RN-10** | Obrigações com prazo e cobranças em aberto devem gerar alertas visuais de proximidade de vencimento e destaque de atraso. | Operação Escolar | Fixa |
| **RN-11** | Alterações críticas em matrículas, contratos e financeiro devem ser registradas em tabela de auditoria. | Governança | Fixa |

---

## 6. Como o sistema deve ser (Requisitos Não-Funcionais)

| Tema | Requisito | Como medir |
|---|---|---|
| **Segurança (RNF-01)** | Autenticação segura por senha hash (BCrypt), sessões com timeout e controle rigoroso por perfil. | Nenhuma senha trafegada ou salva em texto plano. |
| **Privacidade / LGPD (RNF-02)** | Dados de menores e responsáveis protegidos contra acessos cruzados ou vazamentos. | Usuário só acessa dados onde possui relação de vínculo autorizada. |
| **Disponibilidade (RNF-03)** | Disponibilidade contínua durante o horário letivo e administrativo. | Sistema operacional e acessível via navegador Web. |
| **Desempenho (RNF-04)** | Tempo de resposta para consultas cotidianas e lançamentos inferior a 2 segundos. | Testes de carga e smoke tests regulares. |
| **Responsividade (RNF-05)** | Layout adaptável a celulares, tablets e desktops (Mobile-First / Bootstrap). | Testado em resoluções de smartphone (375px) a monitores desktop (1920px). |
| **Auditoria (RNF-07)** | Registro com carimbo de data/hora, identificação de usuário e ação para alterações em matrículas e pagamentos. | Tabela `Auditoria` com integridade referencial. |
| **Integridade (RNF-10)** | O sistema impede orfandade de dados e incongruências entre cadastro, matrícula, contrato e cobrança. | Chaves estrangeiras e constraints relacionais no MySQL. |

---

## 7. Dados e Diagrama de Entidades

Baseado no Diagrama de Classes UML extraído da especificação técnica:

1. **`Responsavel`**: `idResponsavel`, `nome`, `contatos`.
2. **`Aluno`**: `idAluno`, `dadosPessoais`, `contato`, `situacao`, `historico`.
3. **`Usuario`**: `idUsuario`, `login`, `senha`, `status`, `ultimoAcesso`.
4. **`Perfil`**: `idPerfil`, `nome`, `permissoes`.
5. **`PreMatricula`**: `idPreMatricula`, `data`, `status` (*StatusPreMatricula*), `observacoes`.
6. **`Matricula`**: `idMatricula`, `anoLetivo`, `etapaSerie`, `situacao`, `data`.
7. **`Documento`**: `idDocumento`, `tipo`, `status` (*StatusDocumento*), `dataRecebimento`, `arquivo`.
8. **`Contrato`**: `idContrato`, `modelo`, `valoresCondicoes`, `status` (*StatusContrato*), `arquivo`.
9. **`Cobranca`**: `idCobranca`, `competencia`, `vencimento`, `valor`, `desconto`, `acrescimos`, `situacao` (*StatusCobranca*), `comprovante`.
10. **`Pagamento`**: `idPagamento`, `data`, `valor`, `situacao`, `comprovante`.
11. **`Turma`**: `idTurma`, `anoLetivo`, `identificacao`.
12. **`Materia`**: `idMateria`, `nome`, `etapaSerie`.
13. **`PlanoDeAula`**: `idPlano`, `data`, `planejamento`.
14. **`DiarioDeClasse`**: `idDiario`, `data`, `conteudo`, `frequencia`, `observacoes`.
15. **`ItemDeFarda`**: `idItem`, `tamanho`, `quantidade`, `situacao`.
16. **`Livro`**: `idLivro`, `nome`, `origem`, `situacaoEntrega`.
17. **`Obrigacao`**: `idObrigacao`, `tipo`, `prazo`, `situacao` (*StatusObrigacao*), `comprovante`.
18. **`Calendario`**: `idCalendario`, `evento`, `data`, `tipo`.
19. **`Notificacao`**: `idNotificacao`, `tipo`, `mensagem`, `lida`, `data`.
20. **`Auditoria`**: `idAuditoria`, `acao`, `dataHora`, `detalhes`.

---

## 8. Integrações

| Serviço | Para quê | Essencial no MVP? | Plano B se cair / simulação |
|---|---|---|---|
| **Emissão de Cobranças / Cora** | Geração e liquidação de boletos | Não (Simulado) | Serviço mock em memória / banco interno de transações. |
| **Armazenamento de Documentos** | Upload de certidões e comprovantes | Sim | Armazenamento local seguro no diretório de uploads do servidor. |
| **Calendário Regulatório (SMTT / Censo)** | Monitorar prazos obrigatórios | Sim | Job agendado interno (Spring Scheduled) verificando vencimentos e gerando notificações. |

---

## 9. Limites

| Tipo | Limite |
|---|---|
| **Contexto** | Projeto acadêmico de Programação Orientada a Objetos (POO-PJBL-2). |
| **Ambiente de Desenvolvimento** | GitHub Codespaces (Linux nativo, OpenJDK 25, Maven, Git). |
| **Custos** | R$ 0,00 (100% código aberto, sem serviços proprietários pagos). |

---

## 10. Stack Tecnológica

| Camada | Escolha | Justificativa |
|---|---|---|
| **Linguagem Back-end** | **Java 25 (OpenJDK)** | Obrigatório na disciplina de POO. Permite demonstrar herança, polimorfismo, encapsulamento e composição. |
| **Framework Back-end** | **Spring Boot 3.x** | Padrão da indústria; provê injeção de dependência, Spring Data JPA para o banco e Spring Security. |
| **Banco de Dados** | **MySQL** | Banco relacional solicitado na especificação do cliente; complementado com H2 para suíte de testes rápidos. |
| **Front-end Web** | **HTML5 + CSS3 (Bootstrap) + JavaScript** | Interface responsiva e limpa para desktop e celular sem complexidade desnecessária de frameworks pesados. |
| **Testes** | **JUnit 5 + AssertJ + Mockito** | Testes unitários para validar regras de negócio e integridade das entidades POO. |

---

## 11. Riscos

| Risco | Chance | Impacto | O que fazer |
|---|---|---|---|
| Escopo muito amplo para o prazo acadêmico | Média | Alto | Fatiar estritamente o desenvolvimento através do MVP em fases bem delimitadas. |
| Complexidade no controle financeiro e estornos | Baixa | Médio | Criar regras de domínio robustas no service layer de `Cobranca` e `Pagamento`. |

---

## 12. Perguntas em aberto

*Todas as perguntas de especificação foram respondidas no documento fornecido (`Poo 2.0.docx`). Nenhuma pergunta bloqueia a fundação e início do MVP.*

---

## 13. MVP (Produto Mínimo Viável)

- **Perfil atendido:** Secretaria Escolar, Gestor e Aluno/Responsável.
- **Jornada Principal (Teste de Fim a Fim Obrigatório):**
  1. Secretaria cadastra Aluno e Responsável (com validação anti-duplicidade).
  2. Responsável ou Secretaria abre uma Pré-Matrícula e anexa documento.
  3. Gestor analisa e aprova a Pré-Matrícula (registrando auditoria).
  4. Secretaria efetiva a Matrícula vinculando à Série/Turma e conferindo documentos.
  5. Sistema gera Contrato e Cobrança da primeira mensalidade/taxa.
  6. Aluno acessa o Portal do Aluno e visualiza sua grade de matérias e o boleto da cobrança gerada.
- **Entra no MVP:** RF-01, RF-02, RF-03, RF-04, RF-06, RF-07, RF-08, RF-09, RF-10, RF-13.
- **Fase seguinte:** Pedagógico (RF-11, RF-12), Obrigações (RF-14, RF-15), Fardas/Livros (RF-05).

---

## 14. Glossário

| Termo | Significado |
|---|---|
| **Pré-Matrícula** | Ficha de intenção de vaga submetida à análise prévia da direção antes da confirmação oficial. |
| **Competência** | Mês/ano de referência de uma obrigação financeira (ex.: `03/2026`). |
| **RBAC** | *Role-Based Access Control* (Controle de Acesso Baseado em Perfis). |
| **SMTT / CENSO** | Obrigações regulatórias do município e do Ministério da Educação com prazos fixos de entrega. |

---

## 15. Versões

| Versão | Data | O que mudou | Aprovado por |
|---|---|---|---|
| 0.1 | 2026-09-11 | Consolidação completa da especificação e diagrama de classes do `Poo 2.0.docx`. | Rosivaldo |
