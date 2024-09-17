# Mircoserviço

Este projeto é cria um microsserviço user que por meio de um broker envia uma mensagem assíncrona e não bloqueante para outro microserviço de envio de e-mails. 

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação utilizada no desenvolvimento do serviço.
- **Spring Boot**: Framework para criação de aplicações Java, facilitando a configuração e a construção de serviços web.
- **Spring Mail**: Módulo do Spring para enviar e-mails de forma simplificada.
- **RabbitMQ**: Sistema de mensageria para lidar com a fila de e-mails, garantindo alta disponibilidade e processamento assíncrono.
- **CloudAMQO**: Serviço de hospedagem gerenciado do RabbitMQ na nuvem, utilizado para escalar o sistema de mensageria.
- **Docker**: Utilizado para containerização da aplicação, garantindo portabilidade e facilidade de deploy.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar logs, templates de e-mails e informações de status de envio.
- **Maven**: Gerenciador de dependências e build automation.
- **Postman**: Ferramenta utilizada para testar e documentar a API de envio de e-mails.

## Principais Funcionalidades

- **Envio de E-mails**: A principal função do microsserviço é gerenciar o envio de e-mails transacionais e de notificação, através de filas (RabbitMQ/CloudAMQP), garantindo que sejam processados de forma assíncrona e eficiente.
  
- **Fila de Mensagens**: Utiliza RabbitMQ (local ou via CloudAMQP) para lidar com o processamento em massa de e-mails, escalando horizontalmente conforme a demanda.

- **Armazenamento de Dados**: Usa PostgreSQL para armazenar logs de envio, templates de e-mails e informações de status, garantindo persistência e rastreamento detalhado.
- **Envio via SMTP do Gmail**: O microsserviço utiliza o SMTP do Gmail como servidor de e-mail para o envio das mensagens.
- **Template de E-mails**: Suporte para uso de templates personalizáveis em HTML para envio de e-mails.

## Como Executar

### Pré-requisitos
- Docker e Docker Compose instalados.
- Java 21 instalado.
- PostgreSQL (local ou via container).
- RabbitMQ (local ou via CloudAMQP).
- Conta de e-mail Gmail configurada para envio via SMTP.
- Postman para testar a API.
### Configuração do SMTP do Gmail

Para utilizar o Gmail como servidor SMTP, adicione as seguintes configurações no arquivo `application.properties` ou `application.yml`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seu-email@gmail.com
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/TexeiraThiago/EmailMicroService.git
    ```
2. Execute o microsserviço com Docker:
    ```bash
    docker-compose up
    ```
3. Executar o projeto na sua IDE

4. Acessar a aplicação por padrão em http://localhost:8081/users

Exemplo de Requisição POST

Para enviar um e-mail, faça uma requisição POST para o endpoint /send-email com o seguinte corpo:

```json
{
  "to": "email_destino@example.com",
  "subject": "Assunto do E-mail",
  "body": "Corpo da mensagem em texto ou HTML"
}
```

Contribuição

Sinta-se à vontade para abrir issues ou pull requests com sugestões e melhorias.
