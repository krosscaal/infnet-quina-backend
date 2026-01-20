# Krossby Jogo Quina Backend

Este projeto é uma API REST desenvolvida para o gerenciamento (CRUD) de jogadores de Quina, funcionando como o back-end da aplicação.



## 🚀 Funcionalidades

*   **CRUD de Jogadores:** Gerenciamento completo de informações dos jogadores.
*   **Integração com API Externa:** O sistema consome uma API externa para:
    *   Obter os dados do último jogo apurado da Quina.
    *   Consultar resultados de concursos específicos através do número do jogo.
*   **Segurança:** Implementação robusta de autenticação e autorização.
*   **Gestão de Usuários:** Cadastro e login de usuários para acesso ao sistema.

## 🛠️ Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3**
*   **Spring Data JPA:** Para persistência de dados.
*   **Spring MVC:** Estrutura para criação da API REST.
*   **Spring Security:** Configuração de segurança e controle de acesso.
*   **JWT (JSON Web Token):** Utilizado para autenticação stateless através de tokens.
*   **OpenFeign (FeignClient):** Utilizado para o consumo simplificado e declarativo da API externa de loterias da Caixa.
*   **Lombok:** Para redução de código boilerplate.
*   **Jakarta EE:** Uso de especificações modernas com o namespace `jakarta.*`.


## 💻 Projeto Front-end Relacionado

Este back-end foi desenvolvido para servir à seguinte aplicação front-end:
*   **Repositório Front-end:** [infnet-quina-frontend](https://github.com/krosscaal/infnet-quina-frontend)


## 🔐 Segurança

A segurança é tratada via **Spring Security** com uma arquitetura baseada em **Tokens JWT**.
*   As requisições (exceto login e registro) exigem um token válido no cabeçalho `Authorization`.
*   Diferentes níveis de acesso baseados em roles (`ROLE_ADMIN`, `ROLE_USER`).
*   As senhas são criptografadas utilizando `BCryptPasswordEncoder`.

## 📡 Consumo de API Externa

A aplicação utiliza o **FeignClient** para se comunicar com a API de loterias. A interface `ApiLoteriaCaixaQuinaFeignClient` define os endpoints para buscar informações atualizadas diretamente dos resultados oficiais da Quina.
// ... existing code ...
A aplicação utiliza o **FeignClient** para se comunicar com a API de loterias. A interface `ApiLoteriaCaixaQuinaFeignClient` define os endpoints para buscar informações atualizadas diretamente dos resultados oficiais da Quina.

## ⚙️ Instalação e Execução

### Pré-requisitos
*   Java 21 instalado.
*   Maven 3.x instalado (ou utilizar o `mvnw` incluso).
*   Git para clonar o repositório.

### Passo a passo

1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/krosscaal/infnet-quina-backend.git
    acesse a pasta clonada
    ```

2.  **Configurar variáveis de ambiente:**
    Certifique-se de verificar ou configurar as propriedades necessárias no arquivo `src/main/resources/application.properties`, especialmente as URLs de APIs externas e o segredo do JWT:
    *   `api.caixa.url`: URL base da API de loterias.
    *   `api.security.token.secret`: Chave secreta para geração dos tokens JWT.

3.  **Compilar o projeto:**
    ```bash
    ./mvnw clean install
    ```

4.  **Executar a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

A API estará disponível em `http://localhost:8080` (ou na porta configurada). Você também pode acessar o console do banco de dados H2 (se estiver utilizando o perfil de desenvolvimento) em `/h2-console`.