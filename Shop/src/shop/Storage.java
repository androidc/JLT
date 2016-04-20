package shop;

import java.util.*;

// Класс склад. Представлен в виде коллекции HashMap<id, Товар>

public class Storage extends HashMap<Integer, Product>{
    public static int count;
    
    
    public Storage(){
        count++;
    }
    
    public void add(Product product){
        put(count, product);
    }
    
    public Product get(int id, int quantity){         // вычитать количество из общей кучи товара
        Product product = get(id);
        if (product.getQuantity() >= quantity){
            product.setQuantity(product.getQuantity() - quantity);
            
        }
        else
            return null;
        
        return product;
    }
    
    public void sort(){
        
    }
}
