## 🏠 MorarAqui - Portal de Soluções Imobiliárias

## 📋 Descrição do Projeto

O MorarAqui é uma plataforma completa para a gestão de anúncios imobiliários. O sistema permite que proprietários e corretores cadastrem imóveis com detalhes técnicos, gerenciem galeria de fotos e alcancem compradores de forma eficiente. O projeto foca em uma arquitetura robusta e escalável, utilizando as melhores práticas de desenvolvimento Java.

## ✅ Principais Funcionalidades

- **Busca Avançada de Imóveis** - Filtros por cidade, bairro, finalidade e tipo de imóvel.

- **Gestão de Anúncios** - Cadastro completo de casas, apartamentos e terrenos.

- **Galeria de Fotos** - Upload de múltiplas imagens com armazenamento organizado e ordenação personalizada.

- **Autenticação Híbrida** - Login tradicional via JWT e Login Social (Google/OAuth2).

- **Localização Inteligente** - Estrutura hierárquica de Estados, Cidades e Bairros para precisão na busca.

- **Segurança** - Proteção de rotas sensíveis e controle de acesso por anunciante.

## 🛠️ Tecnologias Utilizadas

- **Java 17 (LTS)**

- **Spring Boot 3.x**

- **Spring Security (JWT + OAuth2/Google)**

- **Spring Data JPA**

- **MySQL 8.0**

- **Lombok**

- **SpringDoc OpenAPI (Swagger)**

- **Maven**

## 📦 Pré-requisitos**

- **Java 17 ou superior**

- **Maven 3.6+**

- **MySQL 8.0**

- **Git**

## 🚀 Como Executar o Projeto

  - **Clone o Repositório**
  
      git clone https://github.com/seu-usuario/moraraqui.git
      cd moraraqui


 - **Configuração do Banco de Dados**
  
 No arquivo src/main/resources/application.properties, ajuste as credenciais do seu MySQL:

      spring.datasource.url=jdbc:mysql://localhost:3306/moraraqui
      
      spring.datasource.username=seu_usuario
      
      spring.datasource.password=sua_senha

- **Build e Execução**

       mvn clean install
      
       mvn spring-boot:run


- Verificar Status

    A API estará disponível em: http://localhost:8080


## 📚 Documentação da API (Swagger)

O Swagger fornece uma interface interativa para testar todos os endpoints.

👉 Acesse: http://localhost:8080/swagger-ui.html

## 🔐 Como Testar Endpoints Protegidos

Faça login via /login ou OAuth2 para obter o Token JWT.

No Swagger, clique no botão "Authorize".

Insira o token no formato: Bearer SEU_TOKEN_AQUI.

## 🔑 Endpoints Principais

### Imóveis

| Método | Endpoint | Descrição | Auth |
| :--- | :--- | :--- | :---: |
| **GET** | `/imoveis` | Lista todos os imóveis (paginado) | ❌ |
| **GET** | `/imoveis/busca` | Busca avançada com filtros | ❌ |
| **POST** | `/imoveis` | Cadastrar novo imóvel | ✅ |
| **POST** | `/imoveis/{id}/fotos` | Upload de fotos para o imóvel | ✅ |

### Localização

| Método | Endpoint | Descrição | Auth |
| :--- | :--- | :--- | :---: |
| **GET** | `/estados/listar` | Lista todos os estados | ❌ |
| **GET** | `/cidades/estado/{id}` | Lista cidades de um estado | ❌ |
| **GET** | `/bairros/cidade/{id}` | Lista bairros de uma cidade | ❌ |
## 📂 Estrutura do Projeto

src/main/java/com/moraraqui/api/

├── controller/      # Endpoints da API

├── domain/          # Entidades JPA e Regras de Negócio

├── dto/             # Objetos de Transferência de Dados

├── infra/           # Segurança, Swagger e Configurações

├── repository/      # Interfaces de acesso ao banco

└── service/         # Lógica de serviço e upload de fotos

## ⚙️ Configurações Adicionais

Diretório de Fotos: Por padrão, as fotos são salvas na pasta C:/moraraqui/uploads. Certifique-se de que a pasta existe ou altere o caminho no application.properties.

OAuth2: Para o login com Google funcionar, configure seu client-id e client-secret no console do Google Cloud.

## 📝 Licença

Projeto desenvolvido por Anderson Cardim para fins de portfólio.
















