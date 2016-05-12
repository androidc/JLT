package ishop;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
        
    @Override
    public void start(Stage primaryStage) {
        
        //   данные по БД в классе ProductsWindow
        new StaffWindow();
        
//        if (new LoginWindow().login()) new ProductsWindow(primaryStage);
//        else System.out.println("Error login. The programm will be closed.");
    }
    
//---

    public static void main(String[] args) {
        launch(args);
    }

}
