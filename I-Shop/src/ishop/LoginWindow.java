package ishop;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {
    private String url = "jdbc:mysql://localhost:3306/shop",
                   user = "",
                   password = "";
    private boolean isLogin = false;        //  Переделать, это явная "дырка" в безопасности
    
    
    public boolean login(){
        
        init();
        
        
        return isLogin;
    }
    
    
    private void init(){
        Stage stage = new Stage();
        VBox loginPane = new VBox();
        
        Label loginLabel = new Label("Login");
        TextField loginField = new TextField();
        
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        
        HBox buttonPane = new HBox();
        
        Button okButton = new Button("Login");
        Button cancelButton = new Button("Cancel");
        
        EventHandler event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == okButton){
//                    if (ConnectToDB.connect(url, loginField.getText(), passwordField.getText()) != null){
//                        isLogin = true;
//                    }
                }
                if (event.getSource() == cancelButton){
                    System.exit(0);
                }
            }
            
        };
        
        
        buttonPane.getChildren().addAll(okButton, cancelButton);
        
        loginPane.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField, buttonPane);
        
        
        
        stage.setScene(new Scene(loginPane));
        stage.show();
    }
    
}
