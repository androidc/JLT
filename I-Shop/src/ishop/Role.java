package ishop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Role {
    private static final String[] LEVEL = {"ADMINISTRATOR", "MAIN_OPERATOR", "OPERATOR", "CUSTOMER", "PROVIDER"};
    
    private final IntegerProperty idRole;
    private final StringProperty name;
    private final StringProperty description;
    
    
    public Role(){
        idRole = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        description = new SimpleStringProperty();
    }

    
    
    public int getIdRole() {
        return idRole.get();
    }

    public void setIdRole(int idRole) {
        this.idRole.set(idRole - 1);
        name.set(LEVEL[this.idRole.get()]);
        //this.idRole = idRole;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public static String idToName(int id){
        return LEVEL[id];
    }
    
    public static int nameToId(String n){
        int index = -1;
        for (int i = 0; i < LEVEL.length; i++){
            if (LEVEL[i].equalsIgnoreCase(n)) index = i;
        }
        return index;
    }
    
    public static String[] getAllRoles(){
        return LEVEL;
    }
    
    @Override
    public String toString(){
        return this.name.get().toUpperCase();
    }
    
}
