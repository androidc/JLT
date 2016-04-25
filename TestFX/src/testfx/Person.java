package testfx;

import java.util.Date;

// Класс Персона.


public class Person{
    
    public static enum Level {ADMINISTRATOR, MAIN_OPERATOR, OPERATOR, CUSTOMER, PROVIDER};
            
        
    private String firstName, lastName;         // Имя, фамилия
    private Date birthday;                      // Дата рождения
    private String address;                     // Адрес
    private String phoneNumber;                 // Телефон
    private Level lvl;                          // Уровень доступа к БД, определяется Правами доступа
    
    
    public Person(){
        this("Somebody", "Somebody");
    }
    
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Person(String firstName, String lastName, Date birthday, String address, String phoneNumber){
        this(firstName, lastName);
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
 
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setLevel(Level lvl){
        this.lvl = lvl;
    }
    
    public Level getLevel(){
        return lvl;
    }
}
        

