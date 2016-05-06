package ishop;

import java.util.Date;

// Класс Персона.


public class Person{

    void getId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static enum Level {ADMINISTRATOR, MAIN_OPERATOR, OPERATOR, CUSTOMER, PROVIDER};
            
    private int id;
    private int idRole;
    private int idFunction;
    private String firstName, lastName;         // Имя, фамилия
    private Date birthday;                      // Дата рождения
    private String address;                     // Адрес
    private String personalPhone, workPhone;    // Телефон
    private Level lvl;                          // Уровень доступа к БД, определяется Правами доступа
    
    
    public Person(){
        this("Somebody", "Somebody");
    }
    
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person(String firstName, String lastName, Date birthday, String address, String workPhone){
        this(firstName, lastName);
        this.birthday = birthday;
        this.address = address;
        this.workPhone = workPhone;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
 
    public String getLastName(){
        return lastName;
    }
    
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
 
    public Date getBirthday(){
        return birthday;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
 
    public String getAddress(){
        return address;
    }
    
    public void setPersonalPhone(String phoneNumber){
        this.personalPhone = phoneNumber;
    }
 
    public String getPersonalPhone(){
        return personalPhone;
    }
    
    public void setWorkPhone(String phoneNumber){
        this.workPhone = phoneNumber;
    }
 
    public String getWorkPhone(){
        return workPhone;
    }
    public void setLevel(Level lvl){
        this.lvl = lvl;
    }
    
    public Level getLevel(){
        return lvl;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }
}
        

