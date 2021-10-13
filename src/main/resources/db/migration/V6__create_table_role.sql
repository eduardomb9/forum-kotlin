CREATE TABLE role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome varchar(50) not null,
    PRIMARY KEY(id)
);

INSERT INTO role VALUES (1, 'LEITURA-ESCRITA');