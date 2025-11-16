#  Global Solution 2025 - API ELEVA-Skills

**API RESTful para a Plataforma de Upskilling/Reskilling "ELEVA"**

O projeto é a infraestrutura de backend para uma plataforma focada no Futuro do Trabalho, conectando pessoas de baixa renda a oportunidades de requalificação (Reskilling) e aperfeiçoamento (Upskilling).

---

##  1. Problema e Solução

### O Problema: O Futuro do Trabalho e a Desigualdade

O futuro do trabalho, impulsionado por IA, automação e análise de dados, está criando uma lacuna de habilidades. Milhões de empregos correm risco, enquanto novas funções de alta qualificação surgem. Este cenário ameaça aprofundar a desigualdade social, pois a requalificação de qualidade muitas vezes não é acessível a populações de baixa renda, que ficam presas em subempregos.

### A Solução: API "ELEVA-Skills"

A "ELEVA-Skills" é a API que serve como pilar central para a plataforma **ELEVA**. Ela foi desenhada usando princípios de Domain-Driven Design para criar um ecossistema de aprendizado robusto.

A API gerencia as entidades centrais do domínio:

1.  **Usuarios:** Os profissionais (foco em baixa renda) que buscam se requalificar.
2.  **TrilhasDeAprendizagem:** Os caminhos de estudo focados nas competências do futuro.
3.  **Competencias:** As habilidades (Técnicas e Comportamentais) que compõem as trilhas.
4.  **Matriculas:** A entidade-chave que conecta um `Usuario` a uma `TrilhaDeAprendizagem`, formalizando a "inscrição" e permitindo o acompanhamento do progresso (Reskilling/Upskilling).

###  Conexão com os ODS

O projeto está intrinsecamente alinhado aos Objetivos de Desenvolvimento Sustentável da ONU:

* **ODS 4 (Educação de Qualidade):** A API é a infraestrutura que permite o gerenciamento e a oferta de `TrilhasDeAprendizagem` acessíveis e de qualidade.
* **ODS 8 (Trabalho Decente):** O objetivo da plataforma é retirar as pessoas de empregos precários, requalificando-as para carreiras tecnológicas mais duradouras, seguras e com melhor remuneração.
* **ODS 9 (Inovação e Infraestrutura):** A própria API é a infraestrutura digital inovadora. Além disso, as trilhas (ex: "IA Ética") preparam talentos para a nova indústria 4.0.
* **ODS 10 (Redução das Desigualdades):** Este é o foco central do "ELEVA". Ao prover uma plataforma acessível, quebramos a barreira de oportunidade e usamos a tecnologia como um elevador social.

---

##  2. Tecnologias e Arquitetura

### Versões e Dependências

* **Java:** 17
* **Spring Boot:** 3.2.x
* **Dependências Principais:**
    * `spring-boot-starter-web`: Para a API RESTful.
    * `spring-boot-starter-data-jpa`: Para persistência de dados.
    * `spring-boot-starter-validation`: Para o Bean Validation.
    * `spring-boot-starter-security`: (Implementado para gerenciar CORS e CSRF).
    * `h2database`: Banco de dados relacional em memória.
    * `lombok`: Para reduzir o boilerplate (getters, setters).

###  Arquitetura

O projeto segue rigorosamente uma arquitetura em camadas para separação de responsabilidades:

1.  **Controller:** Camada mais externa, responsável por receber requisições HTTP, validar dados de entrada (DTOs) e retornar respostas HTTP (com DTOs).
2.  **Service:** Camada de negócio. Contém toda a lógica, regras e orquestração. É transacional (`@Transactional`) e lança exceções de negócio (ex: `ResourceNotFoundException`).
3.  **Repository:** Camada de acesso a dados. Interfaces que estendem `JpaRepository` para comunicação com o banco.

### Padrões Adotados

* **DTO (Data Transfer Object):** Utilizamos DTOs em **todas** as respostas de `Controller` para:
    1.  **Evitar Loops Infinitos** de serialização JSON (corrigidos com `@JsonIdentityInfo` inicialmente, mas DTOs são a solução mais limpa).
    2.  **Resolver o Problema N+1:** Nossos endpoints de lista (`GET /recurso`) agora fazem apenas 1 query, pois não carregam entidades filhas desnecessariamente.
    3.  **Segurança:** Nunca expomos a Entidade do banco de dados diretamente na API.
* **@RestControllerAdvice:** Um `GlobalExceptionHandler` centraliza o tratamento de exceções (como `404 Not Found` e `400 Bad Request` de validação).
* **Spring Security:** Foi adicionada uma configuração de segurança (`SecurityConfig`) para **desabilitar o CSRF** (`.csrf(csrf -> csrf.disable())`), permitindo que os verbos `PUT`, `POST` e `DELETE` funcionem corretamente em um contexto de API RESTful.

---

##  3. Como Executar Localmente

### Pré-requisitos

* Java JDK 17
* Apache Maven 3.8
* Git

### Passos para Execução

