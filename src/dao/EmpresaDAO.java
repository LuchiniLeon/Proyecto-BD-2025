package dao;

import conexion.DBConnection;
import java.sql.*;
import modelo.Empresa;        

public class EmpresaDAO {
    private UsuarioDAO usuario = new UsuarioDAO();

    public void insertarEmpresa(Empresa empresa) throws SQLException { 
        Connection conn = null;
        PreparedStatement stmtEmpresa = null; // Renombramos la variable a stmtEmpresa

        try {
            conn = DBConnection.getConnection(); 
            conn.setAutoCommit(false);
             
            int idEmpresa = usuario.insertarUsuario(empresa);

            //insercion tabla empresa (hija)
            String sqlEmpresa = "INSERT INTO empresa (id_e, cuit, capacidad_instalada) VALUES (?, ?, ?)"; 
            stmtEmpresa = conn.prepareStatement(sqlEmpresa); 
            stmtEmpresa.setInt(1, idEmpresa); 
            stmtEmpresa.setLong(2, empresa.getCuit());         // El CUIT es BIGINT
            stmtEmpresa.setInt(3, empresa.getCapacidadInstalada()); 
            
            // se ejecuta en nuevo statement
            stmtEmpresa.executeUpdate(); 

            //confirma los cambios para el fin de transaccion
            conn.commit(); 
            System.out.println("Empresa insertada correctamente. ID Generado: " + idEmpresa); 

        } catch (SQLException e) {
            // caso fallo: rollback
            System.err.println("Fallo en la transacción de Empresa. Intentando Rollback...");
            if (conn != null) {
                conn.rollback(); 
            }
            throw e; 
        } finally {
            //cerramos todo
            if (stmtEmpresa != null) stmtEmpresa.close(); // Referencia a stmtEmpres
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }
}