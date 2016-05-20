package ishop;

// Класс товар.

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Product {
    
    private int id = 0;             // Нужен ли? MySQL генерирует автоматически
    private String name = "";        // Наименование
    private int price = 0;          // Цена
    private String code = "";        // Артикул (Можно использовать как id)
    private String unit = "";        // Единица измерения
    private int quantity = 0;       // Количество
    private int idCategory = 0;     // ID катогории товара
    private String description = ""; // Описание
    private int idProvider = 0;     // ID поставщика
    
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

    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public String DataDB(Connection connection){
        String sendQuery = "SELECT categoryName FROM product_category WHERE idCategory=(SELECT idCategory FROM product WHERE code='" + code + "')";
        String allData = "";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sendQuery);){
                    while(resultSet.next()){
                    allData=id+"\n"+resultSet.getString(1)+"\n"+code+"\n"+name+"\n"+unit+"\n"+quantity+"\n";
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
       sendQuery= "SELECT companyName FROM provider WHERE idProvider=(SELECT idProvider FROM product WHERE code='"+code+"')";
       try(Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sendQuery);){
                    while(resultSet.next()){
                    allData=allData+resultSet.getString(1);
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        return allData;
    }
    
}
