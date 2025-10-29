package app;

import conexion.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        
        Connection connection = null;

        try {
            // Intenta obtener la conexión usando tu gestor
            connection = DBConnection.getConnection(); 
            
            // Si no lanzó excepción, la conexión fue exitosa
            System.out.println("=============================================");
            System.out.println("✅ CONEXIÓN EXITOSA A LA BASE DE DATOS.");
            System.out.println("URL: " + connection.getMetaData().getURL()); // Muestra la URL conectada
            System.out.println("=============================================");

        } catch (SQLException e) {
            // Si lanza SQLException, algo falló (driver, credenciales, DB caída)
            System.err.println("=============================================");
            System.err.println("❌ ERROR DE CONEXIÓN A LA BASE DE DATOS.");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("=============================================");

        } finally {
            // Asegura el cierre de la conexión, sin importar si hubo error o no.
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}