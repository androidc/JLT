package ishop;

import static ishop.ConnectToDB.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection(String url, String login, String password){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Can't connected to DB");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    
    public static Connection getConnection(){
        return connection;
    }
    
    public static void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Closing ERROR");
            ex.printStackTrace();
        }
    }
}
