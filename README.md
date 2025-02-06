# Enterprise System API

API RESTful desenvolvida com o framework Spring Boot para gestão de uma empresa.

## Funcionalidades

- Gerenciamento de dados da empresa
- Operações CRUD (Criar, Ler, Atualizar, Deletar)
- Acesso via API RESTful

## Tecnologias Utilizadas

- **Java**: Linguagem de programação
- **Spring Boot**: Framework para desenvolvimento de aplicações Java
- **Docker**: Containerização da aplicação
- **Banco de Dados**: MySQL

## Dependências Utilizadas

- **Spring Boot Data JPA**: Para gerenciamento de dados e integração com o banco de dados.
- **Spring Boot Web**: Para construir a API RESTful.
- **Spring Boot DevTools**: Para facilitar o desenvolvimento (opcional).
- **Spring Boot Validation**: Para validação de dados.
- **MySQL Connector**: Para conexão com o banco de dados MySQL.
- **Lombok**: Para reduzir a verbosidade do código Java (opcional).
- **Spring Boot Security**: Para gerenciamento de autenticação e autorização.
- **Spring Boot Test**: Para testes automatizados.
- **Spring Security Test**: Para testes de segurança.
- **Java JWT**: Para manipulação de JSON Web Tokens.


## Principais Endpoints

Aqui estão os principais endpoints da API:

### 1. **Gerenciamento de Funcionários**

- **GET** `http://localhost:8080/funcionario`  
  Retorna a lista de todos os funcionários.

- **POST** `http://localhost:8080/funcionario`  
  Cria um novo funcionário.

- **PUT** `http://localhost:8080/funcionario/{id}`  
  Atualiza as informações de um funcionário existente.

- **DELETE** `http://localhost:8080/funcionario/{id}`  
  Remove um funcionário.

### 2. **Gerenciamento de Departamentos**

- **GET** `http://localhost:8080/departamento`  
  Retorna a lista de todos os departamentos.

- **POST** `http://localhost:8080/departamento`  
  Cria um novo departamento.

- **PUT** `http://localhost:8080/departamento/{id}`  
  Atualiza as informações de um departamento existente.

- **DELETE** `http://localhost:8080/departamento/{id}`  
  Remove um departamento.

## Requisitos

- **Docker**: Certifique-se de que o Docker e o Docker Compose estão instalados na sua máquina. Você pode seguir as instruções de instalação no [site oficial do Docker](https://docs.docker.com/get-docker/).

## Instruções para Rodar a API

Siga os passos abaixo para rodar a API localmente usando Docker:

### 1. Clonar o Repositório

Primeiro, clone o repositório:

```bash
git clone https://github.com/vandersonamaral/enterprise-system-api.git
cd enterprise-system-api
```

### 2. Criar e Rodar o Contêiner do Banco de Dados

Inicie o contêiner do MySQL:

```bash
docker-compose up -d db
```

### 3. Gerar o JAR da Aplicação

Certifique-se de que você tenha o Maven instalado. Em seguida, gere o JAR da aplicação:

```bash
./mvnw clean package
```

### 4. Construir e Rodar a API

Agora, construa e inicie a aplicação:

```bash
docker-compose up --build
```

### 5. Acessar a API

Após iniciar os contêineres, você pode acessar sua API através do seguinte endpoint:

```
http://localhost:8080/api
```

## Rodando a Aplicação Localmente (sem Docker)

Caso você queira rodar a aplicação localmente sem Docker, você precisará modificar o arquivo `src/main/resources/application.properties` para apontar para o banco de dados local. 

### Exemplo de Configuração

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/enterprise
spring.datasource.username=Vanderson
spring.datasource.password=root
```

Após fazer essas alterações, você pode executar a aplicação diretamente com o seguinte comando:

```bash
./mvnw spring-boot:run
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
