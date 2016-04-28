package ishop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectToDB {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement prStatement;
    protected ResultSet resultSet;
    
    
    public Connection connectToDB(String url, String user, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    
    public boolean close(){
        
            try {
                if (prStatement != null) prStatement.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
               e.printStackTrace();
                return false;
            }
        
        return true;
    }
}
