package ishop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProductsWindow extends Stage{
    
//    private final String url = "jdbc:mysql://localhost:3306/shop";      
//                               // "jdbc:mysql://77.108.69.15:3306/shop";
//    private final String user = "admin";
//    private final String password = "shop2016";
    
    private Product product;
    private ObservableList<Product> productsData;
    private ObservableList<String> providersData;
    private Button addButton, editButton, moveButton, closeButton;
    private Stage primaryStage;
    
    
    public ProductsWindow(Stage primaryStage){
        this.primaryStage = primaryStage;
        
        init();
    }
    
    
    private void init(){
        
        //ConnectingWindow cw = new ConnectingWindow();
        //cw.run();
        //cw.show();
        
        ProductsLoader loadProducts = new ProductsLoader();
        ProvidersLoader loadProviders = new ProvidersLoader();
        
//        if (loadProducts.connect(url, user, password) == null){
//            Alert error = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
//            error.setTitle("Error");
//            error.setContentText("Error connecting to DB!");
//            error.showAndWait();
//        }
        //cw.close();
        
        productsData = FXCollections.observableArrayList(loadProducts.loadData(DatabaseConnection.getConnection()));
//        loadProviders.connect(url, user, password);

        providersData = FXCollections.observableArrayList(loadProviders.loadData(DatabaseConnection.getConnection()));
        
        
        
        primaryStage.setTitle("Storage");
        
        GridPane grid = new GridPane();
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        //grid.setGridLinesVisible(true);
        
        grid.setStyle("-fx-background-color: lightgray; -fx-font: normal 10pt TimesNewRoman");
        grid.setAlignment(Pos.CENTER);
        
        Text sceneTitle = new Text("Network Storage Interface");
        sceneTitle.setFont(Font.font("Impact", FontWeight.BOLD, 30));
        grid.add(sceneTitle, 0, 0, 5, 1);
        
        //grid.setAlignment(Pos.CENTER);
        
        Label productsList = new Label("Products list");
        grid.add(productsList, 0, 2);
        
        TableView<Product> table = new TableView<>();
        table.setEditable(false);
        
        TableColumn numberColumn = new TableColumn("№");
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn unitColumn = new TableColumn("Unit");
        TableColumn quantityColumn = new TableColumn("Quantity");
        
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        table.setEditable(true);
        table.getColumns().addAll(numberColumn
                                , nameColumn
                                , unitColumn
                                , quantityColumn);
        
        table.setItems(productsData);
        
        grid.add(table, 0, 3, 2, 5);
        
        Label searchProduct = new Label("Search");
        grid.add(searchProduct, 2, 2);
        
        ObservableList<String> search = FXCollections.observableArrayList(
            "idProduct",
            "idCategory",
            "Category",
            "Code",
            "Name",
            "idProvider",
            "Provider"  
        
        );
        
        ComboBox searchCmb = new ComboBox(search); 
        grid.add(searchCmb, 2, 3, 1, 1);
        
        searchCmb.setValue("idProduct");
        
        TextField searchField = new TextField();
        grid.add(searchField, 3, 3, 3, 1);
       
        searchField.textProperty().addListener(new ChangeListener<String>(){
          @Override public void changed(ObservableValue ov, String t, String t1) {
            String searchItem=searchCmb.getSelectionModel().getSelectedItem().toString();
            String searchQuery;
              if (searchItem.equals("Category")){
                 searchQuery= "SELECT * FROM product WHERE idCategory=(SELECT idCategory FROM product_category WHERE categoryName like  '%"+t1+"%')";
                 loadProducts.setSendQuery(searchQuery); 
                  
              } else if (searchItem.equals("Provider")){
                 searchQuery= "SELECT * FROM product WHERE idProvider=(SELECT idProvider FROM provider WHERE companyName like  '%"+t1+"%')";
                 loadProducts.setSendQuery(searchQuery); 
              } else{
                  searchQuery="Select * from product where "+searchItem+" like  '%"+t1+"%'"; 
                  loadProducts.setSendQuery(searchQuery);
                    
                   
              }
              System.out.println(searchQuery);
               productsData = FXCollections.observableArrayList(loadProducts.loadData(DatabaseConnection.getConnection()));
               table.setItems(productsData);
            }    
  
        });
        
        Label singleProduct = new Label("Product");
        grid.add(singleProduct, 2, 4);
        
        VBox vbox = new VBox(1);
        vbox.setAlignment(Pos.BOTTOM_RIGHT);
        Label idProduct = new Label("id Product:");
        Label category = new Label("Category:");
        Label code = new Label("Code:");
        Label name = new Label("Name:");
        Label unit = new Label("Unit:");
        Label quantity = new Label("Quantity:");
        Label provider = new Label("Provider:");
        vbox.getChildren().addAll(idProduct, category, code, name, unit,quantity, provider );
        grid.add(vbox, 2, 5);
        
        ListView<String> productView = new ListView<>();
        productView.setMaxHeight(190);
        grid.add(productView, 3, 5, 3, 2);
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Product pr = (Product) table.getSelectionModel().getSelectedItem();
                ObservableList <String>details = FXCollections.observableArrayList(pr.DataBD(DatabaseConnection.getConnection()));
                productView.setItems(details);
                
                //productView.setCellFactory(new PropertyValueFactory<>(""));
            }
        });
        
        EventHandler event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                
                if (ae.getSource() == addButton){
                    //System.out.println("ADD");
                    //editWindow(primaryStage);
                    product = new EditWindow().editProductData(new Product());
                    System.out.println("PROD"  + product);
                    if (product != null) {
                        productsData.add(product);
                        ProductsSaver save = new ProductsSaver();
                        //save.connect(url, user, password);
                        save.saveData(product, DatabaseConnection.getConnection(), "add");
                        //save.close();
                    }
                }
                if (ae.getSource() == editButton){
                    //System.out.println("Selected product - " + table.getSelectionModel().getSelectedItem());
                    product = new EditWindow().editProductData(table.getSelectionModel().getSelectedItem());
                    
                    if (product != null) {
                        
                        //productsData.sorted();
                        ProductsSaver save = new ProductsSaver();
                        //save.connect(url, user, password);
                        save.saveData(product, DatabaseConnection.getConnection(), "update");
                        table.refresh();
                        //save.close();
                    }
                }
                if (ae.getSource() == moveButton){
                    //System.out.println("MOVE");
                    //editWindow(primaryStage);  // - it's not good
                    // editing into GridPane
                }
                if (ae.getSource() == closeButton){
                    Alert exit = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    exit.setHeaderText("The program will be closed.");
                    exit.setContentText("Are you sure?");
                    //exit.initStyle(StageStyle.UTILITY);
                    //exit.getButtonTypes().add(ButtonType.YES.getButtonData(). );   // how make other default button???
                    if (exit.showAndWait().get() == ButtonType.YES) {
                        DatabaseConnection.close();
                        System.exit(0);
                    }
                }
            }
        };
        
        addButton = new Button("Add product");
        addButton.setOnAction(event);
        GridPane.setValignment(addButton, VPos.BOTTOM);
        grid.add(addButton, 2, 7);
        
        editButton = new Button("Edit product");
        editButton.setOnAction(event);
        GridPane.setValignment(editButton, VPos.BOTTOM);
        grid.add(editButton, 3, 7);
        
        moveButton = new Button("Move product");
        moveButton.setOnAction(event);
        GridPane.setValignment(moveButton, VPos.BOTTOM);
        grid.add(moveButton, 4, 7);
        
        closeButton = new Button("Close");
        closeButton.setOnAction(event);
        GridPane.setValignment(closeButton, VPos.BOTTOM);
        grid.add(closeButton, 5, 7);
        
        Scene scene = new Scene(grid, Color.BLUE); //, 300, 300);
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //primaryStage
        primaryStage.show();
    }
}

