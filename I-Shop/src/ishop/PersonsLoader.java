package ishop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonsLoader implements Loadable{
    private Statement statement;
    private ResultSet resultSet;
    
    private final String sendQuery = "SELECT * FROM " + "personal";

    
    
    @Override
    public ArrayList loadData(Connection connection) {
        ArrayList<Person> persons = new ArrayList<>();
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                ArrayList<Role> personsRoles = new RoleLoader().loadData(connection);
                
                while(resultSet.next()){
                    Person person = new Person();
                    //person.setID(++id);
                    person.setId(resultSet.getInt("idPerson"));
                    person.setIdRole(resultSet.getInt("idRole"));
                    person.setIdFunction(resultSet.getInt("idFunction"));
                    person.setLevel(person.getIdRole());
                    person.setFirstName(resultSet.getString("firstName"));
                    person.setLastName(resultSet.getString("lastName"));
                    person.setBirthday(resultSet.getDate("birthday"));
                    person.setPersonalPhone(resultSet.getString("personalPhone"));
                    person.setWorkPhone(resultSet.getString("workPhone"));
                    person.setAddress(resultSet.getString("address"));
                    person.setLogin(resultSet.getString("login"));
                    person.setPassword(resultSet.getString("password"));
                    
                    
                    persons.add(person);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return persons;
    }
}
