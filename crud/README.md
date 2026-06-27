# CRUD Enterprise

Projeto desenvolvido com foco em boas práticas de desenvolvimento utilizando **Java 25** e **Spring Boot 4**.

O objetivo é evoluir um CRUD simples até um projeto com características encontradas em aplicações enterprise, servindo como laboratório de estudos e portfólio técnico.

---

# Tecnologias

- Java 25
- Spring Boot 4.1
- Maven
- Spring Data JPA
- Spring Web MVC
- Bean Validation
- PostgreSQL
- Flyway
- MapStruct
- OpenAPI / Swagger
- JUnit 5
- Mockito
- MockMvc
- Docker

---

# Funcionalidades

## Backend

- CRUD completo de Produtos
- DTOs utilizando Java Record
- Mapeamento com MapStruct
- Validação com Bean Validation
- Tratamento global de exceções
- Paginação
- Ordenação
- Filtros dinâmicos utilizando JPA Specification

## Banco de Dados

- PostgreSQL
- Versionamento utilizando Flyway
- Hibernate configurado em modo Validate

## Documentação

- OpenAPI 3
- Swagger UI

## Testes

- Testes unitários da camada Service
- MockMvc para testes da camada REST

---

# Requisitos

- Java 25
- Maven 3.9+
- Docker
- Docker Compose

Verifique:

```bash
java -version
mvn -v
docker --version
docker compose version
```

---

# Executando o Banco

```bash
docker compose up -d
```

Verifique:

```bash
docker ps
```

Banco:

| Propriedade | Valor |
|-------------|--------|
| Banco | cruddb |
| Usuário | crud |
| Senha | crud |
| Porta | 5432 |

---

# Executando a Aplicação

```bash
mvn clean spring-boot:run
```

---

# Executando os Testes

Todos os testes:

```bash
mvn clean test
```

Apenas testes unitários:

```bash
mvn -Dtest=ProductServiceTest test
```

Apenas testes REST:

```bash
mvn -Dtest=ProductControllerTest test
```

---

# Endpoints

## Criar Produto

```
POST /api/products
```

```json
{
  "name": "Notebook",
  "description": "Dell Latitude",
  "price": 3500
}
```

---

## Buscar Produto

```
GET /api/products/{id}
```

---

## Listar Produtos

```
GET /api/products
```

Paginação:

```
?page=0&size=10
```

Ordenação:

```
?sort=name,asc
```

Filtros:

```
?name=note
```

```
?minPrice=1000
```

```
?maxPrice=5000
```

```
?name=note&minPrice=1000&maxPrice=5000
```

---

## Atualizar Produto

```
PUT /api/products/{id}
```

---

## Remover Produto

```
DELETE /api/products/{id}
```

---

# Documentação

Swagger:

```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

# Estrutura

```
src
├── main
│   ├── java
│   │   └── com.mhj.crud
│   │       ├── config
│   │       ├── exception
│   │       └── product
│   └── resources
│       ├── application.yaml
│       └── db
│           └── migration
└── test
    └── java
        └── com.mhj.crud
            └── product
```

---

# Qualidade

Atualmente o projeto possui:

- Arquitetura em camadas
- DTOs utilizando Java Record
- MapStruct
- Bean Validation
- Exception Handler Global
- Flyway
- PostgreSQL
- Paginação
- Specification
- OpenAPI
- Testes Unitários
- Testes REST com MockMvc

---

# Roadmap

## ✅ 0.1.0

- Bootstrap
- CRUD
- PostgreSQL
- Flyway

## ✅ 0.2.0

- JPA Specification

## ✅ 0.3.0

- OpenAPI / Swagger

## ✅ 0.4.0

- Unit Tests (Mockito)

## ✅ 0.5.0

- Controller Tests (MockMvc)

## ⏳ 0.6.0

- Testcontainers

## ⏳ 0.7.0

- Dockerfile

## ⏳ 0.8.0

- GitHub Actions

## ⏳ 0.9.0

- Micrometer + Spring Actuator

## ⏳ 1.0.0

- Projeto preparado para produção

---

# Versão

**0.5.0**

---

Projeto desenvolvido para estudos avançados em Java e Spring Boot, com foco em arquitetura enterprise e preparação para entrevistas técnicas de nível Sênior / Tech Lead.