package ishop;

class ProductsCategory {
    private int ID = 0;
    private String name = "";

    
    public ProductsCategory(){
        
    }
    
    public ProductsCategory(int id, String name){
        this.ID = id;
        this.name = name;
    }
    
    public int getID() {
        return ID;
    }

    
    public void setID(int ID) {
        this.ID = ID;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
}
