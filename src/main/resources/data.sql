#create table users (    username varchar(50) not null primary key,    password varchar(255) not null,    enabled boolean not null) engine = InnoDb;
#create table authorities (    username varchar(50) not null,    authority varchar(50) not null,    foreign key (username) references users (username),    unique index authorities_idx_1 (username, authority)) engine = InnoDb;
#create table marca ( id int not null AUTO_INCREMENT, descrizione varchar(255), PRIMARY KEY (id));
#create table carburante ( id int not null AUTO_INCREMENT, descrizione varchar(255), PRIMARY KEY (id));
#create table veicolo (  id int not null AUTO_INCREMENT, carburante int not null,marca  varchar(50), modello varchar(50), targa varchar(20), PRIMARY KEY (id));