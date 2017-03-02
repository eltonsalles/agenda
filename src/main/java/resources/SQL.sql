/* 
 * Para usar o sistema é necessário iniciar o servidor 
 * Java DB em Serviços (NetNeans)
 *
 * Criar o banco de dados agenda 
 * com o usuário agenda_u e a senha agenda_p
 *
 * Depois rodar estes scripts para a criação das tabelas
 */

CREATE TABLE contato (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nome VARCHAR(150) NOT NULL,
    data_nasc DATE,
    criado_em TIMESTAMP,
    alterado_em TIMESTAMP
);

CREATE TABLE email (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    email VARCHAR(255) NOT NULL,
    contato_id INTEGER NOT NULL REFERENCES contato(id)
);

CREATE TABLE telefone (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ddi VARCHAR(3) NOT NULL,
    ddd VARCHAR(4) NOT NULL,
    numero VARCHAR(15) NOT NULL,
    tipo VARCHAR(12) NOT NULL,
    contato_id INTEGER NOT NULL REFERENCES contato(id)
);