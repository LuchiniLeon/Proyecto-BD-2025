package dao;

import conexion.DBConnection; 
import modelo.Persona;       
import java.sql.*;           

public class PersonaDAO {

    public void insertarPersona(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtPersona = null;

        try {
            conn = DBConnection.getConnection(); 
            
            //desactivar autocommit para hacerlo manual
            conn.setAutoCommit(false); 

            // con id generado INSERCION tabla padre "usuario"
            String sqlUsuario = "INSERT INTO usuario (direccion, telefono) VALUES (?, ?)";
            stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, persona.getDireccion());
            stmtUsuario.setString(2, persona.getTelefono());
            
            stmtUsuario.executeUpdate(); 

            // obtener el id generado
            int nuevoIdUsuario = -1;
            try (ResultSet rs = stmtUsuario.getGeneratedKeys()) { 
                if (rs.next()) {
                    nuevoIdUsuario = rs.getInt(1); 
                    persona.setId(nuevoIdUsuario); 
                } else {
                    throw new SQLException("Error al obtener el ID generado para el usuario.");
                }
            }

            // INSERCION tabla hija persona 
            String sqlPersona = "INSERT INTO persona (id_p, dni, nombre, fecha_nac, edad) VALUES (?, ?, ?, ?, ?)";
            stmtPersona = conn.prepareStatement(sqlPersona); 
            
            stmtPersona.setInt(1, nuevoIdUsuario); 
            stmtPersona.setInt(2, persona.getDni());
            stmtPersona.setString(3, persona.getNombre());
            stmtPersona.setDate(4, java.sql.Date.valueOf(persona.getFechaNac())); 
            stmtPersona.setInt(5, persona.getEdad());

            stmtPersona.executeUpdate(); 

            //fin transaccion, confirma los cambios
            conn.commit(); 
            System.out.println("Persona insertada correctamente. ID Generado: " + nuevoIdUsuario);

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
            if (stmtUsuario != null) stmtUsuario.close();
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }
}