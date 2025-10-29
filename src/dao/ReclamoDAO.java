package dao;

import conexion.DBConnection;
import java.sql.*; // Necesitas la clase modelo para transportar los datos
import java.util.ArrayList;
import java.util.List;
import modelo.Reclamo;


/*Listar los reclamos de un usuario con la cantidad de rellamados*/

public class ReclamoDAO {

    public List<Reclamo> listarReclamosConRellamados(int idUsuario) throws SQLException {
        List<Reclamo> listaReclamos = new ArrayList<>();

        //LEFT JOIN para incluir reclamos sin rellamados (COUNT = 0).
        //GROUP BY para agrupar por cada reclamo.
        String sql = "SELECT " +
                     "r.nro_reclamo, r.fecha_hora, r.fecha_res, " +
                     "COUNT(l.nro_llamado) AS cantidad_rellamados " +
                     "FROM reclamo r " +
                     // LEFT JOIN es crucial para incluir reclamos con 0 rellamados
                     "LEFT JOIN rellamado l ON r.nro_reclamo = l.nro_reclamo_rell " + 
                     "WHERE r.id_re = ? " +
                     "GROUP BY r.nro_reclamo, r.fecha_hora, r.fecha_res";
        
        try (Connection con = DBConnection.getConnection(); // La conexión se cierra automáticamente
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            //NO USAR setAutoCommit(false) para SELECT. Ya está en modo AutoCommit=true.
            
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) { 
                    
                    Reclamo r = new Reclamo();
                    r.setNroReclamo(rs.getInt("nro_reclamo"));
                    // Usamos getTimestamp para DATETIME y lo convertimos si es necesario, o lo leemos como Timestamp
                    r.setFechaHora(rs.getTimestamp("fecha_hora"));
                    r.setFechaRes(rs.getString("fecha_res"));
                    r.setCantidadRellamados(rs.getInt("cantidad_rellamados")); 
                    
                    listaReclamos.add(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reclamos (DAO): " + e.getMessage());
            throw e;
        }
        
        return listaReclamos;
    }
}