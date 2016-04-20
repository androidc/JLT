/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Den
 */
public class TestFX extends Application {
    private ObservableList<String> data = FXCollections.observableArrayList("1", "Name", "3");
    
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Storage");
        
        
        GridPane grid = new GridPane();
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        //grid.setGridLinesVisible(true);
        
        grid.setAlignment(Pos.CENTER);
        
        Text sceneTitle = new Text("Network Storage Interface");
        sceneTitle.setFont(Font.font("Impact", FontWeight.BOLD, 30));
        grid.add(sceneTitle, 0, 0, 5, 1);
        
        //grid.setAlignment(Pos.CENTER);
        
        Label productsList = new Label("Products list");
        grid.add(productsList, 0, 2);
        
        TableView<String> table = new TableView<>();
        table.setEditable(false);
        table.getColumns().addAll(new TableColumn("№")
                , new TableColumn("Name")
                , new TableColumn("Item")
                , new TableColumn("Quantity"));
        //table.autosize();
        //table.setMaxHeight(200);
        table.setItems(data);
        data.add("COOL");
        
        
        grid.add(table, 0, 3, 2, 3);
        
        Label singleProduct = new Label("Product");
        grid.add(singleProduct, 2, 2);
        
        
        
        ListView<String> productView = new ListView<>();
        productView.setMaxHeight(150);
        grid.add(productView, 2, 3, 3, 1);
        
        //grid.setAlignment(Pos.BASELINE_RIGHT);
        
        
        
        Button addButton = new Button("Add product");
        GridPane.setValignment(addButton, VPos.BOTTOM);
        grid.add(addButton, 2, 5);
        
        Button moveButton = new Button("Move product");
        GridPane.setValignment(moveButton, VPos.BOTTOM);
        grid.add(moveButton, 3, 5);
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                System.exit(0);
            }
        
        });
        GridPane.setValignment(closeButton, VPos.BOTTOM);
        grid.add(closeButton, 4, 5);
        
        
        Scene scene = new Scene(grid); //, 300, 300);
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        
        primaryStage.show();
        
        /*
        Button btn = new Button();
        Label label = new Label();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        
        
        StackPane root = new StackPane();
        //root.setLayoutX(100);
        //root.relocate(10, 100);
        root.getChildren().addAll(btn, label);
        //root.getChildren().add(label);
        label.setText("COOL");
        label.setRotate(45);
        //label.set
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
