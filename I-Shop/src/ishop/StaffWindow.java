package ishop;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class StaffWindow extends Stage{
    private TableView staffTable;
    
    
    
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
        
        staffTable = new TableView();
        staffTable.setPrefSize(800, 400);
        staffTable.setEditable(true);
        
        
        TableColumn<Person, String> firstNameColumn = new TableColumn<>("FirstName");
        TableColumn<Person, String> lastNameColumn = new TableColumn<>("LastName");
        TableColumn<Person, Date> birthdayColumn = new TableColumn<>("Birthday");
        TableColumn               phoneColumn = new TableColumn("Phone");
        TableColumn<Person, String> workPhoneColumn = new TableColumn<>("work");
        TableColumn<Person, String> personalPhoneColumn = new TableColumn<>("personal");
        TableColumn<Role, String> functionColumn = new TableColumn<>("Function");
        TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
        TableColumn<Person, String> loginColumn = new TableColumn<>("Login");
        TableColumn<Person, String> passwordColumn = new TableColumn<>("Password");
        
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
        
        final StringConverter<Role> converter = new StringConverter<Role>() {
            @Override
            public String toString(Role p){
                return p.getName();
            }
            @Override
            public Role fromString(String s) {
            return null; 
            }
        };


        
        functionColumn.setCellFactory(new Callback<TableColumn<Role, String>, TableCell<Role, String>>() {
            @Override
            public ComboBoxTableCell<Role, String> call(TableColumn<Role, String> param) {
                //System.out.println(param.getColumns().get(0));
                return new ComboBoxTableCell<Role, String>(staffRole) {
                    
                    @Override
                    public void updateItem(String r, boolean e){
                        super.updateItem(r, e);
                        if(e){
                            setText(null);
                        }else{
                            setText(r);
                        }
                    }
                };
            }
        
        });
        
        //functionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(staffRole));
        
//        functionColumn.setCellFactory(new Callback<TableColumn<Role, String>, TableCell<Role, String>>() {
//    @Override
//    public TableCell<Role, String> call(TableColumn<Role, String> p) {
//        
//        final Map<String, Product> products = new HashMap();
//        Iterator<Product> productsi = staffRole.iterator();
//        while(productsi.hasNext()) {
//            Product product = productsi.next();
//            products.put(product.getName(), product);
//        }
//
//        ComboBoxTableCell cell = new ComboBoxTableCell(FXCollections.observableArrayList(products.keySet())){
//            @Override
//            public void updateItem(Object item, boolean empty) {
//                super.updateItem(item, empty);
//                if(item != null) {
//                    //functionColumn.getTableView().getItems().get(getIndex()).  setProduct(products.get(item.toString()));
//                }
//            }
//        };
//        cell.setAlignment(Pos.CENTER);
//        return cell;
//    }
//});
        
//        functionColumn.setOnEditCommit(new EventHandler<CellEditEvent<Role, String>>(){
//            @Override
//            public void handle(CellEditEvent<Role, String> event) {
//                //String nv = event.getNewValue();
//                //System.out.println(nv /*+ " / " + Role.nameToId(event.getNewValue())*/ );
//                System.out.println("Event - " + event.getNewValue());
//                
//                ((Role)event.getTableView().getItems().get(event.getTablePosition().getRow()))
//                        .setIdRole(
//                                Role.nameToId(event.getNewValue()) );   //event.getTableView().getEditingCell().getRow()
//                
//                for (int i = 0 ; i < staffTable.getItems().size() ; i++){
//                    //Person p = null;//(Person)(staffTable.getItems().get(i));
//                    System.out.println(staffTable.getItems().get(i));
//                    //event.getTablePosition().getRow();
//                }
//            }
//            
//        });
        
        //ComboBoxTableCell cb = new ComboBoxTableCell(staffRole);
//        functionColumn.setCellFactory(new Callback<TableColumn<Role, String>, ComboBoxTableCell<Role, String>>(){
//            @Override
//            public ComboBoxTableCell<Role, String> call(TableColumn<Role, String> param) {
//                return new ComboBoxTableCell<Role, String>(){
//                    public void updateItem(Role r, boolean e){
//                        //super.updateItem(r, e);
//                        setText(r.getName());
//                    }
//                };
//            }
//            
//        });
        //functionColumn.setCellFactory(ComboBoxTableCell.<Role, String>forTableColumn(staffRole));
        
        
        //System.out.println(cb.getText());
        
//        functionColumn.setOnEditCommit((TableColumn.<Person, String>CellEditEvent e) -> {
//            //String nv = e.
//        });
//        
//        System.out.println();

//        functionColumn.setCellFactory(new Callback<TableColumn<Role, Role>, ComboBoxTableCell<Role, Role>>(){
//            @Override
//            public ComboBoxTableCell<Role, Role> call(TableColumn<Role, Role> param) {
//                //System.out.println(param.getText());
//                
////                ComboBoxTableCell cb = new ComboBoxTableCell<Role, Role>(){
////                    @Override
////                    public void updateItem(Role r, boolean empty){
////                        super.updateItem(r, empty);
////                        if (empty){
////                            setText(null);
////                        } else{
////                            setGraphic(null);
////                            setText(r.getName());
////                        }
////                    }
////                };
////                cb.setEditable(true);
//                return new ComboBoxTableCell<Role, String>(){
//                    @Override
//                    public void updateItem(Role r, boolean empty){
//                        
//                        super.updateItem(r, empty);
//                        if (empty){
//                            setText(null);
//                        } else{
//                            setGraphic(null);
//                            setText(r.getName());
//                        }
//                    }
//                };
//            }
//            
//        });
//        
    
        
        
//        functionColumn.setCellFactory(new Callback<TableColumn<Role, Role>, TableCell<Role, Role>>(){
//            @Override
//            public TableCell<Role, Role> call(TableColumn<Role, Role> param) {
//                
//                return new TableCell<Role, Role>(){
//                    @Override
//                    public void updateItem(Role r, boolean empty){
//                        super.updateItem(r, empty);
//                        if (empty){
//                            setText(null);
//                        }else{
//                            //setGraphic(null);
//                            setText(r.getName());
//                        }
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
                    
                    for (int it = 0 ; it < staffTable.getItems().size(); it++) {
                        Person p = (Person) staffTable.getItems().get(it);
                        System.out.println(p.getIdRole());
                    }
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
