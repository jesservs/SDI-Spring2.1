drop database if exists sditcc;
create database sditcc;

use sditcc;

/*Tabela de perfis*/
create table perfil (
	idperfil 	int primary key auto_increment,
	nomeperfil 	varchar (45) not null
	) ENGINE = InnoDB;

/*Tabela de departamentos*/
create table departamento (
	iddepto 	int primary key auto_increment,
	descdepto 	varchar (45) not null,
	deptoativo	boolean default 1 not null
	) ENGINE = InnoDB;
	
/*Tabela de usuários*/
create table usuario (
	idusuario 	 int primary key auto_increment,
	login 		 varchar (45) 	not null,
	senha 		 varchar (100) 	not null,
	nomeusuario  varchar (45) 	not null,
	cpfusuario 	 varchar (45)	not null,
	telusuario 	 varchar (45),
	email		 varchar (45) 	not null,
	usuarioativo boolean default 1 not null,
	id_perfil 	int not null,
	id_depto 	int not null,
	foreign key (id_perfil) references perfil (idperfil),
	foreign key (id_depto) references departamento (iddepto)
	) ENGINE = InnoDB;

/*Tabela de status*/
create table status (
	idstatus 	int primary key auto_increment,
	descstatus 	varchar (45) not null
	) ENGINE = InnoDB;

/*Tabela de prioridades*/
create table prioridade (
	idprioridade 	int primary key auto_increment,
	descprioridade 	varchar (45) not null,
	temporesolucao int not null
	) ENGINE = InnoDB;

/*Tabela dos tipos de chamados*/
create table tipo (
	idtipo 		int primary key auto_increment,
	desctipo 	varchar (45) not null
	) ENGINE = InnoDB;

/*Tabela de categorias*/
create table categoria (
	idcategoria 	int primary key auto_increment,
	desccategoria	varchar (80) not null,
	id_tipo 		int not null,
	id_prioridade	int not null,
	categoriaativa 	boolean default 1 not null,
	foreign key (id_tipo) references tipo (idtipo),
	foreign key (id_prioridade) references prioridade (idprioridade)
	) ENGINE = InnoDB;

/*Tabela de conhecimento*/
create table conhecimento (
	idconhecimento		int primary key auto_increment,
	descconhecimento	text (500) not null,
	dthrultalteracao	datetime not null,
	id_categoria		int not null,
	id_usuario			int not null,
	foreign key (id_categoria) references categoria (idcategoria),
	foreign key (id_usuario) references usuario (idusuario)
	) ENGINE = InnoDB;
	
/*Tabela de chamados*/
/*create table chamado(
				idChamado int primary key auto_increment, 
				descChamado varchar(200) not null, 
				dtHrAbertura varchar(40) not null,
				dtHrFechamento varchar(40),
				id_status int not null,
				id_usuario int not null,				
				id_funcionario int not null,
				id_categoria int not null,
				foreign key (id_categoria) references categoria (idcategoria),
				foreign key (id_usuario) references usuario(idUsuario) on delete cascade,
				foreign key (id_status) references status(idStatus) on delete cascade,
				foreign key (id_funcionario) references funcionario(idFuncionario) on delete cascade);*/

/*Tabela de chamados*/
create table chamado (
	idchamado			int primary key auto_increment,
	dthrabertura		datetime not null,
	dthrfechamento		datetime,
	dthrsolucao			datetime,
	descchamado			text (1000) not null,
	id_status			int not null,
	id_responsavel 		int,
	id_solicitante 		int not null,
	id_atendente 		int,
	id_categoria		int not null,
	id_novaprioridade	int,
	foreign key (id_status) references status (idstatus),
	foreign key (id_responsavel) references usuario (idUsuario),
	foreign key (id_solicitante) references usuario (idUsuario),
	foreign key (id_atendente) references usuario (idUsuario),
	foreign key (id_categoria) references categoria (idcategoria),
	foreign key (id_novaprioridade) references prioridade (idprioridade)
	) ENGINE = InnoDB;

