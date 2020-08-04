# Exemplo Serviço utilizando JWT
 Serviço configurado com Spring Security utilizando JWT com Oauth2
 
##Pre-requisitos
* MySQL na versão 8

Executar o script de carga
```
INSERT INTO USER (id, first_name, last_name, email, user_name, PASSWORD) VALUES (1, 'Alex', 'Uemura','test@test.com', 'alex', '$2a$09$/RrZabnjGKXltVZXU7FiDuwgYCFV9ai/ySQMQSRzyZiIpOxjulg5m');
INSERT INTO role (id, NAME, description) VALUES (1, 'ROLE_ADMIN', 'system administrator');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
```
 
##Link para gerar o Bcrypt Hash
[gerador de hash Bcrypt](https://www.devglan.com/online-tools/bcrypt-hash-generator)

## Requisições para teste 
* Requisição POST: http://localhost:8080/oauth/token
```
Basic Auth
- username: application_client_id
- password: application_client_secret

Body
- grant_type: password
- username: alex
- password: 123
```

* Requisição GET: http://localhost:8080/users/user
```
Bearer Token 
- Token: (access_token) retornada pela chamada anterior
```