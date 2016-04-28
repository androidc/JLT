package ishop;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
        
    @Override
    public void start(Stage primaryStage) {
        
        //   данные по БД в классе ProductsWindow
        
        new ProductsWindow(primaryStage);
        
    }
    
//---

    public static void main(String[] args) {
        launch(args);
    }

}
