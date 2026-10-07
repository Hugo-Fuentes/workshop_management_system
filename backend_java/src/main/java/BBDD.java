import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BBDD {
    private static String bbdd=DatabaseConfig.getDbUrl();
    private static Connection conexion;

    public static void conectar() throws SQLException {
        conexion= DriverManager.getConnection(bbdd,DatabaseConfig.getDbUser(),DatabaseConfig.getDbPassword());

    }
    public static void desconectar() throws SQLException{
        conexion.close();
    }


}
