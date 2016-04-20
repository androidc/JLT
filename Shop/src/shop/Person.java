package shop;

public abstract class Person implements FullPowers{
    private String name;
    
    
    
    public Person(){
        
        
    }
    
    public Person(String name){
        this.name = name;
    }
    
    
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    
    
}
