package ishop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConnectingWindow implements Runnable{
    private final Thread thread;
    private Stage stage;
    
    public ConnectingWindow(){
        stage = new Stage();
        
        //super();
        thread = new Thread(this);
        
        
//        try {
//            Thread.sleep(1500);
//            
//            //show();
//        } catch (InterruptedException ex) {
//            
//        }
        init();
        //thread.start();
        
        
    }
    
    @Override
    public void run(){
        
        
        
        System.out.println("connectingWindow");
//        try {
//            Thread.sleep(500);
//            
//            
//        } catch (InterruptedException ex) {
//            
//        }
        
    }
    
    
    private synchronized void init(){
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(new Label("Trying connecting to DataBase..."));
        pane.setPadding(new Insets(30, 30, 30, 30));
        pane.setStyle("-fx-background: gray; -fx-font: italic 14pt Georgia");
        stage.setScene(new Scene(pane));
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }
//    public boolean isAlive(){
//        return thread.isAlive();
//    }
    
//    public void show(){
//        
//    }
//    
    public void close(){
        stage.close();
    }
    
}
