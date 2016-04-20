package shop;

// полномочия

public interface FullPowers {
    
    /*
        level
            0 - Покупатель
            1 - Оператор
            2 - Старший оператор
            3 - Администратор
    
    */
    public void setLevel(int lvl);
    
    public int getLevel();
    
}
