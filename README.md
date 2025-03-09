# Sistema de Conferência

Sistema para gerenciamento de participantes, com controle de pagamento e entrada.

## Requisitos

- Java 17
- PostgreSQL
- Maven

## Configuração

1. Clone o repositório
2. Crie um banco de dados PostgreSQL chamado `conferencia_db`
3. Copie o arquivo `.env.example` para `.env` e configure as variáveis de ambiente:
   ```
   DB_URL=jdbc:postgresql://localhost:5432/conferencia_db
   DB_USERNAME=seu_usuario
   DB_PASSWORD=sua_senha
   APP_DOMAIN=http://localhost:8080
   SPRING_PROFILES_ACTIVE=development
   ```

## Executando o Projeto

1. Instale as dependências:
   ```bash
   mvn clean install
   ```

2. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

3. Acesse a aplicação em: http://localhost:8080

## Funcionalidades

- Cadastro de pessoas
- Listagem com filtros por nome e status
- Confirmação de entrada com atualização automática do status de pagamento
- Interface responsiva e intuitiva
- Destaque em vermelho para registros não pagos

## Ambiente de Produção

Para ambiente de produção, atualize as seguintes variáveis no arquivo `.env`:

```
APP_DOMAIN=https://seu-dominio.com
SPRING_PROFILES_ACTIVE=production
``` 