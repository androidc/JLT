package ishop;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoadFromDB_provider extends ConnectToDB{
    
    
    private final String sendQuery = "SELECT * FROM " + "provider";
    
    
    public LoadFromDB_provider(){
        
    }
    
    
    public ArrayList<Provider> loadData(){
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
