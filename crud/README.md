# CRUD Enterprise

![Java](https://img.shields.io/badge/Java-25-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?logo=postgresql&logoColor=white)
![License](https://img.shields.io/badge/License-Pending-lightgrey)

Projeto de estudo e portfólio técnico com foco em práticas modernas de backend Java:

- API REST de produtos com Spring Boot 4.1
- Arquitetura em camadas (controller, service, repository)
- Banco relacional com PostgreSQL e versionamento com Flyway
- Testes automatizados (JUnit 5, Mockito, MockMvc)
- Containerização com Docker e Docker Compose

## Stack Tecnológica

- Java 25
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Flyway
- MapStruct
- OpenAPI/Swagger (springdoc)
- JUnit 5, Mockito e MockMvc
- Docker e Docker Compose

## Visao Geral da Arquitetura

Fluxo principal da API:

1. `ProductController` recebe requests HTTP em `/api/products`.
2. `ProductService` aplica regras de negocio e orquestra o caso de uso.
3. `ProductRepository` acessa o banco via Spring Data JPA + Specification.
4. `ProductMapper` converte entidade <-> DTO (request/response).
5. `GlobalExceptionHandler` padroniza respostas de erro.

```text
HTTP Client
   |
   v
ProductController
   |
   v
ProductService
   |            \
   v             v
ProductRepository ProductMapper
   |
   v
PostgreSQL (Flyway)
```

## Estrutura de Diretorios

```text
crud/
|-- CHANGELOG.md
|-- docker-compose.yml
|-- Dockerfile
|-- HELP.md
|-- mvnw
|-- mvnw.cmd
|-- pom.xml
|-- README.md
`-- src/
	|-- main/
	|   |-- java/com/mhj/crud/
	|   |   |-- CrudApplication.java
	|   |   |-- config/
	|   |   |   `-- OpenApiConfig.java
	|   |   |-- exception/
	|   |   |   |-- ApiErrorResponse.java
	|   |   |   |-- GlobalExceptionHandler.java
	|   |   |   `-- ResourceNotFoundException.java
	|   |   `-- product/
	|   |       |-- Product.java
	|   |       |-- ProductController.java
	|   |       |-- ProductFilter.java
	|   |       |-- ProductMapper.java
	|   |       |-- ProductRepository.java
	|   |       |-- ProductRequest.java
	|   |       |-- ProductResponse.java
	|   |       |-- ProductService.java
	|   |       `-- ProductSpecification.java
	|   `-- resources/
	|       |-- application.yaml
	|       `-- db/migration/
	|           `-- V1__create_products.sql
	`-- test/
		`-- java/com/mhj/crud/
			|-- CrudApplicationTests.java
			`-- product/
				|-- ProductControllerTest.java
				`-- ProductServiceTest.java
```

## Guia de Execucao Local

### Pre-requisitos

- Java 25
- Maven 3.9+ (ou Maven Wrapper)
- PostgreSQL 17+ em execucao local

### 1) Subir apenas o banco com Docker (opcional e recomendado)

```bash
docker compose up -d postgres
```

### 2) Executar a aplicacao localmente

```bash
./mvnw clean spring-boot:run
```

### 3) Executar testes

```bash
./mvnw clean test
```

### Endpoints de documentacao

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Guia de Execucao com Docker

### Subir ambiente completo (app + postgres)

```bash
docker compose up -d --build
```

### Acompanhar logs da aplicacao

```bash
docker compose logs -f app
```

### Parar somente a aplicacao

```bash
docker compose stop app
```

### Parar todo o ambiente

```bash
docker compose stop
```

## Exemplos de Requisicoes da API

Base URL: `http://localhost:8080`

### Criar produto

```bash
curl -X POST "http://localhost:8080/api/products" \
  -H "Content-Type: application/json" \
  -d '{
	"name": "Notebook Pro",
	"description": "16GB RAM, 1TB SSD",
	"price": 7999.90
  }'
```

### Listar produtos com paginacao e ordenacao

```bash
curl "http://localhost:8080/api/products?page=0&size=10&sort=price,desc"
```

### Filtrar por nome e faixa de preco

```bash
curl "http://localhost:8080/api/products?name=note&minPrice=1000&maxPrice=9000"
```

### Buscar por ID

```bash
curl "http://localhost:8080/api/products/1"
```

### Atualizar produto

```bash
curl -X PUT "http://localhost:8080/api/products/1" \
  -H "Content-Type: application/json" \
  -d '{
	"name": "Notebook Pro 2026",
	"description": "32GB RAM, 1TB SSD",
	"price": 8999.90
  }'
```

### Remover produto

```bash
curl -X DELETE "http://localhost:8080/api/products/1"
```

## Roadmap Visual

| Versao | Status | Entregas |
|---|---|---|
| 0.1.0 | Concluido | Bootstrap, CRUD, PostgreSQL, Flyway |
| 0.2.0 | Concluido | JPA Specification |
| 0.3.0 | Concluido | OpenAPI / Swagger |
| 0.4.0 | Concluido | Unit Tests (Mockito) |
| 0.5.0 | Concluido | Controller Tests (MockMvc) |
| 0.6.0 | Concluido | Dockerfile + Docker Compose |
| 0.7.0 | Planejado | GitHub Actions (CI) |
| 0.8.0 | Planejado | Micrometer + Actuator |
| 0.9.0 | Planejado | Repository Tests (Testcontainers) |
| 1.0.0 | Planejado | Release production ready + docs enterprise |

## Convencoes do Projeto

- Padrao arquitetural em camadas por dominio (`product`) e responsabilidade.
- DTOs com Java `record` para requests e responses.
- Mapeamento de DTOs com MapStruct.
- Validacao de entrada com Bean Validation.
- Erros de negocio e recursos nao encontrados tratados globalmente.
- Migrações de banco versionadas com Flyway.

## Futuro: CI/CD e Observabilidade

Itens previstos para as proximas versoes:

- Pipeline de CI com GitHub Actions (`build`, `test`, `package`).
- Integracao com quality gates (ex.: SpotBugs, Checkstyle, Sonar).
- Metricas e health checks com Actuator + Micrometer.
- Exportacao de metricas para Prometheus/Grafana.
- Estrategia de release automatizada com versionamento semantico.

## Versao Atual

`0.6.0`
