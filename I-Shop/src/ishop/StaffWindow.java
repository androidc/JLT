package ishop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StaffWindow extends Stage{
    
    public StaffWindow(){
        
        init();
    }
    
    
    
    private void init(){
        
        BorderPane mainPane = new BorderPane();
        FlowPane buttonsPane = new FlowPane();
        
        mainPane.setPadding(new Insets(10, 10 ,10 ,10));
        buttonsPane.setHgap(10);
        buttonsPane.setPadding(new Insets(10, 0, 0, 0));
        buttonsPane.setAlignment(Pos.CENTER_RIGHT);
        
        TableView staffTable = new TableView();
        staffTable.setPrefSize(800, 400);
        
        TableColumn firstNameColumn = new TableColumn("FirstName");
        TableColumn lastNameColumn = new TableColumn("LastName");
        TableColumn birthdayColumn = new TableColumn("Birthday");
        TableColumn phoneColumn = new TableColumn("Phone");
        TableColumn workPhoneColumn = new TableColumn("work");
        TableColumn personalPhoneColumn = new TableColumn("personal");
        TableColumn functionColumn = new TableColumn("Function");
        TableColumn addressColumn = new TableColumn("Address");
        TableColumn loginColumn = new TableColumn("Login");
        TableColumn passwordColumn = new TableColumn("Password");
        
        phoneColumn.getColumns().addAll(workPhoneColumn, personalPhoneColumn);
        
        staffTable.getColumns().addAll(firstNameColumn
                                        , lastNameColumn
                                        , birthdayColumn
                                        , phoneColumn
                                        , functionColumn
                                        , addressColumn
                                        , loginColumn
                                        , passwordColumn);
        
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        
        EventHandler events = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if (e.getSource() == saveButton){
                    
                }
                if (e.getSource() == cancelButton){
                    
                }
            }
        };
        
        saveButton.setOnAction(events);
        cancelButton.setOnAction(events);
        
        buttonsPane.getChildren().addAll(saveButton, cancelButton);
        
        mainPane.setCenter(staffTable);
        mainPane.setBottom(buttonsPane);
        
        setMinWidth(100);
        setMinHeight(100);
        
        setScene(new Scene(mainPane));
        minWidthProperty().set(400);
        minHeightProperty().set(300);
        show();
    }
    
    
}
