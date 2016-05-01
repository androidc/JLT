package ishop;

import java.lang.management.ThreadInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectToDB {
    protected static Connection connection;
    protected Statement statement;
    protected PreparedStatement prStatement;
    protected ResultSet resultSet;
    ConnectingWindow cw = null;
    
    public static Connection connect(String url, String user, String password) {
        
        
//        Thread t = new Thread(){
//            @Override
//            public void run(){
//                cw = new ConnectingWindow();
//            }
//        };
//        t.start();


        //while (t.isAlive()){}
//        Thread mt = Thread.currentThread();
//        
        
        //cw.run();
        ///cw.show();
//        while (cw.isAlive()){}
//        try {
//            //        try {
////            Thread.sleep(500);
////        } catch (InterruptedException ex) {
////        }
//        
//        //mt.join();
//        } catch (InterruptedException ex) {
//            
//        }

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            //cw.close();
            return null;
        }
        //cw.close();
        return connection;
    }
    
    public boolean close(){
        
            try {
                if (statement != null) statement.close();
                if (prStatement != null) prStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        
        return true;
    }
    
    
    
}
