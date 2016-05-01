package ishop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProvidersLoader implements Loadable{
    private Statement statement;
    private ResultSet resultSet;
    //protected PreparedStatement prStatement;
    
    private final String sendQuery = "SELECT * FROM " + "provider";
    
    
    public ProvidersLoader(){
        
    }
    
    
    @Override
    public ArrayList<Provider> loadData(Connection connection){
        ArrayList<Provider> providers = new ArrayList<>();
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                
                while(resultSet.next()){
                    Provider provider = new Provider();
                    provider.setID(resultSet.getInt("idProvider"));
                    provider.setName(resultSet.getString("companyName"));
                    providers.add(provider);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return providers;
    }
}
