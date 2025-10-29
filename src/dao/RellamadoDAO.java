package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DBConnection;

public class RellamadoDAO {
    private static void listaReclamos(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtUsuario = null;
        PreparedStatement stmtPersona = null;
        try {
            conn = DBConnection.getConnection();

            // desactivar autocommit para hacerlo manual
            conn.setAutoCommit(false);

            String query = "select r.nro_reclamo, u.id, COUNT(l.nro_reclamo_rell)" +
                    "from proyectoBD.reclamo r " +
                    "join proyectoBD.usuario u" +
                    "join proyectoBD.rellamado l" +
                    "where u.id = r.id_re AND l.nro_reclamo_rell = r.nro_reclamo";

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            System.out.println();
            System.out.println("Listado de usuarios con sus reclamos");
            System.out.println("-----------------------------------");
            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1));
                // si esto llega a fallar poner (id) y el resto
                System.out.println("Reclamo: " + resultSet.getString(2));
                System.out.println("N° rellamados: " + resultSet.getString(3));
                System.out.println("----------------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }

}
