package ishop;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadFromDB extends ConnectToDB{
    
    private final String sendQuery = "SELECT * FROM " + "product";
    
    
    public LoadFromDB(){
        
    }
    
    
    public ArrayList<Product> loadData(){
        ArrayList<Product> products = new ArrayList<>();
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                while(resultSet.next()){
                    Product product = new Product();
                    product.setID(resultSet.getInt("idProduct"));
                    product.setIdCategory(resultSet.getInt("idCategory"));
                    product.setCode(resultSet.getString("code"));
                    product.setName(resultSet.getString("name"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setDescription(resultSet.getString("idProvider"));
                    product.setIdProvider(resultSet.getInt("idProvider"));
                                        
                    products.add(product);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
            
        }
        
        return products;
    }
    
}
