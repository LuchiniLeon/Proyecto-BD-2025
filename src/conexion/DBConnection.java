package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MySQL 
 * con credenciales fijas para el proyecto.
 */
public class DBConnection {

    // 1. Parámetros de conexión fijos (Hardcoded)
    private static final String DRIVER = "org.gjt.mm.mysql.Driver"; 
    private static final String URL = "jdbc:mysql://localhost/prueba"; // URL JDBC para MySQL
    private static final String USER = "tu_user";       // agregar tu user 
    private static final String PASSWORD = "tu_password"; // agregar tu clave

    // SQLException Si hay un error de conexión o driver.

    public static Connection getConnection() throws SQLException {
        
        try {
            // 2. Cargar el driver JDBC (Class.forName)
            // Esto prepara el driver para el DriverManager (Teórico 9, Pág. 12)
            Class.forName(DRIVER); 

        } catch (ClassNotFoundException e) {
            // Error si el JAR no está en lib/ o no está configurado en el classpath.
            System.err.println("ERROR: El driver JDBC (" + DRIVER + ") no fue encontrado.");
            throw new SQLException("Fallo al cargar el driver JDBC. Verifique la carpeta 'lib/' y el classpath.", e);
        }

        // 3. Obtener la conexión
        // DriverManager.getConnection() retorna la Interface Connection (Teórico 9, Pág. 8)
        return DriverManager.getConnection(URL, USER, PASSWORD); 
    }
}