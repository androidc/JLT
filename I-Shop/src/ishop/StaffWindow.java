package ishop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

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
        staffTable.setEditable(true);
        
        
        TableColumn firstNameColumn = new TableColumn("FirstName");
        TableColumn lastNameColumn = new TableColumn("LastName");
        TableColumn birthdayColumn = new TableColumn("Birthday");
        TableColumn phoneColumn = new TableColumn("Phone");
        TableColumn workPhoneColumn = new TableColumn("work");
        TableColumn personalPhoneColumn = new TableColumn("personal");
        TableColumn functionColumn = new TableColumn<>("Function");
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
        
        ObservableList staffList = FXCollections.observableArrayList(new PersonsLoader().loadData(DatabaseConnection.getConnection()));
        
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        workPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("workPhone"));
        personalPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("personalPhone"));
        functionColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        staffTable.setItems(staffList);
        staffTable.setEditable(true);
        
        ObservableList staffRole = FXCollections.observableArrayList(new RoleLoader().loadData(DatabaseConnection.getConnection()));
        
        
        functionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(staffRole));
        
        
        
//        functionColumn.setCellFactory(new Callback<TableColumn<Role, String>, TableCell<Role, String>>(){
//            @Override
//            public TableCell<Role, String> call(TableColumn<Role, String> param) {
//                
//                return new TableCell<Role, String>(){
//                    @Override
//                    public void updateItem(String r, boolean empty){
//                        
//                    }
//                };
//            }
//            
//        });
        
        
//        functionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new Callback <TableColumn<Role, Role>, TableCell<Role, Role>>(){
//            
//            @Override
//            public TableCell<Role, Role> call (TableColumn<Role, Role> r){
//                //System.out.println("!!!");
//                return new TableCell<Role, Role>(){
//                    
//                    @Override
//                    public void updateItem(Role r, boolean empty){
//                        super.updateItem(r, empty);
//                        if (empty){
//                            setText(null);
//                        }else{
//                            setGraphic(null);
//                            setText(r.getName() + "---");
//                            System.out.println(r);
//                        }
//                    }
//                };
//            }
//        }));
        //functionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(new RoleLoader().loadData(DatabaseConnection.getConnection()))));
        
        
//        Callback cb = new Callback <Role, String>(){
//            @Override
//            public String call(Role r){
//                //return r.getName();
//                return r.getName(); //{
//                    
////                    @Override
////                    public void updateItem(Role item, boolean empty){
////                        super.updateItem(item, empty);
////                        
////                        if (empty){
////                            setText(null);
////                            
////                        }else{
////                            setText(item.getName());
////                            setGraphic(null);
////                            System.out.println(item.getName());
////                        }
////                    }
////                    
////                };
//            }
//
//            
//
//            
//        };
//        
        //functionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(staffRole));
        
        

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
