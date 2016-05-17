package ishop;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
        
    @Override
    public void start(Stage primaryStage) {
        
        //   данные по БД в классе ProductsWindow
        
        // Отладка StaffWindow
        //DatabaseConnection.getConnection("jdbc:mysql://77.108.69.15:3306/shop", "admin", "shop2016");
        DatabaseConnection.getConnection("jdbc:mysql://localhost:3306/shop", "admin", "shop2016");
        //new StaffWindow();
        new ProductsWindow(primaryStage);
//        if (new LoginWindow().login()) new ProductsWindow(primaryStage);
//        else System.out.println("Error login. The programm will be closed.");
    }
    
//---

    public static void main(String[] args) {
        launch(args);
    }

}
