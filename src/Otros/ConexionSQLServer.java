package Otros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSQLServer {
    public static Connection Conectar(){

        // Connect to database
        String hostName = "socet.database.windows.net";
        String dbName = "SOCET";
        String user = "socetplusadmin";
        String password = "Socet12345";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection conn = null;
        try {
                conn = DriverManager.getConnection(url);
                System.out.println("Successful connection");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQLServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}