-- Desactivar la verificación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Limpieza de datos 
TRUNCATE TABLE resuelve;
TRUNCATE TABLE usa;
TRUNCATE TABLE rellamado;
TRUNCATE TABLE reclamo;
TRUNCATE TABLE motivo;
TRUNCATE TABLE material;
TRUNCATE TABLE empleado;
TRUNCATE TABLE persona;
TRUNCATE TABLE empresa;
TRUNCATE TABLE usuario;
-- TRUNCATE TABLE reclamo_eliminado;

-- USUARIOS, MOTIVOS, Y MATERIALES (INSERCIONES DE INICIO)

-- USUARIO (17 en total)
-- id : 1, 2, 3, 5, 10, 11, 12, 13, 14, 15 = PERSONAS 
-- id:  4, 6, 8, 9 = EMPLEADOS 
-- id: 7, 16, 17 = EMPRESAS

INSERT INTO usuario (id, direccion, telefono) VALUES
(1, 'Calle Falsa 123', '3584101010'),   -- p1: Ana
(2, 'Av. Central 456', '3584202020'),   -- p2: Leonardo
(3, 'Ruta 36 Km 5', '3584303030'),     -- p3: Roberto
(4, 'Barrio Industrial', '3584404040'), -- e1: Marta 
(5, '25 de Mayo 100', '3584505050'),    -- p4: Luis
(6, 'Las Heras 789', '3584606060'),     -- e2: Ricardo 
(7, 'Belgrano 111', '3584707070'),      -- empresa1: Empresa Norte 
(8, 'Mitre 222', '3584808080'),        -- e3: Sofía 
(9, 'San Martín 333', '3584909090'),    -- e4: Pedro 
(10, 'General Paz 444', '3584112233'),  -- p5: Carlos
(11, 'España 500', '3584111111'),      -- p6: María Elena
(12, 'Saavedra 600', '3584222222'),     -- p7: Javier
(13, 'Jujuy 700', '3584333333'),       -- p8: Elsa
(14, 'Salta 800', '3584444444'),       -- p9: Diego
(15, 'Corrientes 900', '3584555555'),   -- p10: Florencia
(16, 'Chacabuco 100', '3584666666'),    -- empresa2: Empresa Sur S.A. 
(17, 'Lavalle 200', '3584777777');     -- empresa3: Distribuidora E 


-- MOTIVO (8 en total)

INSERT INTO motivo (cod_motivo, descripcion) VALUES
(1, 'Falta de suministro'),        
(2, 'Baja tensión'),              
(3, 'Poste caído'),
(4, 'Problema con medidor'),
(5, 'Falla en alumbrado público'),
(6, 'Corte programado no avisado'),
(7, 'Riesgo eléctrico en domicilio'),
(8, 'Cambio de titularidad pendiente');

-- MATERIAL (9 en total)

INSERT INTO material (cod_material, descripcion_mat) VALUES
(1, 'Fusible tipo K'),            
(2, 'Cable preensamblado 16mm'),  
(3, 'Aislador de porcelana'),
(4, 'Medidor digital'),
(5, 'Pinza de sujeción 50mm'),
(6, 'Conector bimetálico'),
(7, 'Guantes dieléctricos'),
(8, 'Cable subterráneo 5x10mm'),
(9, 'Transformador 10KVA');

-- 2. INSERCIÓN HERENCIA (Debe usar los id)

-- PERSONA (id: 1, 2, 3, 5, 10, 11, 12, 13, 14, 15)

INSERT INTO persona (id_p, dni, nombre, fecha_nac, edad) VALUES
(1, 25888999, 'Ana Gómez', '1978-05-15', 47),
(2, 44252839, 'Leonardo Campos', '2002-07-15', 23),
(3, 42342839, 'Roberto Gomez', '1980-07-15', 45),
(5, 30111222, 'Luis Torres', '1990-11-20', 35),
(10, 32444555, 'Carlos Solis', '1988-03-10', 37),
(11, 38123456, 'María Elena Roldán', '1993-01-22', 32),
(12, 40987654, 'Javier Núñez', '1998-12-05', 27),
(13, 22111333, 'Elsa Peralta', '1970-08-01', 55),
(14, 29000000, 'Diego Funes', '1985-04-30', 40),
(15, 33555777, 'Florencia Vidal', '1991-09-19', 34);

