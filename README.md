# Eventos-TestAPI

API Rest para Gerenciamento de Eventos
Bem-vindo ao projeto de gestão de eventos. Este projeto é uma API REST desenvolvida para gerenciar eventos e inscrições de usuários. Abaixo, você encontrará detalhes sobre a configuração, execução e operações da API.

#Desafio
O desafio consistiu em desenvolver uma API REST para realizar operações básicas com eventos e usuários, com regras específicas para inscrições e gestão de eventos.
Funcionalidades.

#Pré-requisitos
- Spring Boot como framework.
- Banco de Dados: H2 (ou SQL Server, PostgreSQL, MySQL).
- Java: 8 ou superior.

#Operações Básicas
A API permite realizar as seguintes operações:

#Criar um Evento

- Endpoint: POST /eventos
- Requisição: { "nome": "Nome do Evento", "vagas": 100, "dataHoraInicio": "2024-08-12T10:00:00", "dataHoraFim": "2024-08-12T18:00:00" }
- Resposta: { "id": 1, "nome": "Nome do Evento", "vagas": 100, "dataHoraInicio": "2024-08-12T10:00:00", "dataHoraFim": "2024-08-12T18:00:00" }


#Criar um Usuário

1 - Endpoint: POST /usuarios
2 - Requisição: { "nome": "Nome do Usuário" }
3 - Resposta: { "id": 1, "nome": "Nome do Usuário" }
4 - Inscrever um Usuário em um Evento

- Endpoint: POST /inscricoes
- Requisição: { "usuarioId": 1, "eventoId": 1 }
- Resposta: { "id": 1, "usuario": { "id": 1, "nome": "Nome do Usuário" }, "evento": { "id": 1, "nome": "Nome do Evento" }, "dataInscricao": "2024-08-11T14:00:00" }
- Cancelar uma Inscrição

- Endpoint: DELETE /inscricoes/{id}
- Requisição: N/A
- Resposta: { "message": "Inscrição cancelada com sucesso" }
- Listar Inscrições de um Usuário

- Endpoint: GET /usuarios/{id}/inscricoes
- Resposta: [ { "id": 1, "evento": { "id": 1, "nome": "Nome do Evento" }, "dataInscricao": "2024-08-11T14:00:00" } ]
- Listar Inscritos de um Evento

- Endpoint: GET /eventos/{id}/inscritos
- Resposta: [ { "id": 1, "usuario": { "id": 1, "nome": "Nome do Usuário" }, "dataInscricao": "2024-08-11T14:00:00" } ]
- Realizar Entrada do Usuário no Evento

- Endpoint: POST /inscricoes/{id}/entrada
- Requisição: N/A
- Resposta: { "message": "Entrada registrada com sucesso" }

  #Regras de Inscrição
1. Limite de Vagas: Não é permitido inscrever usuários quando o limite de vagas do evento for atingido.
2. Evento Iniciado: Não é permitido inscrever usuários após o evento ter sido iniciado.
3. Período de Entrada: O usuário só pode entrar no evento no período de uma hora antes do início até o término do evento.
4. Cancelamento: Não é permitido cancelar uma inscrição após o usuário ter realizado a entrada no evento.

  #Diferenciais
- Testes: Foram elaborados testes utilizando JUnit e Mockito para garantir a qualidade do código.

 #Executando o Projeto
*Locamente
1. Clone o repositório:
   ![image](https://github.com/user-attachments/assets/cdac882a-e713-43f0-878e-25537823788d)
   
2. Compile e execute a aplicação:
   ![image](https://github.com/user-attachments/assets/bc81c767-3243-4957-9896-2f9008b05664)

 #Testes
Para executar os testes:

1. Execute os testes com Maven:
   ![image](https://github.com/user-attachments/assets/1c25b664-fd41-4021-aadc-a13a442e215e)

#Contato
Em caso de dúvidas, entre em contato com joaohenri293@gmail.com.




