CREATE TABLE TBUSUARIOS ( 
    _id          INTEGER         PRIMARY KEY
                                 NOT NULL
                                 UNIQUE,
    nome         VARCHAR( 30 )   NOT NULL,
    sobrenome    VARCHAR( 30 )   NOT NULL,
    apelido      VARCHAR( 50 ),
    email        VARCHAR( 80 )   NOT NULL
                                 UNIQUE,
    senha        VARCHAR( 80 ),
    dtnascimento DATETIME        NOT NULL,
    endereco     VARCHAR( 50 ),
    bairro       VARCHAR( 50 ),
    cidade       VARCHAR( 50 ),
    cep          VARCHAR( 9 ),
    telefone     VARCHAR( 20 ),
    status       VARCHAR( 1 ),
    dtcriacao    DATETIME,
    imagem       VARCHAR( 100 ) 
);

INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (1, 'Vitor Hugo', 'Santos', 'Vito', 'vitor_hs@live.com', 1234, '26/04/1995', 'Rua Mauricio A. Moreno, 41', 'Vila Norte', 'Santa Rita do Passa Quatro', 13670000, '19 933331334', 'A', '16/04/2015', '#d50000');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (2, 'Sebastiao', 'Purcini', 'Seba', 'sebap@gmail.com', 1234, '23/01/1990', 'Rua Agostinho G Pires, 80', 'Jd Dom Pedro', 'Serrana', 14150000, '16 992654939', 'A', '16/04/2015', '#C51162');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (3, 'Nathan', 'Bernardes', 'Elvis', 'nathan@hotmail.com', 1234, '19/01/1995', 'Rua Serafim do Bem, 100', 'Centro', 'Serrana', 14150000, '16 992456789', 'A', '16/04/2015', '#AA00FF');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (4, 'Debora', 'Pelicano', 'Debo', 'deborapd@barao.com', 1234, '10/03/1975', 'Avenida Prof. Joao Fiusa 59', 'Jd Canada', 'Ribeirao Preto', 14150000, '16 998134652', 'A', '16/04/2015', '#6200EA');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (5, 'Ricardo', 'Morelli', 'Morelli', 'morelli.r@msn.com', 1234, '28/05/1944', 'Rua Joao de Bertoli, 586', 'Jd California', 'Ribeirao Preto', 14020229, '16 997890990', 'A', '16/04/2015', '#304FFE');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (6, 'Luciana', 'Toro', 'Lu', 'lu.toro@gmail.com', 11, '27/08/1995', 'Rua Jose Andreoli, 892', 'Jd Sumare', 'Ribeirao Preto', 14080560, '16 991235645', 'C', '16/04/2015', '#2962FF');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (7, 'Amanda', 'Nunes', 'Nanda', 'nanda@yahoo.com', 11, '30/08/1987', 'Rua Carlos Lucas Evangelista, 356', 'Ribeirania', 'Ribeirao Preto', 14567080, '16 999878675', 'C', '16/04/2015', '#0091EA');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (8, 'Andressa', 'Conacci', 'Dressa', 'dessa@barao.com', 11, '16/09/1985', 'Rua Maria Quereli, 440', 'Parque dos Lagos', 'Ribeirao Preto', 14500090, '16 992345678', 'C', '16/04/2015', '#00B8D4');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (9, 'Eduardo', 'Silva', 'Edu', 'edu@barao.edu.br', 11, '12/03/1984', 'Professor Joaquim de Faria, 770', 'Vila Abranches', 'Ribeirao Preto', 14050400, '16 998909867', 'C', '16/04/2015', '#00BFA5');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (10, 'Joao', 'Pedroso', 'Jo', 'joao@sebrae.com', 11, '11/04/1958', 'Rua Jose Aissum, 356', 'Jd Castelo Branco', 'Ribeirao Preto', 14647000, '16 992345678', 'C', '16/04/2015', '#64DD17');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (11, 'Monica', 'Souza', 'Mo', 'moni@baidu.com', 11, '25/12/1980', 'Rua Albino Goncalves, 122', 'Iguatemi', 'Ribeirao Preto', 14300050, '16 988901256', 'C', '16/04/2015', '#FF6D00');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (12, 'Flavia', 'Santos', 'Fla', 'flavia@consultoriarh.com', 11, '03/11/1990', 'Rua Angelica, 1302', 'Jd Palma Travassos', 'Ribeirao Preto', 14400040, '16 991345684', 'C', '16/04/2015', '#00C853');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (13, 'Janaina', 'Garcia', 'Jana', 'jana@aeus.com', 11, '07/09/1993', 'Rua da Redencao, 590', 'Jd Mosteiro', 'Ribeirao Preto', 14490090, '16 993453489', 'C', '16/04/2015', '#A1887F');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (14, 'Marilda', 'Janduzo', 'Ma', 'rilda@uol.com', 11, '10/06/1989', 'Rua Lidio de Oliveira Valada, 880', 'Itamaraty', 'Ribeirao Preto', 14300030, '16 998123491', 'C', '16/04/2015', '#90A4AE');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (15, 'Adamastor', 'Pedrada', 'Mastor', 'ada@mastor.com', 1, '22/10/1988', 'Rua Olimpio Meireles Palma, 60', 'Ribeirania', 'Ribeirao Preto', 14500050, '22 992341276', null, null, '#7986CB');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (16, 'Hermenegildo', 'Santos', 'Herme', 'hermene@gildo.com', 11, '17/01/1991', 'Avenida Eduardo Andreia Matarazzo', 'Simioni', 'Ribeirao Preto', 14030000, '22 993325498', null, null, '#FF8A65');
INSERT INTO [TBUSUARIOS] ([_id], [nome], [sobrenome], [apelido], [email], [senha], [dtnascimento], [endereco], [bairro], [cidade], [cep], [telefone], [status], [dtcriacao], [imagem]) VALUES (17, 'Asdrubal ', 'Feliciano', 'Felici', 'Feliciano@sai.com', 11, '02/05/1995', 'Av. Leao XIII, 250', 'Ribeirania', 'Ribeirao Preto', 14130090, '22 991347650', null, null, '#BA68C8');

