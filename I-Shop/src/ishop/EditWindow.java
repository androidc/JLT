package ishop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private TextField nameField, codeField, unitField, quantityField;
    private ComboBox categoryField, providerField;
    private Button saveButton, cancelButton;
    
    private boolean correct = true;
    private Product product;
    
    
    public Product editProductData(Product prd, ObservableList <Provider> providers){
        product = prd;
        
        if (product == null) this.product = new Product();
        
        init(product, providers);
        
        nameField.setText(product.getName());
        codeField.setText(product.getCode());
        categoryField.setVisibleRowCount(0);             //setText(String.valueOf(product.getIdCategory()));
        unitField.setText(product.getUnit());
        quantityField.setText(String.valueOf(product.getQuantity()));
        providerField.setVisibleRowCount(0);
        System.out.println("product name - " + product.getName());
        
        return product;
    }
    
    
    private void init(Product prd, ObservableList <Provider> providers){
        product = prd;
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label nameLabel = new Label("Product's name");
        nameField = new TextField(product.getName());

        Label codeLabel = new Label("Code");
        codeField = new TextField(product.getCode());

        Label categoryLabel = new Label("Category");
        categoryField = new ComboBox();                     //TextField(String.valueOf(product.getIdCategory()));
        ObservableList productsCategory = FXCollections.observableArrayList(new ProductsCategoryLoader().loadData(DatabaseConnection.getConnection()));
        categoryField.setItems(productsCategory);
        categoryField.setPromptText("Choise products category");
        categoryField.setEditable(true);
        categoryField.setCellFactory(new Callback<ListView<ProductsCategory>, ListCell<ProductsCategory>>(){
            @Override
            public ListCell<ProductsCategory> call(ListView<ProductsCategory> param) {
                return new ListCell<ProductsCategory>(){
                    @Override
                    public void updateItem(ProductsCategory item, boolean empty){
                        super.updateItem(item, empty);
                        if (item != null){
                            setText(item.getName());
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        categoryField.getSelectionModel().select(product.getIdCategory());
        
        Label unitLabel = new Label("Item");
        unitField = new TextField(product.getUnit());

        Label quantityLabel = new Label("Quantity");
        quantityField = new TextField(String.valueOf(product.getQuantity()));
        quantityField.textProperty().addListener(new ChangeListener<String>(){
            
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (isInteger(newValue)){
                    quantityField.setStyle("-fx-text-fill: black");
                    correct = true;
                } else{
                    quantityField.setStyle("-fx-text-fill: red");
                    correct = false;
                }
            }
        });

        Label providerLabel = new Label("Provider");
        //TextField providerField = new TextField();
        providerField = new ComboBox();
        providerField.setItems(providers);
        
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
        providerField.getSelectionModel().select(product.getIdProvider());
        
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
                if (ae.getSource() == saveButton && correct){
                    //System.out.println("SAVE");
                    //product = new Product();
                    product.setName(nameField.getText());
                    product.setCode(codeField.getText());
                    product.setIdCategory(categoryField.getSelectionModel().getSelectedIndex() + 1);
                    product.setUnit(unitField.getText());
                    product.setQuantity(Integer.parseInt(quantityField.getText()));
                    product.setIdProvider(providerField.getSelectionModel().getSelectedIndex() + 1);
                    
                    window.close();
                }
                if (ae.getSource() == cancelButton){
                    System.out.println("CANCEL");
                    product = null;
                    System.out.println("PRODUCT = " + product);
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
    }
    
    private boolean isInteger(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
}
