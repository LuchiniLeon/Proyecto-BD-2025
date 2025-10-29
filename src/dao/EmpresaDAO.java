package dao;

import conexion.DBConnection;
import java.sql.*;
import modelo.Empresa;           

public class EmpresaDAO {

    public void insertarEmpresa(Empresa empresa) throws SQLException { 
        Connection conn = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtEmpresa = null; // Renombramos la variable a stmtEmpresa

        try {
            conn = DBConnection.getConnection(); 
            conn.setAutoCommit(false);
             
            //insercion tabla usuario
            String sqlUsuario = "INSERT INTO usuario (direccion, telefono) VALUES (?, ?)";
            stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, empresa.getDireccion());
            stmtUsuario.setString(2, empresa.getTelefono());
            
            stmtUsuario.executeUpdate(); 

            //obtengo id generado
            int nuevoIdUsuario = -1;
            try (ResultSet rs = stmtUsuario.getGeneratedKeys()) { 
                if (rs.next()) {
                    nuevoIdUsuario = rs.getInt(1); 
                    empresa.setId(nuevoIdUsuario); 
                } else {
                    throw new SQLException("Error al obtener el ID generado para el usuario.");
                }
            }

            //insercion tabla empresa (hija)
            String sqlEmpresa = "INSERT INTO empresa (id_e, cuit, capacidad_instalada) VALUES (?, ?, ?)"; 
            stmtEmpresa = conn.prepareStatement(sqlEmpresa); 
            stmtEmpresa.setInt(1, nuevoIdUsuario); 
            stmtEmpresa.setLong(2, empresa.getCuit());         // El CUIT es BIGINT
            stmtEmpresa.setInt(3, empresa.getCapacidadInstalada()); 
            
            // se ejecuta en nuevo statement
            stmtEmpresa.executeUpdate(); 

            //confirma los cambios para el fin de transaccion
            conn.commit(); 
            System.out.println("Empresa insertada correctamente. ID Generado: " + nuevoIdUsuario); 

        } catch (SQLException e) {
            // caso fallo: rollback
            System.err.println("Fallo en la transacción de Empresa. Intentando Rollback...");
            if (conn != null) {
                conn.rollback(); 
            }
            throw e; 
        } finally {
            //cerramos todo
            if (stmtEmpresa != null) stmtEmpresa.close(); // Referencia a stmtEmpresa
            if (stmtUsuario != null) stmtUsuario.close();
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }
}