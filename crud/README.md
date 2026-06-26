# CRUD Enterprise - Java 25 + Spring Boot 4

Projeto de estudo e portfólio com foco em boas práticas modernas para aplicações Java utilizando Spring Boot.

O objetivo deste repositório é evoluir um CRUD simples até um projeto com características encontradas em aplicações enterprise, aplicando arquitetura limpa, boas práticas, testes automatizados, observabilidade, CI/CD e tecnologias utilizadas no mercado.

---

# Tecnologias

- Java 25
- Spring Boot 4.1
- Maven
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Docker Compose
- Flyway
- MapStruct

---

# Funcionalidades da versão 0.1.0

- CRUD completo de Produtos
- DTOs utilizando Java Record
- Mapeamento entre Entity e DTO utilizando MapStruct
- Persistência com Spring Data JPA
- Banco PostgreSQL executando em Docker
- Versionamento do banco utilizando Flyway
- Validação automática do Schema pelo Hibernate
- Paginação e Ordenação utilizando Pageable
- Tratamento global de exceções com @RestControllerAdvice
- Bean Validation para validação das requisições

---

# Requisitos

- Java 25
- Maven 3.9+
- Docker
- Docker Compose

Verifique as versões instaladas:

```bash
java -version
mvn -v
docker --version
docker compose version
```

---

# Subindo o Banco de Dados

Na raiz do projeto execute:

```bash
sudo docker compose up -d
```

Verifique se o container foi iniciado:

```bash
sudo docker ps
```

Configuração do banco:

| Propriedade | Valor |
|-------------|--------|
| Database | cruddb |
| Usuário | crud |
| Senha | crud |
| Porta | 5432 |

---

# Executando a aplicação

```bash
mvn clean spring-boot:run
```

Durante a inicialização o Flyway executará automaticamente as migrations.

Verifique as tabelas criadas:

```bash
sudo docker exec -it crud-postgres psql -U crud -d cruddb -c "\dt"
```

Resultado esperado:

```text
               List of relations
 Schema |         Name
--------+-----------------------
 public | flyway_schema_history
 public | products
```

---

# Endpoints

Base URL

```
http://localhost:8080/api/products
```

## Criar Produto

```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{
  "name":"Notebook",
  "description":"Dell Latitude",
  "price":3500.00
}'
```

---

## Listar Produtos

```bash
curl "http://localhost:8080/api/products?page=0&size=10&sort=name,asc"
```

---

## Buscar Produto

```bash
curl http://localhost:8080/api/products/1
```

---

## Atualizar Produto

```bash
curl -X PUT http://localhost:8080/api/products/1 \
-H "Content-Type: application/json" \
-d '{
  "name":"Notebook Dell",
  "description":"Latitude Atualizado",
  "price":4200.00
}'
```

---

## Remover Produto

```bash
curl -X DELETE http://localhost:8080/api/products/1
```

---

# Exemplo de erro de validação

Requisição:

```json
{
  "name":"",
  "price":0
}
```

Resposta:

```json
{
  "timestamp": "...",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação",
  "details": [
    "name: Nome é obrigatório",
    "price: Preço deve ser maior que zero"
  ]
}
```

---

# Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── com.mhj.crud
│   │       ├── CrudApplication
│   │       ├── exception
│   │       │   ├── ApiErrorResponse
│   │       │   ├── GlobalExceptionHandler
│   │       │   └── ResourceNotFoundException
│   │       └── product
│   │           ├── Product
│   │           ├── ProductController
│   │           ├── ProductMapper
│   │           ├── ProductRepository
│   │           ├── ProductRequest
│   │           ├── ProductResponse
│   │           └── ProductService
│   └── resources
│       ├── application.yaml
│       └── db
│           └── migration
│               └── V1__create_products.sql
```

---

# Banco de Dados

As tabelas são criadas através do Flyway.

Migration atual:

```
src/main/resources/db/migration/V1__create_products.sql
```

O Hibernate está configurado apenas para validar o schema:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: validate
```

Dessa forma toda alteração estrutural do banco deverá ser realizada através de migrations.

---

# Roadmap

## v0.2.0
- Filtros dinâmicos utilizando JPA Specification

## v0.3.0
- OpenAPI / Swagger

## v0.4.0
- Testes Unitários com JUnit e Mockito

## v0.5.0
- Testcontainers

## v0.6.0
- Dockerfile da aplicação

## v0.7.0
- Observabilidade com Micrometer

## v0.8.0
- GitHub Actions (CI)

## v1.0.0
- Projeto preparado para ambiente de produção

---

# Versão Atual

**0.1.0-SNAPSHOT**

---

Desenvolvido como projeto de estudos para aprofundamento em Java, Spring Boot e arquitetura de aplicações enterprise.