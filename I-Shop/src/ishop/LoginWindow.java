package ishop;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.jgss.LoginConfigImpl;

public class LoginWindow {
    private String url = "jdbc:mysql://localhost/shop",
                         //"jdbc:mysql://77.108.69.15:3306/shop" ,
                   user = "",
                   password = "";
    private boolean isLogin = false;        //  Переделать, это явная "дырка" в безопасности
    
    
    public boolean login(){
        
        init();
        
        
        return isLogin;
    }
    
    
    private void init(){
        Stage stage = new Stage();
        VBox loginPane = new VBox(10);
        
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        //loginPane;
        
        Label loginLabel = new Label("Login");
        TextField loginField = new TextField();
        
        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        
        //FlowPane buttonPane = new FlowPane();
        HBox buttonPane = new HBox(10);
        
        Button okButton = new Button("Login");
        Button cancelButton = new Button("Cancel");
        
        Label tryConnectingLabel = new Label();
        
        EventHandler event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == okButton){
                    okButton.setDisable(true);
                    loginField.setDisable(true);
                    passwordField.setDisable(true);
                    tryConnectingLabel.setText("... trying to connect ...");
                    //Thread t = new Thread(){
                    //    @Override
                    //    public void run(){
                            if (DatabaseConnection.getConnection(url, loginField.getText(), passwordField.getText()) != null){
                                System.out.println("loggined!");
                                isLogin = true;
                                //Thread.currentThread().interrupt();
                                if (stage.isShowing()) stage.close();
                                
                            }
                            else {
                                System.out.println("Can't connecting to DB");
                                //System.exit(0);
                            }
                            tryConnectingLabel.setText(null);
                            passwordField.clear();
                            loginField.setDisable(false);
                            passwordField.setDisable(false);
                            okButton.setDisable(false);
                    //    };
                    //};
                    //t.start();
//                    Thread currT = Thread.currentThread();
//                    currT.join();
                    //while(t.isAlive()){}
                        
                    
                    
                }
                
                if (event.getSource() == cancelButton){
                    System.exit(0);
                }
            }
            
        };
        
        okButton.setOnAction(event);
        cancelButton.setOnAction(event);
        
        buttonPane.getChildren().addAll(okButton, cancelButton);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        //buttonPane.setPadding(new Insets(10, 10, 10, 10));
        //buttonPane.setHgap(10);
        //buttonPane.setVgap(10);
        
        loginPane.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField, tryConnectingLabel, buttonPane);
        
        
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(new Scene(loginPane));
        stage.showAndWait();
    }
    
}
