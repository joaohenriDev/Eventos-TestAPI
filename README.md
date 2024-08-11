# Eventos-TestAPI

API Rest para Gerenciamento de Eventos
Este projeto é uma API Rest desenvolvida em Java para gerenciar eventos e inscrições de usuários. O objetivo é fornecer operações básicas para a criação de eventos e usuários, bem como gerenciar inscrições e entradas nos eventos, seguindo regras específicas.

Funcionalidades
Criação de Evento:

Permite criar um novo evento com um nome, quantidade de vagas e horários de início e fim.
Criação de Usuário:

Permite criar um novo usuário com um nome.
Inscrição de Usuário em Evento:

Permite inscrever um usuário em um evento, respeitando as regras de disponibilidade e horários.
Cancelamento de Inscrição:

Permite cancelar a inscrição de um usuário em um evento, com verificações para impedir o cancelamento após a entrada no evento.
Listar Inscrições de um Usuário:

Lista todos os eventos em que um usuário está inscrito.
Listar Inscritos de um Evento:

Lista todos os usuários inscritos em um evento.
Entrada do Usuário no Evento:

Permite registrar a entrada do usuário em um evento, dentro do período permitido.
Regras Gerais
Limite de Vagas:

Não é permitido inscrever usuários quando o limite de vagas do evento for atingido.
Inscrição Após Início do Evento:

Não é permitido inscrever usuários após o evento ter iniciado.
Período de Entrada:

O usuário só pode entrar no evento no período de uma hora antes do início até o término do evento.
Cancelamento Após Entrada:

Não é permitido cancelar a inscrição após o usuário ter realizado a entrada no evento.
Requisitos
Java 17 ou superior
Spring Boot 3.x
JPA/Hibernate
Banco de Dados H2 (ou outro banco de dados relacional)
Instruções de Execução
Clonar o Repositório:

bash
Copiar código
git clone https://github.com/seuusuario/seurepositorio.git
cd seurepositorio
Construir o Projeto:

bash
Copiar código
./mvnw clean install
Executar o Projeto:

bash
Copiar código
./mvnw spring-boot:run
Acessar a API:
A API estará disponível em http://localhost:8080. Utilize ferramentas como Postman ou cURL para fazer requisições aos endpoints.

Endpoints
POST /evento: Criação de um novo evento
POST /usuario: Criação de um novo usuário
POST /inscricao: Inscrição de um usuário em um evento
DELETE /usuario/{usuarioId}/evento/{eventoId}: Cancelamento de uma inscrição
GET /usuario/{usuarioId}/inscricoes: Listar inscrições de um usuário
GET /evento/{eventoId}/inscritos: Listar inscritos de um evento
POST /evento/{eventoId}/entrada: Registrar a entrada do usuário no evento
Observações
Este projeto será concluído até o dia 11/08. As funcionalidades podem ser atualizadas conforme o progresso.
