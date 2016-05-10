package ishop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoleLoader implements Loadable{

    private Statement statement;
    private ResultSet resultSet;
    
    private final String sendQuery = "SELECT * FROM " + "role";

    
    
    @Override
    public ArrayList loadData(Connection connection) {
        ArrayList<Role> roles = new ArrayList<>();
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                while(resultSet.next()){
                    Role role = new Role();
                    role.setIdRole(resultSet.getInt("idRole"));
                    role.setName(resultSet.getString("name"));
                    role.setDescription(resultSet.getString("description"));
                    
                    roles.add(role);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return roles;
    }
    
}
