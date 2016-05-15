package ishop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Role {
    
    private int idRole;
    private StringProperty name = new SimpleStringProperty();
    private String description;
    
    
    public Role(){
        
    }

    
    
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return this.name.get();
    }
    
}
