package shop;

import java.util.*;

// Класс склад. Представлен в виде коллекции HashMap<id, Товар>

public class Storage extends HashMap<Integer, Product>{
    public static int count;
    
    
    public Storage(){
        
    }
    
    public void add(Product product){
        put(count++, product);
    }
    
    public void get(int id, int quantity){         // вычитать количество из общей кучи товара
        Product product = (Product) get(id);
        if (product.getQuantity() >= quantity){
            product.setQuantity(product.getQuantity() - quantity);
        }
        if (product.getQuantity() <= 0) remove(id);
    }
    
    public void get(Product product, int quantity){
        //Product product = get(product);
    }
    
    public void sort(){
        
    }
}