CREATE TABLE TBEMPRESAS ( 
    _id       INTEGER        PRIMARY KEY
                             NOT NULL
                             UNIQUE,
    empr_cnpj VARCHAR( 25 )  NOT NULL,
    nome      VARCHAR( 50 )  NOT NULL,
    status    VARCHAR( 1 )   NOT NULL 
);

INSERT INTO [TBEMPRESAS] ([_id], [empr_cnpj], [nome], [status]) VALUES (1, '46.788.789/0001-9', 'Barao de Maua', 'A');

CREATE TABLE VUSUAEMPR ( 
    _id    INTEGER PRIMARY KEY
                   NOT NULL
                   UNIQUE,
    iduser INTEGER NOT NULL
                   CONSTRAINT 'fk_user_vinculo' REFERENCES TBUSUARIOS ( _id ),
    idempr INTEGER NOT NULL
                   CONSTRAINT 'fk_empr_vinculo' REFERENCES TBEMPRESAS ( _id ) 
);

INSERT INTO [VUSUAEMPR] ([_id], [iduser], [idempr]) VALUES (1, 1, 1);

CREATE TABLE TBCOMPROMISSOS ( 
    _id           INTEGER        PRIMARY KEY
                                 NOT NULL
                                 UNIQUE,
    titulo        VARCHAR( 30 )  NOT NULL,
    descricao     VARCHAR( 50 )  NOT NULL,
    dtinicio      DATETIME       NOT NULL,
    dtfim         DATETIME,
    participantes TEXT,
    status        VARCHAR( 1 )   NOT NULL,
    iduser        INTEGER        CONSTRAINT 'fk_comp_usuar' REFERENCES TBUSUARIOS ( _id ),
    idempr        INTEGER        CONSTRAINT 'fk_comp_emp' REFERENCES TBEMPRESAS ( _id ) 
);

INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (1, 'Inicio de Desenvolvimento', 'Teste sobre Construcao', '16/04/2015 22:33', '17/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (2, 'Reuniao com a Debora', 'Teste', '12/04/2015 22:33', '12/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (3, 'Apresentacao Final', 'Apresentacao Final do Projeto', '17/04/2015 22:33', '17/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (4, 'YY', 'Teste 3', '26/04/2015 22:33', '26/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (5, 'WW', 'Teste 4', '17/04/2015 22:33', '17/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (6, 'AA', 'Teste 5', '18/04/2015 22:33', '18/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (7, 'BB', 'Teste 6', '01/05/2015 22:33', '19/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (8, 'CC', 'Teste 7', '25/04/2015 22:33', '25/04/2015 23:59', null, 'A', 1, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (9, 'TT', 'Teste 8', '21/04/2015 22:33', '21/04/2015 23:59', null, 'A', 2, 1);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (10, 'RR', 'Teste 9', '22/04/2015 22:33', '22/04/2015 23:59', null, 'A', 2, 2);
INSERT INTO [TBCOMPROMISSOS] ([_id], [titulo], [descricao], [dtinicio], [dtfim], [participantes], [status], [iduser], [idempr]) VALUES (11, 'CC', 'Teste 0', '10/04/2015 22:33', '10/04/2015 23:59', null, 'A', 2, 2);