/*Tabela de histórico do chamado*/
create table historico (
	idhistorico 		int primary key auto_increment,
	dthrabertura		datetime not null,
	dthrfechamento		datetime,
	dthrsolucao			datetime,
	dthralteracao		datetime,
	descchamado			text (1000) not null,
	id_status			int not null,
	id_responsavel 		int,
	id_solicitante 		int not null,
	id_atendente 		int,
	id_categoria		int not null,
	id_novaprioridade	int,
	id_operador			int,
	id_chamado			int not null,
	foreign key (id_status) references status (idstatus),
	foreign key (id_responsavel) references usuario (idUsuario),
	foreign key (id_solicitante) references usuario (idUsuario),
	foreign key (id_atendente) references usuario (idUsuario),
	foreign key (id_categoria) references categoria (idcategoria),
	foreign key (id_novaprioridade) references prioridade (idprioridade),
	foreign key (id_operador) references usuario (idUsuario),
	foreign key (id_chamado) references chamado (idchamado)
	) ENGINE = InnoDB;

/*Tabela de Qualidade*/
create table qualidade (
	idqualidade			int primary key auto_increment,
	descqualidade		text (1000) not null,
	nota		 		int not null,
	id_solicitante 		int not null,
	id_chamado 			int not null,
	foreign key (id_solicitante) references usuario (idUsuario),
	foreign key (id_chamado) references chamado (idChamado)
) ENGINE = InnoDB;

/*Status de um chamado*/	
insert into status values(null, 'Novo');
insert into status values(null, 'Em andamento');
insert into status values(null, 'Aguardando');
insert into status values(null, 'Pausado');
insert into status values(null, 'Resolvido');
insert into status values(null, 'Fechado');
	
/*Prioridades de um chamado*/
insert into prioridade values (null, 'Critica', 1);	
insert into prioridade values (null, 'Alta', 4);
insert into prioridade values (null, 'Media', 24);
insert into prioridade values (null, 'Baixa', 48);
insert into prioridade values (null, 'Planejada', 0);

/*Tabela de tipos de chamados*/
insert into tipo values (null, 'Incidente');
insert into tipo values (null, 'Requisicao de servico');
insert into tipo values (null, 'Solicitacao de acesso');
					
/*Incidentes*/
insert into categoria values (null, 'Sistema operacional apresenta lentidao', 1, 3, 1);  
insert into categoria values (null, 'Sistema operacional reinicia involuntariamente', 1, 1, 1); 
insert into categoria values (null, 'Sistema operacional nao inicia', 1, 1, 1); 
insert into categoria values (null, 'Mensagem de erro no windows', 1, 3, 1);
insert into categoria values (null, 'Disco rigido sem espaco de armazenamento', 1, 1, 1); 

/*Requisição de serviço*/
insert into categoria values (null, 'Instalacao de novo aplicativo', 2, 3, 1);
insert into categoria values (null, 'Desinstalar aplicativo', 2, 3, 1);
insert into categoria values (null, 'Trocar o tonner da impressora', 2, 4, 1);
insert into categoria values (null, 'Movimentacao de computador', 2, 3, 1);
insert into categoria values (null, 'Configurar rede sem fio para o usuário', 2, 3, 1);

/*Solicitação de acesso*/
insert into categoria values (null, 'Conceder acesso a um sistema da intranet', 3, 3, 1);
insert into categoria values (null, 'Remover o acesso  de um sistema da intranet', 3, 2, 1);
insert into categoria values (null, 'Acesso a VPN da empresa', 3, 3, 1);
insert into categoria values (null, 'Remover acesso a VPN da empresa', 3, 2, 1);

/*Perfis existentes*/
insert into perfil values (null, 'Atendente');
insert into perfil values (null, 'Tecnico');
insert into perfil values (null, 'Administracao');
insert into perfil values (null, 'Usuario');

/*Exemplos de departamentos*/
insert into departamento values (null, 'Suporte técnico', 1);
insert into departamento values (null, 'Recursos humanos', 1);
insert into departamento values (null, 'Desenvolvimento', 1);
insert into departamento values (null, 'Infraestrutura', 1);
insert into departamento values (null, 'Escritorio de Projetos', 1);
insert into departamento values (null, 'Juridico', 1);
insert into departamento values (null, 'Contabilidade', 1);
insert into departamento values (null, 'Compras', 1);

