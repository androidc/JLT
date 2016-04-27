package ishop;

import java.sql.SQLException;

public class SaveIntoDB extends ConnectToDB{
    //private final String table = "";
    private final String sendQuery = "INSERT INTO " + "product" + " SET idCategory = ?"
                                                        + ", code = ?, name = ?, unit = ?, quantity = ?"
                                                        + ", description = ?, idProvider = ?";
    
    public SaveIntoDB(){
        
    }
    
    public void saveData(Product product){
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
                
                prStatement.execute();
                
                System.out.println(resultSet);
                
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
