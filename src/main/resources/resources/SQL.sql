/* 
 * Para usar o sistema é necessário iniciar o servidor 
 * Java DB em Serviços (NetNeans)
 *
 * Criar o banco de dados agenda 
 * com o usuário agenda_u e a senha agenda_p
 *
 * Depois rodar estes scripts para a criação da tabela
 */

CREATE TABLE contato (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nome VARCHAR(150) NOT NULL,
    data_nasc DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
);