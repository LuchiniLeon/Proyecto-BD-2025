package dao;

import conexion.DBConnection; 
import modelo.Empresa;       
import java.sql.*;           

public class EmpresaDAO {

    public void insertarPersona(Empresa empresa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtPersona = null;

        try {
            conn = DBConnection.getConnection(); 
            
            // 1. INICIO DE TRANSACCIÓN: Desactivar AutoCommit
            conn.setAutoCommit(false); 

            // =========================================================================
            // A. INSERCIÓN EN TABLA PADRE: USUARIO (Obteniendo el ID generado)
            String sqlUsuario = "INSERT INTO usuario (direccion, telefono) VALUES (?, ?)";
            stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            stmtUsuario.setString(1, empresa.getDireccion());
            stmtUsuario.setString(2, empresa.getTelefono());
            
            stmtUsuario.executeUpdate(); 

            // B. OBTENER ID GENERADO 
            int nuevoIdUsuario = -1;
            try (ResultSet rs = stmtUsuario.getGeneratedKeys()) { 
                if (rs.next()) {
                    nuevoIdUsuario = rs.getInt(1); 
                    empresa.setId(nuevoIdUsuario); 
                } else {
                    throw new SQLException("Error al obtener el ID generado para el usuario.");
                }
            }

            // =========================================================================
            // C. INSERCIÓN EN TABLA HIJA: PERSONA
            String sqlPersona = "INSERT INTO empresa (id_e, cuit, capacidad_instalada) VALUES (?, ?, ?)";
            stmtPersona = conn.prepareStatement(sqlPersona); 
            
            stmtPersona.setInt(1, nuevoIdUsuario); 
            stmtPersona.setInt(2, persona.getDni());
            stmtPersona.setString(3, persona.getNombre());
            stmtPersona.setDate(4, java.sql.Date.valueOf(persona.getFechaNac())); 
            stmtPersona.setInt(5, persona.getEdad());

            stmtPersona.executeUpdate(); 

            // 2. FIN DE TRANSACCIÓN: Confirmar los cambios
            conn.commit(); 
            System.out.println("✅ Persona insertada correctamente. ID Generado: " + nuevoIdUsuario);

        } catch (SQLException e) {
            // 3. FALLO: Hacer ROLLBACK
            System.err.println("❌ Fallo en la transacción de Persona. Intentando Rollback...");
            if (conn != null) {
                conn.rollback(); 
            }
            throw e; 
        } finally {
            // 4. CIERRE DE RECURSOS
            if (stmtPersona != null) stmtPersona.close();
            if (stmtUsuario != null) stmtUsuario.close();
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }
}
}
