package ishop;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Den
 */
public class Main extends Application {
    private final String url = //"jdbc:mysql://localhost:3306/shop";      
                                "jdbc:mysql://77.108.69.15:3306/shop";
    private final String user = "admin";
    private final String password = "shop2016";
    private Product product;

    
    private ObservableList<Product> data; // = FXCollections.observableArrayList();

    

    private Button addButton, moveButton, closeButton, saveButton, cancelButton;
    
    @Override
    public void start(Stage primaryStage) {
        
        //loadDB();
        
        LoadFromDB load = new LoadFromDB();
        if (load.connectToDB(url, user, password) == null){
            Alert error = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
            error.setTitle("Error");
            error.setContentText("Error connecting to DB!");
            error.showAndWait();
        }
        //load.loadData();
        
        data = FXCollections.observableArrayList(load.loadData());
        
        //data = FXCollections.observableArrayList();
        
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
        //table.autosize();
        //table.setMaxHeight(200);
        
        
        //data.add("COOL");
        table.setItems(data);
        //table.setUserData(data);
        
                
        grid.add(table, 0, 3, 2, 3);
        
        
        Label singleProduct = new Label("Product");
        grid.add(singleProduct, 2, 2);
        
        
        
        ListView<String> productView = new ListView<>();
        productView.setMaxHeight(150);
        grid.add(productView, 2, 3, 3, 1);
        
        //grid.setAlignment(Pos.BASELINE_RIGHT);
        
        
        
        EventHandler event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                
                if (ae.getSource() == addButton){
                    
                    System.out.println("ADD");
                    editWindow(primaryStage);
                }
                if (ae.getSource() == moveButton){
                    System.out.println("MOVE");
                    //editWindow(primaryStage);  // - it's not good
                    // editing into GridPane
                }
                if (ae.getSource() == closeButton){
                    Alert exit = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    exit.setHeaderText("The program will be closed.");
                    exit.setContentText("Are you sure?");
                    //exit.initStyle(StageStyle.UTILITY);
                    //exit.getButtonTypes().add(ButtonType.YES.getButtonData(). );   // how make other default button???
                    if (exit.showAndWait().get() == ButtonType.YES) System.exit(0);
                }
            }
        };
        
        addButton = new Button("Add product");
        addButton.setOnAction(event);
        GridPane.setValignment(addButton, VPos.BOTTOM);
        grid.add(addButton, 2, 5);
        
        moveButton = new Button("Move product");
        moveButton.setOnAction(event);
        GridPane.setValignment(moveButton, VPos.BOTTOM);
        grid.add(moveButton, 3, 5);
        
        closeButton = new Button("Close");
        closeButton.setOnAction(event);
        GridPane.setValignment(closeButton, VPos.BOTTOM);
        grid.add(closeButton, 4, 5);
        
        
        Scene scene = new Scene(grid, Color.BLUE); //, 300, 300);
        primaryStage.setScene(scene);
        
        
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //primaryStage
        primaryStage.show();
        
    }
    
    private void editWindow(Stage primaryStage){
        
        product = EditWindow.getProductData();
        if (product != null) {
            data.add(product);
            SaveIntoDB save = new SaveIntoDB();
            save.connectToDB(url, user, password);
            save.saveData(product);
            save.close();
        }
        
    }

    
    private void loadDB() {
        
    }
    
    
//---

    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