/*Exemplos de usuários*/
insert into usuario values (null, 'lsilva', md5(123456), 'Leonardo Silva', '44825895736', '24049993', 'lsilva@email.com', 1, 4, 2);
insert into usuario values (null, 'jcosta', md5(123456), 'Jorge Costa', '14785269985', '040458885', 'jcosta@email.com', 1, 4, 7);
insert into usuario values (null, 'krocha', md5(123456), 'Karla Rocha', '14778566698', '24045699', 'krocha@email.com', 1, 4, 5);
insert into usuario values (null, 'mlima', md5(123456), 'Meline Lima', '01459665589', '24049666', 'mlima@email.com', 1, 4, 6);
insert into usuario values (null, 'tlemes', md5(123456), 'Tulio Lemes', '14785569985', '24049855', 'tlemes@email.com', 1, 4, 6);
insert into usuario values (null, 'rlacerda', md5(123456), 'Rafael Lacerda', '04785597758', '24046588', 'rlacerda@email.com', 1, 4, 2);
insert into usuario values (null, 'fnicodemos', md5(123456), 'Felipe Nicodemos', '14478755589', '24048858', 'fnicodemos@email.com', 1, 4, 3);
insert into usuario values (null, 'fbrandao', md5(123456), 'Flávia Brandão', '14788833325', '24041125', 'fbrandao@email.com', 1, 4, 1);
insert into usuario values (null, 'rcordeiro', md5(123456), 'Renan Cordeiro', '12345678896', '24041147', 'rcordeiro@email.com', 1, 4, 7);
insert into usuario values (null, 'rlobo', md5(123456), 'Robson Lobo', '14544477858', '24048966', 'rlobo@email.com', 1, 4, 6);
insert into usuario values (null, 'vcaldas', md5(123456), 'Vitor Caldas', '15585698744', '24047747', 'vcaldas@email.com', 1, 4, 2);

/*Técnicos*/
insert into usuario values (null, 'ygusmao', md5('1234'), 'Yuri Gusmão', '145.635.788-26', '2404-7778', 'ygusmao@email.com', 1, 2, 1);
insert into usuario values (null, 'alemos', md5('1234'), 'Augusto Lemos', '874.588.258-85', '2404-7778', 'alemos@email.com', 1, 2, 1);
insert into usuario values (null, 'jcorreia', md5('1234'), 'Jordan Correia', '147.569.365-84', '2404-7778', 'jcorreia@email.com', 1, 2, 1);
insert into usuario values (null, 'esilva', md5('1234'), 'Elvis Silva', '778.965.288-32', '2404-7778', 'esilva@email.com', 1, 2, 1);

/*Atendentes*/
insert into usuario values (null, 'jsilva', md5('1234'), 'Judith Silva', '552-447-663.44', '2404-8888', 'jsilva@email.com', 1, 1, 1);
insert into usuario values (null, 'tamaro', md5('1234'), 'Tatiana Amaro', '784.669.885-36', '2404-8888', 'tamaro@email.com', 1, 1, 1);


/*Administradores*/
insert into usuario values (null, 'msantos', md5('1234'), 'Michael Santos', '129.360.177-26', '2404-3577', 'msantos@email.com', 1, 3, 1);

/*INSERT INTO QUALIDADE (IDQUALIDADE, DESCQUALIDADE, NOTA, ID_SOLICITANTE, ID_CHAMADO)
VALUES (null, 'Atendimento rápido e eficiente. Estou Satisfeito!', 3, 1, 1);*/

/*Trigger para o histórico*/
DELIMITER $$

CREATE TRIGGER historico_chamado
BEFORE UPDATE ON chamado
FOR EACH ROW 
BEGIN
INSERT INTO historico 
SET dthrabertura = OLD.dthrabertura,
dthrfechamento = OLD.dthrfechamento,
dthrsolucao = OLD.dthrsolucao,
dthralteracao = now(),
descchamado = OLD.descchamado,
id_status = OLD.id_status,
id_responsavel = OLD.id_responsavel,
id_solicitante = OLD.id_solicitante,
id_atendente = OLD.id_atendente,
id_categoria = OLD.id_categoria,
id_novaprioridade = OLD.id_novaprioridade,
id_chamado = OLD.idchamado;
END$$

DELIMITER ;





