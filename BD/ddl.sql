USE proyectoBD;

DROP TABLE IF EXISTS reclamosEliminados; -- Ya no tiene FK, pero va primero por seguridad
DROP TABLE IF EXISTS resuelve;
DROP TABLE IF EXISTS usa;
DROP TABLE IF EXISTS rellamado;

DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS empresa;
DROP TABLE IF EXISTS reclamo;
DROP TABLE IF EXISTS motivo;
DROP TABLE IF EXISTS material;

DROP TABLE IF EXISTS usuario;


CREATE TABLE usuario(
id INTEGER AUTO_INCREMENT NOT NULL,
direccion VARCHAR(50), 
telefono VARCHAR(50),
CONSTRAINT id_pk PRIMARY KEY (id)
);

CREATE TABLE empresa(
id_e INTEGER NOT NULL,
cuit BIGINT NOT NULL UNIQUE,
capacidad_instalada INTEGER,
CONSTRAINT id_e_pk PRIMARY KEY (id_e),
CONSTRAINT id_e_fk FOREIGN KEY (id_e) REFERENCES usuario (id) ON DELETE CASCADE,
CONSTRAINT cap_valid CHECK (capacidad_instalada < 50000 AND capacidad_instalada > 0)
);

CREATE TABLE persona(
id_p INTEGER NOT NULL,
dni INTEGER NOT NULL UNIQUE,
nombre VARCHAR(50) NOT NULL,
fecha_nac DATE NOT NULL,
edad INTEGER NOT NULL,
CONSTRAINT id_p_pk PRIMARY KEY (id_p),
CONSTRAINT id_p_fk FOREIGN KEY (id_p) REFERENCES usuario (id) ON DELETE CASCADE,
CONSTRAINT dni_valid CHECK (dni < 1000000000 AND dni > 0)
);

CREATE TABLE empleado(
id_em INTEGER NOT NULL,
sueldo INTEGER,
CONSTRAINT id_em_pk PRIMARY KEY (id_em),
CONSTRAINT id_em_fk FOREIGN KEY (id_em) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE motivo(
cod_motivo INTEGER AUTO_INCREMENT NOT NULL,
descripcion VARCHAR(50),
CONSTRAINT cod_motivo_pk PRIMARY KEY (cod_motivo)
);

CREATE TABLE reclamo(
nro_reclamo INTEGER AUTO_INCREMENT NOT NULL,
fecha_hora DATETIME,	-- origen del reclamo
fecha_res DATE, -- fecha de resolucion
cod_motivo_re INTEGER NOT NULL,
id_re  INTEGER NOT NULL,
CONSTRAINT nro_reclamo_pk PRIMARY KEY (nro_reclamo),
CONSTRAINT cod_motivo_fk FOREIGN KEY (cod_motivo_re) REFERENCES motivo (cod_motivo) ON DELETE NO ACTION,
CONSTRAINT id_re_fk FOREIGN KEY (id_re) REFERENCES usuario (id) ON DELETE CASCADE
);

CREATE TABLE material(
cod_material INTEGER AUTO_INCREMENT NOT NULL,
descripcion_mat VARCHAR(50),
CONSTRAINT cod_mat_pk PRIMARY KEY (cod_material)
);

CREATE TABLE rellamado(
nro_reclamo_rell INTEGER NOT NULL,
nro_llamado INTEGER NOT NULL,
fecha_hora_llamado DATETIME,
CONSTRAINT nro_rec_fk FOREIGN KEY (nro_reclamo_rell) REFERENCES reclamo (nro_reclamo) ON DELETE CASCADE,
CONSTRAINT rec_lla_pk PRIMARY KEY (nro_reclamo_rell, nro_llamado) 
);

CREATE TABLE usa(
nro_reclamo_usa INTEGER NOT NULL,
cod_material_usa INTEGER NOT NULL,
cant INTEGER, 
CONSTRAINT cod_mat_usa FOREIGN KEY (cod_material_usa) REFERENCES material (cod_material),
CONSTRAINT nro_reclamo_usa FOREIGN KEY (nro_reclamo_usa) REFERENCES 
reclamo (nro_reclamo) ON DELETE CASCADE,
CONSTRAINT usa_pk PRIMARY KEY (nro_reclamo_usa, cod_material_usa)
);

CREATE TABLE resuelve(
nro_reclamo_resuelve INTEGER NOT NULL,
id_resuelve INTEGER NOT NULL,
CONSTRAINT nro_reclamo_pk PRIMARY KEY (nro_reclamo_resuelve, id_resuelve),
CONSTRAINT nro_reclamo_fk FOREIGN KEY (nro_reclamo_resuelve) REFERENCES reclamo (nro_reclamo) ON DELETE CASCADE,
CONSTRAINT id_fk FOREIGN KEY (id_resuelve) REFERENCES empleado (id_em) ON DELETE RESTRICT
);

CREATE TABLE reclamosEliminados(
nro_reclamo_el INTEGER NOT NULL,
fecha_eliminacion DATE NOT NULL,
usuario VARCHAR(50),
CONSTRAINT nro_fecha_us_pk PRIMARY KEY (nro_reclamo_el, fecha_eliminacion, usuario)
);

DELIMITER //

CREATE TRIGGER trigger_eliminacion_reclamos
AFTER DELETE ON reclamo
FOR EACH ROW
BEGIN
    INSERT INTO reclamosEliminados (nro_reclamo_el, fecha_eliminacion, usuario)
    VALUES (OLD.nro_reclamo, NOW(), CURRENT_USER());
END;
//

DELIMITER ;


