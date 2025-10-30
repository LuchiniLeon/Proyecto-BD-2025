USE proyectoBD;
-- Devolver por cada reclamo, el detalle de materiales utilizados para
-- solucionarlo, si un reclamo no uso materiales, listarlo también.
SELECT cod_material_usa, descripcion_mat FROM material NATURAL JOIN usa NATURAL RIGHT OUTER JOIN reclamo;
-- Devolver los usuarios que tienen más de un reclamo
SELECT id_re, COUNT(nro_reclamo) AS cantidad_reclamos
FROM reclamo
GROUP BY id_re
HAVING COUNT(nro_reclamo) > 1; -- (usuarios) con más de 1 reclamo
--  Listado de reclamos que fueron asignados a más de un empleado de mantenimiento.
SELECT m1.nro_reclamo_resuelve FROM resuelve m1, resuelve m2 
WHERE m1.nro_reclamo_resuelve = m2.nro_reclamo_resuelve AND m1.id_resuelve != m2.id_resuelve;