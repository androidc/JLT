package ishop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsCategoryLoader implements Loadable{

    private Statement statement;
    private ResultSet resultSet;
    //protected PreparedStatement prStatement;
    
    private final String sendQuery = "SELECT * FROM " + "product_category";
    
    
    
    @Override
    public ArrayList<ProductsCategory> loadData(Connection connection) {
        ArrayList<ProductsCategory> prCategory = new ArrayList<>();
        
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                
                while(resultSet.next()){
                    ProductsCategory productsCategory = new ProductsCategory();
                    productsCategory.setID(resultSet.getInt("idCategory"));
                    productsCategory.setName(resultSet.getString("categoryName"));
                    
                    prCategory.add(productsCategory);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
        
        return prCategory;
    }
    
}
