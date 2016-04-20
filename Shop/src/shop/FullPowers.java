package shop;

// полномочия

public interface FullPowers {
    
    /*
        level
            0 - Покупатель
            1 - Оператор
            2 - Старший оператор
            3 - Администратор
    
            Пока делал этот интерфейс, подумал, а нафига он вообще нужен,
            если полномочия можно проверить, опросив класс?.. getClass
    
    */
    
    public void setLevel(int lvl);
    
    public int getLevel();
    
}
