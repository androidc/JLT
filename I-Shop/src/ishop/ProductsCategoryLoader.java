package ishop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsCategoryLoader{

    private Statement statement;
    private ResultSet resultSet;
    //protected PreparedStatement prStatement;
    
    private final String sendQuery = "SELECT * FROM " + "product_category";
    
    
    
    public HashMap<Integer, String> loadData(Connection connection) {
        HashMap<Integer, String> prCategory = new HashMap<>();
        
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                
                while(resultSet.next()){
                    ProductsCategory productsCategory = new ProductsCategory();
                    productsCategory.setID(resultSet.getInt("idCategory"));
                    productsCategory.setName(resultSet.getString("categoryName"));
                    
                    prCategory.put(productsCategory.getID(), productsCategory.getName());
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
        
        return prCategory;
    }
    
}