1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/JeffersonJuniorr/GS-DDDJava-Eleva
    cd GS-DDDJava-Eleva
    ```

2.  **Compilar e instalar dependências:**
    ```bash
    mvn clean install
    ```

3.  **Executar a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

4.  **Acesso:**
    A API estará disponível na porta padrão: `http://localhost:8080`.

---

##  4. Configuração do Banco (H2)

* **Tipo:** Banco de dados H2 (Relacional, em memória).
* **Criação do Banco:** As tabelas são criadas automaticamente pelo Hibernate (`spring.jpa.hibernate.ddl-auto=create`).
* **População (Seeds):** O banco é populado com dados iniciais (Usuários, Trilhas, Competências e Matrículas) através do arquivo:
  `src/main/resources/data.sql`
* **Acesso ao H2 Console:**
  Enquanto a aplicação estiver rodando, acesse o console de admin do H2 em:
    * **URL:** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:elevadb`
    * **Username:** `sa`
    * **Password:** `123`

---

##  5. Guia da API (Endpoints)

Testes podem ser feitos via Postman ou Insomnia (os endpoinst podem ser encontrados diretamente no arquivo enviado em conjunto com o projeto com o nome `GS - ELEVA-Skills.postman_collection.json`).

**Variável de Base:** `{{baseUrl}} = http://localhost:8080/auth`.

###  1. Usuários

`Endpoint: {{baseUrl}}/usuarios`

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/` | Cria um novo usuário. |
| `GET` | `/` | Lista todos os usuários (DTO simples). |
| `GET` | `/{id}` | Busca um usuário por ID (DTO). |
| `PUT` | `/{id}` | Atualiza nome, email e área de atuação. |
| `DELETE` | `/{id}` | Deleta um usuário (e suas matrículas, via `Cascade`). |

**`POST /usuarios` (Body):**
```json
{
  "nome": "Carla Mendes",
  "email": "carla.mendes@email.com",
  "areaAtuacao": "Ajudante Geral"
}
```
**`POST /usuarios` (Resposta 201 - DTO):**
```json
{
    "id": 4,
    "nome": "Carla Mendes",
    "email": "carla.mendes@email.com",
    "areaAtuacao": "Ajudante Geral",
    "dataCadastro": "2025-11-16"
}
```

---

###  2. Trilhas de Aprendizagem

`Endpoint: {{baseUrl}}/trilhas`

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/` | Cria uma nova trilha (associando competências). |
| `GET` | `/` | Lista todas as trilhas (DTO simples). |
| `GET` | `/{id}` | Busca uma trilha por ID (DTO). |
| `PUT` | `/{id}` | Atualiza os dados de uma trilha. |
| `DELETE` | `/{id}` | Deleta uma trilha (quebra a relação N:N). |

**`POST /trilhas` (Body):**
```json
{
  "nome": "Mestre em Figma para UX Inclusivo",
  "descricao": "Aprenda a criar interfaces que sejam acessíveis a todos.",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 30,
  "focoPrincipal": "UX/UI e ODS 10",
  "competencias": [
      { "id": 4 },
      { "id": 5 }
  ]
}
```

---

###  3. Competências

`Endpoint: {{baseUrl}}/competencias`

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/` | Cria uma nova competência. |
| `GET` | `/` | Lista todas as competências (DTO simples). |
| `GET` | `/{id}` | Busca uma competência por ID (DTO). |
| `PUT` | `/{id}` | Atualiza uma competência. |
| `DELETE` | `/{id}` | Deleta uma competência (quebra a relação N:N). |

**`POST /competencias` (Body):**
```json
{
  "nome": "Pensamento Crítico",
  "categoria": "Comportamental",
  "descricao": "Analisar problemas de forma lógica"
}
```

---

###  4. Matrículas (Inscrições)

`Endpoint: {{baseUrl}}/matriculas`

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/` | **Inscreve (matricula) um usuário em uma trilha.** |
| `GET` | `/` | Lista todas as matrículas (DTO com nomes). |
| `GET` | `/{id}` | Busca uma matrícula por ID (DTO). |
| `PUT` | `/{id}` | **Atualiza o status** de uma matrícula (Ex: CONCLUIDA). |
| `DELETE` | `/{id}` | Cancela (deleta) uma matrícula. |

**`POST /matriculas` (Body - Inscrever-se):**
```json
{
  "usuario": { "id": 1 },
  "trilha": { "id": 2 }
}
```

**`PUT /matriculas/{id}` (Body - Atualizar Status):**
```json
{
  "status": "CONCLUIDA"
}
```
**`GET /matriculas` (Resposta - DTO de Lista):**
```json
[
    {
        "id": 1,
        "dataInscricao": "2025-11-10",
        "status": "CURSANDO",
        "usuarioId": 1,
        "usuarioNome": "Maria Silva",
        "trilhaId": 1,
        "trilhaNome": "Analista de Dados para Impacto Social"
    }
]
```

##  Integrantes

* **Gabriel Caetano Cordeiro Wanderley** - RM 557582
* **Jefferson Junior Alvarez Moya** - RM 558497
* **Leonardo Pasquini Baldaia** - RM 557416
