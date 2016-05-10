package ishop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonsLoader implements Loadable{
    private Statement statement;
    private ResultSet resultSet;
    //protected PreparedStatement prStatement;
    
    // КЛАСС НЕ ДОДЕЛАН!!!
    
    private final String sendQuery = "SELECT * FROM " + "personal";

    @Override
    public ArrayList loadData(Connection connection) {
        ArrayList<Person> persons = new ArrayList<>();
        if (connection != null){
            try{
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sendQuery);
                while(resultSet.next()){
                    Person person = new Person();
                    //person.setID(++id);
                    //product.setID(resultSet.getInt("idProduct"));
                    person.setId(resultSet.getInt("idPerson"));
                    person.setIdRole(resultSet.getInt("idRole"));
                    person.setIdFunction(resultSet.getInt("idFunction"));
                    person.setFirstName(resultSet.getString("firstName"));
                    person.setLastName(resultSet.getString("lastName"));
                    person.setBirthday(resultSet.getDate("birthday"));
//                    product.setIdProvider(resultSet.getInt("idProvider"));
//                                        
//                    products.add(product);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return persons;
    }
}
