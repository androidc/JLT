package ishop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection(String url, String login, String password){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch(SQLException| ClassNotFoundException e){
            Alert errorWindow = new Alert(Alert.AlertType.ERROR, "Can't connected to DB!\n" + e.getMessage());
            errorWindow.showAndWait();
            //System.out.println("Can't connected to DB. " + e.getMessage());
            //e.printStackTrace();
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
