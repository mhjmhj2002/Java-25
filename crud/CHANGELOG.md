# Changelog

Todas as mudanças relevantes deste projeto serão documentadas neste
arquivo.

## [Unreleased]

### Planned

- Evoluir o `README.md` para um padrao open source/enterprise na release `1.0.0`.
- Consolidar na documentacao: badges, visao de arquitetura, estrutura completa,
  guias local/Docker, exemplos de API, roadmap visual e convencoes do projeto.
- Incluir secao de direcionamento para CI/CD e observabilidade como parte da
  estrategia de maturidade do projeto.

## \[0.7.0\] - 2026-06-27

### Added

-   Pipeline de CI com GitHub Actions em `.github/workflows/ci.yml`
-   Gatilhos de execucao em `push` e `pull_request` para `main`
-   Setup de Java 25 (Temurin) com cache Maven no workflow

### Changed

-   Automacao da validacao de testes Maven via `mvn -B clean test` na pipeline

------------------------------------------------------------------------

## \[0.6.0\] - 2026-06-27

### Added

-   Dockerfile multi-stage
-   Arquivo .dockerignore
-   Aplicação integrada ao Docker Compose
-   Configuração do datasource por variáveis de ambiente
-   Execução como usuário não-root

### Changed

-   Atualização do README
-   Roadmap atualizado (Testcontainers movido para 0.9.0)

------------------------------------------------------------------------

## \[0.5.0\] - 2026-06-27

### Added

-   Testes REST utilizando MockMvc

### Improved

-   Organização da suíte de testes

------------------------------------------------------------------------

## \[0.4.0\] - 2026-06-27

### Added

-   Testes unitários com JUnit 5 e Mockito

------------------------------------------------------------------------

## \[0.3.0\] - 2026-06-26

### Added

-   OpenAPI / Swagger

------------------------------------------------------------------------

## \[0.2.0\] - 2026-06-26

### Added

-   JPA Specification

------------------------------------------------------------------------

## \[0.1.0\] - 2026-06-25

### Added

-   Bootstrap
-   Java 25
-   Spring Boot 4
-   PostgreSQL
-   Flyway
-   MapStruct
-   CRUD inicial