-- EMPLEADO (id: 4, 6, 8, 9)

INSERT INTO empleado (id_em, dni, sueldo) VALUES
(4, 22555444, 720000), -- e1: Marta
(6, 28000111, 900000), -- e2: Ricardo
(8, 35000999, 650000), -- e3: Sofía
(9, 21999888, 780000); -- e4: Pedro

-- EMPRESA (id: 7, 16, 17)

INSERT INTO empresa (id_e, cuit, capacidad_instalada) VALUES
(7, 30698765432, 12000), -- empresa1: Empresa Norte 
(16, 30712345678, 45000), -- empresa2: Empresa Sur S.A.
(17, 33111222333, 25000); -- empresa3: Distribuidora E. 



-- 3. INSERCIÓN DE RECLAMOS

INSERT INTO reclamo (nro_reclamo, fecha_hora, fecha_res, cod_motivo_re, id_re) VALUES
(101, '2025-09-20 10:00:00', '2025-09-20', 1, 1),   -- R101: Ana (U1). Resuelto. Mat=Sí, Empl=1.
(102, '2025-10-01 14:00:00', '2025-10-01', 2, 16),  -- R102: Emp. Sur (U16). Resuelto. Mat=Sí, Empl=2. (+1 empleado)
(103, '2025-10-05 09:30:00', NULL, 1, 5),                   -- R103: Luis (U5). Abierto.
(104, '2025-10-10 08:00:00', '2025-10-10', 3, 1),   -- R104: Ana (U1). Resuelto. Mat=No.
(105, '2025-10-15 11:00:00', '2025-10-15', 4, 16),  -- R105: Emp. Sur (U16). Resuelto. Mat=Sí.
(106, '2025-10-20 07:00:00', '2025-10-20', 1, 7),   -- R106: Emp. Norte (U7). Resuelto. Mat=No.
(107, '2025-10-21 12:00:00', NULL, 2, 5),                   -- R107: Luis (U5). Abierto.
(108, '2025-10-21 10:00:00', '2025-10-21', 7, 11),  -- R108: María Elena (U11). Resuelto. Mat=Sí.
(109, '2025-10-22 09:00:00', '2025-10-22', 5, 12);  -- R109: Javier (U12). Resuelto. Mat=Sí, Empl=1.


-- 4. INSERCIÓN EN TABLAS RELACIONALES (N:M y Rellamados)

-- RESUELVE (Asignación de Empleados)
INSERT INTO resuelve (nro_reclamo_resuelve, id_resuelve) VALUES
(101, 4), -- r101 resuelto por Marta e1
(102, 4), -- r102 resuelto por Marta e1
(102, 6), -- r102 resuelto por Ricardo e2 
(104, 9), -- r104 resuelto por Pedro e4
(105, 4), -- r105 resuelto por Marta e1
(108, 6), -- r108 resuelto por Ricardo e2
(109, 9); -- r109 resuelto por Pedro e4

-- USA (Uso de Materiales)

INSERT INTO usa (nro_reclamo_usa, cod_material_usa, cant) VALUES
(101, 1, 2), -- r101 usó 2 Fusibles
(102, 1, 1), -- r102 usó 1 Fusible
(102, 2, 10), -- r102 usó 10m de Cable
(105, 4, 1), -- r105 usó 1 Medidor digital
(108, 8, 5), -- r108 usó 5m de Cable subterráneo
(108, 9, 1), -- r108 usó 1 Transformador 10KVA
(109, 3, 3); -- r109 usó 3 Aisladores

-- RELLAMADO 

INSERT INTO rellamado (nro_reclamo_rell, nro_llamado, fecha_hora_llamado) VALUES
(103, 1, '2025-10-05 09:45:00'), -- r103 tuvo 2 rellamados
(103, 2, '2025-10-05 10:15:00'),
(104, 1, '2025-10-10 08:15:00'), -- r104 tuvo 1 rellamado
(107, 1, '2025-10-21 12:30:00'), -- r107 tuvo 3 rellamados
(107, 2, '2025-10-21 12:45:00'),
(107, 3, '2025-10-21 13:00:00'); 

-- Reactivar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;


