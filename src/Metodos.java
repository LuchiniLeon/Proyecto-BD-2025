import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Metodos {

    private static void eliminarReclamo(Connection con, Scanner sc) {

        try {

            System.out.print("Ingrese el código del reclamo a eliminar: ");
            String nro_reclamo = sc.nextLine();
            String query = "DELETE FROM proyectoDB2.reclamo WHERE nro_reclamo = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nro_reclamo);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reclamo eliminado.");
            } else {
                System.out.println("No se encontró un reclamo con ese codigo.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarUsuario(Connection con, Scanner sc) {
        try {
            System.out.println("Ingrese el id del usuario: ");
            String id = sc.nextLine();

            // Verificar si el DNI ya existe
            String checkQuery = "SELECT 1 FROM proyectoDB2.usuario WHERE id = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, id);
            ResultSet rs = checkStmt.executeQuery();

            // Si ese next da true y entra significa que hay un registro que cumple
            if (rs.next()) {
                System.out.println("Error: El usuario con id " + id + " ya está registrado.");
                rs.close();
                checkStmt.close();
                return;
            }
            rs.close();
            checkStmt.close();

            // Si no existe, continuar con la inserción
            String insertQuery = "INSERT INTO proyectoDB2.usuario(id, direccion, telefono)" +
                    "VALUES (?,?,?)";
            PreparedStatement insertStmt = con.prepareStatement(insertQuery);
            insertStmt.setString(1, id);

            System.out.println("Ingrese la direccion del usuario: ");
            String direccion = sc.nextLine();
            insertStmt.setString(2, direccion);

            System.out.println("Ingrese el número de telefono del usuario");
            String telefono = sc.nextLine();
            insertStmt.setString(3, telefono);

            int filasInsertadas = insertStmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Usuario insertado con éxito.");
            }

            insertStmt.close();

        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }
}