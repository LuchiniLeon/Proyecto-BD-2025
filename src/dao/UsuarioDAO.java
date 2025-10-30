package dao;

import conexion.DBConnection;
import java.sql.*;
import modelo.Usuario;  

public class UsuarioDAO {

    public int insertarUsuario(Usuario usuario) throws SQLException {

        // con id generado INSERCION tabla padre "usuario"
        String sqlUsuario = "INSERT INTO usuario (direccion, telefono) VALUES (?, ?)";
        // obtener el id generado
        int nuevoIdUsuario = -1;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)){ 
            
            //desactivar autocommit para hacerlo manual
            conn.setAutoCommit(false); 
            stmtUsuario.setString(1, usuario.getDireccion());
            stmtUsuario.setString(2, usuario.getTelefono());

            stmtUsuario.executeUpdate(); 

            try (ResultSet rs = stmtUsuario.getGeneratedKeys()) { 
                if (rs.next()) {
                    nuevoIdUsuario = rs.getInt(1); 
                    usuario.setId(nuevoIdUsuario); 
                } else {
                    throw new SQLException("Error al obtener el ID generado para el usuario.");
                }
            }
        }
        return nuevoIdUsuario;
    }
}
