package ishop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsSaver{
    private PreparedStatement prStatement = null;
    //private ResultSet resultSet;
    //private final String table = "";
    private final String sendSaveQuery = "INSERT INTO " + "product" + " SET idCategory = ?"
                                                        + ", code = ?, name = ?, unit = ?, quantity = ?"
                                                        + ", description = ?, idProvider = ?;";
    
    private final String sendUpdateQuery = "UPDATE " + "product" + " SET idCategory = ?"
                                                        + ", code = ?, name = ?, unit = ?, quantity = ?"
                                                        + ", description = ?, idProvider = ? WHERE idProduct = ?;";
    
    private String sendQuery;
    
    
    public ProductsSaver(){
        
    }
    
    public void saveData(Product product, Connection connection, String update){
        if (update.equalsIgnoreCase("update")) sendQuery = sendUpdateQuery; else sendQuery = sendSaveQuery;
        if (connection != null){
            try{
                prStatement = connection.prepareStatement(sendQuery);
                prStatement.setInt(1, product.getIdCategory());
                prStatement.setString(2, product.getCode());
                prStatement.setString(3, product.getName());
                prStatement.setString(4, product.getUnit());
                prStatement.setInt(5, product.getQuantity());
                prStatement.setString(6, product.getDescription());
                prStatement.setInt(7, product.getIdProvider());
                if (sendQuery.equals(sendUpdateQuery)) prStatement.setInt(8, product.getID());
                
                prStatement.execute();
                
                //System.out.println(resultSet);
                
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }else{
            System.out.println("Connection is null!");
        }
        
    }
    
    
//    public Connection connectToDB(String url, String user, String password) {
//        try{
//            connection = DriverManager.getConnection(url, user, password);
//        }catch(SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//        return connection;
//    }
    
    

    
    
    
    
}
