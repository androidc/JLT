package ishop;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


public class EditWindow {
    private static Button saveButton, cancelButton;
    private static Product product = new Product();
    
    public static Product getProductData(ObservableList <Provider> providers){
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
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
        //TextField providerField = new TextField();
        ComboBox providerField = new ComboBox();
        providerField.setItems(providers);
        
//        providerField.setCellFactory(new Callback<ListView<Provider>, ListCell<Provider>>(){
//            @Override
//            public ListCell<Provider> call(ListView<Provider>() param){
//                
//                return new ListCell<Provider>(){
//                    
//                };
//            }
//        });

        //providerField.getSelectionModel().selectFirst();
        
        providerField.setPromptText("Choise provider");
        providerField.setEditable(true);
    
        providerField.setCellFactory(new Callback<ListView<Provider>, ListCell<Provider>>() {
            @Override
            public ListCell<Provider> call(ListView<Provider> param) {
                
                return new ListCell<Provider>() {
                    @Override
                    public void updateItem(Provider item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        if (!empty) {
                            //setText(item.getName());
                            setText(item.getName());
                            setGraphic(null);
                        }//else setText("***");
                    }
                };
                
            }
        });
        
 
        

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
                    product.setIdProvider(Integer.parseInt(providerField.getSelectionModel().getSelectedItem().toString()));
                    
                    window.close();
                }
                if (ae.getSource() == cancelButton){
                    //System.out.println("CANCEL");
                    product = null;
                    window.close();
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
                     
        //Scene sceneEdit = new Scene(verticalPane);
               
        window.setScene(new Scene(verticalPane));
        window.setResizable(false);
        window.showAndWait();
        
        return product;
    }
    
}
