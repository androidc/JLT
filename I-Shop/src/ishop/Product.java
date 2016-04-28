package ishop;

// Класс товар.

public class Product {
    
    private int id;             // Нужен ли? MySQL генерирует автоматически
    private String name;        // Наименование
    private int price;          // Цена
    private String code;        // Артикул (Можно использовать как id)
    private String unit;        // Единица измерения
    private int quantity;       // Количество
    private int idCategory;     // ID катогории товара
    private String description; // Описание
    private int idProvider;     // ID поставщика
    
    public Product(){
        
    }
    
    public Product(String name, int price){
        this.name = name;
        this.price = price;
    }
    
    public Product(String name, int price, String unit, int quantity, String code){
        this(name, price);
        this.unit = unit;
        this.quantity = quantity;
        this.code = code;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setUnit(String unit){
        this.unit = unit;
    }
    
    public String getUnit(){
        return unit;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    
    public int getIdCategory() {
        return idCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }
    
    public int getIdProvider() {
        return idProvider;
    }
    
}
