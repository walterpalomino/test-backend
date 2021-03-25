
insert into tipo_documento (nombre) values('DNI');
insert into tipo_documento (nombre) values('LE');
insert into tipo_documento (nombre) values('LC');

insert into candidato (nombre,apellido,tipo,numero_documento) values('Matias','Pereyra','1','34123456');
insert into candidato (nombre,apellido,tipo,numero_documento) values('Juan','Lopez','1','34123789');
insert into candidato (nombre,apellido,tipo,numero_documento) values('Maria','Olmedo','1','34789123');

insert into tecnologia (nombre,version) values ('Java',8);
insert into tecnologia (nombre,version) values ('C#',10);
insert into tecnologia (nombre,version) values ('.Net',15);
insert into tecnologia (nombre,version) values ('python',13);

insert into candidato_experiencia (candidato,tecnologia,experiencia) values (1,1,3);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (1,2,5);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (2,4,7);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (3,1,3);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (3,2,4);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (3,3,1);
insert into candidato_experiencia (candidato,tecnologia,experiencia) values (3,4,9);