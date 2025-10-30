USE proyectoBD;
-- Devolver por cada reclamo, el detalle de materiales utilizados para
-- solucionarlo, si un reclamo no uso materiales, listarlo también.
SELECT 
r.nro_reclamo AS 'Número de Reclamo',
m.descripcion_mat AS 'Material Utilizado',
u.cant AS 'Cantidad Utilizada'
FROM reclamo r 
LEFT JOIN usa u ON r.nro_reclamo = u.nro_reclamo_usa
LEFT JOIN material m ON u.cod_material_usa = m.cod_material
ORDER BY r.nro_reclamo;

-- Devolver los usuarios que tienen más de un reclamo
SELECT id_re AS 'Usuario(id)', COUNT(nro_reclamo) AS 'Cantidad de Reclamos'
FROM reclamo
GROUP BY id_re
HAVING COUNT(nro_reclamo) > 1; -- (usuarios) con más de 1 reclamo
--  Listado de reclamos que fueron asignados a más de un empleado de mantenimiento.
SELECT DISTINCT m1.nro_reclamo_resuelve AS 'Número de reclamo asignado a más de un empleado' FROM resuelve m1, resuelve m2 
WHERE m1.nro_reclamo_resuelve = m2.nro_reclamo_resuelve AND m1.id_resuelve != m2.id_resuelve;