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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Den
 */
public class TestFX extends Application {
    private ObservableList<String> data = FXCollections.observableArrayList("1", "Name", "3");
    private Button addButton, moveButton, closeButton, saveButton, cancelButton;
    
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
        
        
        
        EventHandler event = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                
                if (ae.getSource() == addButton){
                    
                    System.out.println("ADD");
                    editWindow(primaryStage);
                }
                if (ae.getSource() == moveButton){
                    System.out.println("MOVE");
                    
                }
                if (ae.getSource() == closeButton){
                    Alert exit = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    exit.setHeaderText("The program will be closed.");
                    exit.setContentText("Are you sure?");
                    
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
        
        
        Scene scene = new Scene(grid); //, 300, 300);
        primaryStage.setScene(scene);
        
        primaryStage.setResizable(false);
        
        primaryStage.show();
        
    }
    
    private void editWindow(Stage primaryStage){
        Stage editStage = new Stage();
                    
                    
                    EventHandler choiser = new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent ae){
                            if (ae.getSource() == saveButton){
                                System.out.println("SAVE");
                                editStage.close();
                            }
                            if (ae.getSource() == cancelButton){
                                System.out.println("CANCEL");
                                editStage.close();
                            }
                        }
                    };
                    
                    Label nameLabel = new Label("Product's name");
                    TextField nameField = new TextField();
                    
                    Label codeLabel = new Label("Code");
                    TextField codeField = new TextField();
                    
                    Label itemLabel = new Label("Item");
                    TextField itemField = new TextField();
                    
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
                    
                    
                    saveButton = new Button("Save");
                    cancelButton = new Button("Cancel");
                    
                    //saveButton.set(new Insets(20, 20 ,0 ,0));
                                        
                    saveButton.setOnAction(choiser);
                    cancelButton.setOnAction(choiser);
                    
                    
                    buttonsPane.getChildren().addAll(saveButton, cancelButton);
                    
                    verticalPane.getChildren().addAll(nameLabel, nameField
                                                    , codeLabel, codeField
                                                    , itemLabel, itemField
                                                    , quantityLabel, quantityField
                                                    , providerLabel, providerField);
                    
                    
                    verticalPane.getChildren().addAll(buttonsPane);
                    
                    
                    
                    Scene sceneEdit = new Scene(verticalPane);
                    
                    
                    editStage.setScene(sceneEdit);
                    editStage.initModality(Modality.WINDOW_MODAL);
                    editStage.initOwner(primaryStage);
                    primaryStage.toFront();
                    editStage.setResizable(false);
                    editStage.show();
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
