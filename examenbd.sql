create database if not exists centro_formacion;
use centro_formacion;

create table if not exists alumno(
id_alumno int auto_increment,
nombre varchar(15),
apellidos varchar(15),
email varchar(30),
constraint  pk_alumno primary key (id_alumno),
constraint uk_alumno_email unique (email)
);

create table if not exists curso(

id_curso int auto_increment,
nombre varchar(15),
horas int,
constraint pk_curso primary key (id_curso),
constraint chkk_curso_horas_pos check (horas > 0)

);

create table if not exists profesor(

id_profesor int auto_increment,
nombre varchar(15),
email varchar(30),
constraint pk_profesor primary key (id_profesor),
constraint uk_profesor_email unique (email)

);

create table if not exists departamento(

id_departamento int auto_increment,
nombre varchar(100),
constraint pk_departamento primary key (id_departamento),
constraint uk_departamento_nombre unique (nombre)

);
/*Bloque B*/
alter table profesor
add column id_departamento int,
add constraint fk_profesor_departamento foreign key (id_departamento) references departamento(id_departamento)
on delete restrict
on update cascade;

create table if not exists matricula(

id_alumno int,
id_curso int,
constraint pk_matricula primary key (id_alumno, id_curso)

);

alter table matricula 
add constraint fk_matricula_alumno foreign key (id_alumno) references alumno(id_alumno)
on delete cascade
on update cascade;

alter table matricula 
add constraint fk_matricula_curso foreign key (id_curso) references curso(id_curso)
on delete restrict 
on update cascade;

alter table profesor 
add column id_profesor_jefe int null,
add constraint fk_profesor_jefe foreign key (id_profesor_jefe) references profesor(id_profesor)
on delete set null;

/*Bloque C*/
alter table matricula
drop foreign key fk_matricula_alumno;
alter table alumno
drop primary key,
add constraint pk_alumno primary key (id_alumno, email);


alter table alumno
drop primary key,
add constraint pk_alumno primary key (id_alumno)
;

alter table curso 
add column codigo int,
add constraint uk_curso_codigo unique (codigo);

alter table profesor 
change id_profesor_jefe id_tutor int;


alter table profesor 
drop foreign key fk_profesor_jefe;
alter table profesor
add constraint fk_profesor_jefe foreign key (id_tutor) references profesor(id_profesor) on delete set null;

alter table departamento
add constraint chk_departamento_nombre_valido check (trim(nombre)<>'');

/*Bloque D*/
alter table matricula
drop foreign key fk_matricula_curso;
alter table matricula
add constraint fk_matricula_curso foreign key (id_curso) references curso(id_curso) 
on delete cascade
on update cascade;


alter table profesor
drop foreign key fk_profesor_departamento;
alter table profesor
add constraint fk_profesor_departamento foreign key (id_departamento) references departamento(id_departamento)
on delete set null
on update cascade;

alter table curso
add column id_responsable int,
add constraint fk_curso_profesor foreign key (id_responsable) references profesor(id_profesor)
on delete set null;

rename table alumno to estudiante;

alter table estudiante
change email correo varchar(100);

create table  if not exists aula(

id_aula int auto_increment,
nombre varchar(100),

constraint pk_id_aula primary key (id_aula),
constraint uk_aula_nombre unique (nombre)


);

alter table curso 
add column id_aula int,
add constraint fk_curso_aula foreign key (id_aula) references aula(id_aula)
on delete restrict 
on update cascade;

/*BLOQUE E*/

alter table matricula 
add column fecha date,
add constraint chk_matricula_fecha_min check ( fecha >= '2000-01-01');

alter table matricula 
drop foreign key fk_matricula_curso;
alter table matricula 
add constraint fk_matricula_alumno foreign key (id_alumno) references estudiante(id_alumno)
on delete cascade
on update cascade;

/*drop table if exists estudiante;
drop table if exists curso;
drop table if exists departamento;
drop table if exists matricula;
drop table if exists profesor;
drop table if exists aula;
drop table if exists alumno;

show create table aula;
show create table curso;
show create table departamento;
show create table estudiante;
show create table matricula;
show create table profesor;*/

/*Bloque F*/
create user 'af_admim'@'localhost' identified by 'Admin1234.';
create user 'cf_user'@'%' identified by 'Admin1234.';
create user 'cf_ro'@'%' identified by 'Admin1234.';
/* PRIVILEGIOS */
GRANT ALL PRIVILEGES ON centro_formacion.* TO 'cf_admin'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE 
ON centro_formacion.estudiante TO 'cf_user'@'%';

GRANT SELECT ON centro_formacion.* TO 'cf_ro'@'%';

/* GRANT OPTION */
CREATE USER 'cf_delegado'@'localhost' IDENTIFIED BY '1234';

GRANT SELECT ON centro_formacion.curso 
TO 'cf_delegado'@'localhost' WITH GRANT OPTION;

/* REVOKE */
REVOKE INSERT, DELETE ON centro_formacion.* FROM 'cf_user'@'%';

/* ROLES */
CREATE ROLE rol_lectura;
CREATE ROLE rol_escritura;
CREATE ROLE rol_total;

GRANT SELECT ON centro_formacion.* TO rol_lectura;
GRANT INSERT, UPDATE, DELETE ON centro_formacion.* TO rol_escritura;
GRANT ALL ON centro_formacion.* TO rol_total;

GRANT rol_total TO 'cf_admin'@'localhost';
GRANT rol_lectura TO 'cf_ro'@'%';
GRANT rol_escritura TO 'cf_user'@'%';

/* CAMBIOS ROLES */
REVOKE rol_escritura FROM 'cf_user'@'%';
GRANT rol_lectura TO 'cf_user'@'%';

DROP ROLE rol_total;

CREATE ROLE rol_total;
GRANT ALL ON centro_formacion.* TO rol_total;
GRANT rol_total TO 'cf_admin'@'localhost';

/* PERMISOS */
SHOW GRANTS FOR 'cf_ro'@'%';

REVOKE ALL PRIVILEGES ON centro_formacion.* FROM 'cf_ro'@'%';

DROP USER 'cf_ro'@'%';
