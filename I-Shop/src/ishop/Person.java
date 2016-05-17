package ishop;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Класс Персона.


public class Person{

    
    //public static enum Level {ADMINISTRATOR, MAIN_OPERATOR, OPERATOR, CUSTOMER, PROVIDER};
            
    private int id;
    private IntegerProperty idRole = new SimpleIntegerProperty();
    private int idFunction;
    private final StringProperty firstName = new SimpleStringProperty();           // Имя,
    private final StringProperty lastName = new SimpleStringProperty();           // Фамилия
    private Date birthday;                      // Дата рождения
    private final StringProperty address  = new SimpleStringProperty();                     // Адрес
    private final StringProperty personalPhone  = new SimpleStringProperty();
    private final StringProperty workPhone  = new SimpleStringProperty();         // Телефон
    private String[] levelName = {"", "ADMINISTRATOR", "MAIN_OPERATOR", "OPERATOR", "CUSTOMER", "PROVIDER"};                          // Уровень доступа к БД, определяется Правами доступа
    private String level;
    private StringProperty function = new SimpleStringProperty();
    private final StringProperty login  = new SimpleStringProperty();
    private final StringProperty password  = new SimpleStringProperty();
    private Role role = new Role();
    
    
    public Person(){
//        this("login", "");
    }
    
//    public Person(String login, String password){
//        this.login = new SimpleStringProperty(login);
//        this.password = new SimpleStringProperty(password);
//        
//    }
    
//    public Person(String firstName, String lastName, Date birthday, String address, String workPhone){
//        this(firstName, lastName);
//        this.birthday = birthday;
//        this.address = new SimpleStringProperty(address);
//        this.workPhone = new SimpleStringProperty(workPhone);
//    }
    
        
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    
    public String getFirstName(){
        return firstName.get();
    }
    
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
 
    public String getLastName(){
        return lastName.get();
    }
    
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
 
    public Date getBirthday(){
        return birthday;
    }
    
    public void setAddress(String address){
        this.address.set(address);
    }
 
    public String getAddress(){
        return address.get();
    }
    
    public void setPersonalPhone(String phoneNumber){
        personalPhone.set(phoneNumber);
    }
 
    public String getPersonalPhone(){
        return personalPhone.get();
    }
    
    public void setWorkPhone(String phoneNumber){
        workPhone.set(phoneNumber);
    }
 
    public String getWorkPhone(){
        return workPhone.get();
    }
//    public void setLevel(Level lvl){
//        this.lvl = lvl;
//    }
    
    public void setLevel(int lvl){
        this.level = levelName[lvl];
    }
    
    public String getLevel(){
        return level;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole.get();
    }

    public void setIdRole(int idRole) {
        this.idRole.set(idRole);
        role = new Role();
        role.setName(Role.idToName(idRole));
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }
    
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFunction() {
        return function.get();
    }

    public void setFunction(String function) {
        this.function.set(function);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
