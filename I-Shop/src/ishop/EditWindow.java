package ishop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditWindow extends Stage{           //  implements Runnable{
    //private TextField nameField, codeField, categoryField, unitField, quantityField, providerField;
    private Button saveButton, cancelButton;
    private Product product;
    
    
    public EditWindow(Stage primaryStage){
        
        Label nameLabel = new Label("Product's name");
        TextField nameField = new TextField();

        Label codeLabel = new Label("Code");
        TextField codeField = new TextField();

        Label categoryLabel = new Label("Category");
        TextField categoryField = new TextField();

        Label unitLabel = new Label("Item");
        TextField unitField = new TextField();

        Label quantityLabel = new Label("Quantity");
        TextField quantityField = new TextField();

        Label providerLabel = new Label("Provider");
        TextField providerField = new TextField();

        VBox verticalPane = new VBox();
        FlowPane buttonsPane = new FlowPane();

        verticalPane.setAlignment(Pos.CENTER_LEFT);
        buttonsPane.setAlignment(Pos.CENTER_RIGHT);

        verticalPane.setPadding(new Insets(20, 20, 20, 20));
        buttonsPane.setPadding(new Insets(20, 00, 00, 00));
        buttonsPane.setHgap(10);
        
        verticalPane.setStyle("-fx-background: lightgray; -fx-font: normal 10pt TimesNewRoman");
        buttonsPane.setStyle("-fx-background: gray; -fx-font: italic 10pt TimesNewRoman");
                    
        EventHandler choiser = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                if (ae.getSource() == saveButton){
                    System.out.println("SAVE");
                    product = new Product();
                    product.setName(nameField.getText());
                    product.setCode(codeField.getText());
                    product.setIdCategory(Integer.parseInt(categoryField.getText()));
                    product.setUnit(unitField.getText());
                    product.setQuantity(Integer.parseInt(quantityField.getText()));
                    product.setIdProvider(Integer.parseInt(providerField.getText()));
//                    SaveIntoDB save = new SaveIntoDB();
//                    save.connectToDB(url, user, password);
//                    save.saveData(product);
//                    save.close();

                    //System.out.println(product.getName());

                    //data.add(product);

                    close();
                }
                if (ae.getSource() == cancelButton){
//                    System.out.println("CANCEL");
                    close();
                }
            }
        };

        saveButton = new Button("Save");
        //saveButton.setDefaultButton(true);
        cancelButton = new Button("Cancel");

        //saveButton.set(new Insets(20, 20 ,0 ,0));

        saveButton.setOnAction(choiser);
        cancelButton.setOnAction(choiser);


        buttonsPane.getChildren().addAll(saveButton, cancelButton);

        verticalPane.getChildren().addAll(nameLabel, nameField
                                        , codeLabel, codeField
                                        , categoryLabel, categoryField
                                        , unitLabel, unitField
                                        , quantityLabel, quantityField
                                        , providerLabel, providerField);


        verticalPane.getChildren().addAll(buttonsPane);



        Scene sceneEdit = new Scene(verticalPane);




        setScene(sceneEdit);
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        primaryStage.toFront();
        setResizable(false);
        //show();
        
    }
    
    public Product getProduct(){
        
        //while (!saveButton.isArmed() || !cancelButton.isArmed()){}
//        System.out.println("gP");
//        
//        Thread t = new Thread(this);
//        t.start();
        
        
        
        close();
        return product;
    }

//    @Override
//    public void run() {
//        System.out.println("run");
//        while (saveButton.isArmed() /*|| !cancelButton.isArmed()*/){}
//        
//    }
    
    

    
    
}
