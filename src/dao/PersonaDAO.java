package dao;

import conexion.DBConnection;
import java.sql.*;
import modelo.Persona;

public class PersonaDAO {
    private UsuarioDAO usuario = new UsuarioDAO();


    public void insertarPersona(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtPersona = null;

        try {
            conn = DBConnection.getConnection(); 
            
            //desactivar autocommit para hacerlo manual
            conn.setAutoCommit(false); 

            // Inserta en tabla usuario primero
            int idPersona = usuario.insertarUsuario(persona);

            // INSERCION tabla hija persona 
            String sqlPersona = "INSERT INTO persona (id_p, dni, nombre, fecha_nac, edad) VALUES (?, ?, ?, ?, ?)";
            stmtPersona = conn.prepareStatement(sqlPersona); 
            
            stmtPersona.setInt(1, idPersona); 
            stmtPersona.setInt(2, persona.getDni());
            stmtPersona.setString(3, persona.getNombre());
            stmtPersona.setDate(4, java.sql.Date.valueOf(persona.getFechaNac())); 
            stmtPersona.setInt(5, persona.getEdad());

            stmtPersona.executeUpdate(); 

            //fin transaccion, confirma los cambios
            conn.commit(); 
            System.out.println("Persona insertada correctamente. ID Generado: " + idPersona);

        } catch (SQLException e) {
            // Caso fallo, hacer rollback
            System.err.println("Fallo en la transacción de Persona. Intentando Rollback...");
            if (conn != null) {
                conn.rollback(); 
            }
            throw e; 
        } finally {
            // cerramos recursos
            if (stmtPersona != null) stmtPersona.close();
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }
}