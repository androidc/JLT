package ishop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductsLoader implements Loadable{
    private Statement statement;
    private ResultSet resultSet;
    
    private String sendQuery = "SELECT * FROM " + "product";

    public void setSendQuery(String sendQuery) {
        this.sendQuery = sendQuery;
    }
    
    public ProductsLoader(){
        
    }
    
    @Override
    public ArrayList<Product> loadData(Connection connection){
        ArrayList<Product> products = new ArrayList<>();
        if (connection != null){
            try(Statement statement = connection.createStatement();
                ResultSet resultSet= statement.executeQuery(sendQuery);){
//                statement = connection.createStatement();
//                resultSet = statement.executeQuery(sendQuery);
                int id = 0;
                while(resultSet.next()){
                    Product product = new Product();
                    product.setID(++id);
                    //product.setID(resultSet.getInt("idProduct"));
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
