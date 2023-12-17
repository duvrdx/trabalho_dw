## Rodando Backend
```sh

docker-compose up --build -d 

```

O banco PostgreSQL rodará na porta 5432 e o Backend estará acessivel via 8080.

O banco poderá ser carregado com alguns tipos já cadastrados para facilitar o Teste, para povoar faça os seguintes passos:

```sh
docker cp inserts.sql postgres_dw:inserts.sql
docker exec -it postgres_dw /bin/bash
psql -U postgres -d postgres -a -f inserts.sql
```

Caso queira executar o fluxo completo temos duas opçoes:

- 1. Utilizar a API (via Postman ou Swagger)
- 2. Seguir o seguinte fluxo no FrontEnd (Cadastrar somente os tipos)
    - Cadastrar Tipo de Épico na tela de Épicos
    - Cadastrar Tipo de História de Usuário e vincular ao Tipo de Épico
    - Cadastrar Tipo de Tarefa e vincular ao Tipo de História de Usuário

## Rodando Frontend
```sh
cd frontend
docker build -t frontend .
docker run -p 3000:3000 frontend

```