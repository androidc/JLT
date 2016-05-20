package ishop;

import com.mysql.jdbc.StringUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class StaffWindow extends Stage {

    private TableView staffTable;

    public StaffWindow() {

        init();
    }

    private void init() {

        BorderPane mainPane = new BorderPane();
        FlowPane buttonsPane = new FlowPane();

        mainPane.setPadding(new Insets(10, 10, 10, 10));
        buttonsPane.setHgap(10);
        buttonsPane.setPadding(new Insets(10, 0, 0, 0));
        buttonsPane.setAlignment(Pos.CENTER_RIGHT);

        staffTable = new TableView();
        staffTable.setPrefSize(800, 400);
        staffTable.setEditable(true);

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("FirstName");
        TableColumn<Person, String> lastNameColumn = new TableColumn<>("LastName");
        TableColumn<Person, Date> birthdayColumn = new TableColumn<>("Birthday");
        TableColumn phoneColumn = new TableColumn("Phone");
        TableColumn<Person, String> workPhoneColumn = new TableColumn<>("work");
        TableColumn<Person, String> personalPhoneColumn = new TableColumn<>("personal");
        TableColumn functionColumn = new TableColumn<>("Function");
        TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
        TableColumn<Person, String> loginColumn = new TableColumn<>("Login");
        TableColumn<Person, String> passwordColumn = new TableColumn<>("Password");

        phoneColumn.getColumns().addAll(workPhoneColumn, personalPhoneColumn);

        staffTable.getColumns().addAll(firstNameColumn, lastNameColumn, birthdayColumn, phoneColumn, functionColumn, addressColumn, loginColumn, passwordColumn);

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
        
        firstNameColumn.setPrefWidth(150);
        lastNameColumn.setPrefWidth(150);
        birthdayColumn.setPrefWidth(100);
        personalPhoneColumn.setPrefWidth(100);
        workPhoneColumn.setPrefWidth(100);
        functionColumn.setPrefWidth(150);
        addressColumn.setPrefWidth(200);
        loginColumn.setPrefWidth(100);
        passwordColumn.setPrefWidth(100);
        
        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = new Callback<TableColumn<Person, String>, TableCell<Person, String>>(){
            @Override
            public TableCell call(TableColumn t){
                return new EditingCell();
            }
        };
        
        firstNameColumn.setEditable(true);
        firstNameColumn.setCellFactory(cellFactory);
        firstNameColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstName(t.getNewValue());
                    }    
                }
        );
        
        lastNameColumn.setEditable(true);
        lastNameColumn.setCellFactory(cellFactory);
        lastNameColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastName(t.getNewValue());
                    }    
                }
        );
        
        //--
        
        personalPhoneColumn.setEditable(true);
        personalPhoneColumn.setCellFactory(cellFactory);
        personalPhoneColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setPersonalPhone(t.getNewValue());
                    }    
                }
        );
        
        workPhoneColumn.setEditable(true);
        workPhoneColumn.setCellFactory(cellFactory);
        workPhoneColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setWorkPhone(t.getNewValue());
                    }    
                }
        );
        
        addressColumn.setEditable(true);
        addressColumn.setCellFactory(cellFactory);
        addressColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAddress(t.getNewValue());
                    }    
                }
        );
        
        loginColumn.setEditable(true);
        loginColumn.setCellFactory(cellFactory);
        loginColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setLogin(t.getNewValue());
                    }    
                }
        );
        
        passwordColumn.setEditable(true);
        passwordColumn.setCellFactory(cellFactory);
        passwordColumn.setOnEditCommit(
                new EventHandler<CellEditEvent<Person, String>>(){
                    @Override
                    public void handle(CellEditEvent<Person, String> t){
                        ((Person)t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassword(t.getNewValue());
                    }    
                }
        );

        staffTable.setItems(staffList);
        staffTable.setEditable(true);

        ObservableList staffRole = FXCollections.observableArrayList(new RoleLoader().loadData(DatabaseConnection.getConnection()));

//        final StringConverter<Role> converter = new StringConverter<Role>() {
//            @Override
//            public String toString(Role p){
//                return p.getName();
//            }
//            @Override
//            public Role fromString(String s) {
//            return null; 
//            }
//        };
        //ComboBox roleSelector;// = new ComboBox(staffRole);
//        functionColumn.setCellFactory(new Callback<TableColumn<Role, String>, TableCell<Role, String>>() {
//            @Override
//            public ComboBoxTableCell<Role, String> call(TableColumn<Role, String> param) {
//                //System.out.println(param.getColumns().get(0));
//                return new ComboBoxTableCell<Role, String>() {
//                    
//                    @Override
//                    public void updateItem(String r, boolean e){
//                        //super.updateItem(r, e);
//                        if(e){
//                            setText(null);
//                        }else{
//                            //roleSelector.setValue(4);
//                            //roleSelector = new ComboBox(staffRole);
//                            setGraphic(new ComboBox(staffRole));
//                            //setText(r);
//                        }
//                    }
//                };
//            }
//        
//        });
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
//        functionColumn.setCellFactory(new Callback<TableColumn<Person, String>, ComboBoxTableCell<String, Role>>(){
//            @Override
//            public ComboBoxTableCell<String, Role> call(TableColumn<Person, String> param) {
//                return new ComboBoxTableCell<String, Role>(staffRole){
//                    @Override
//                    public void updateItem(Role r, boolean e){
//                        super.updateItem(r, e);
//                        if (r != null){
//                            setText(r.getName());
//                            //System.out.println("param - " + param.getCellData());
//                        }
//                    }
//                };
//            }
//            
//        });
        functionColumn.setCellFactory((Callback) ComboBoxTableCell.<Role, String>forTableColumn(staffRole));
//                .call(
//                new Callback<TableColumn<Person, String>, ComboBoxTableCell<String, Role>>() {
//                    @Override
//                    public ComboBoxTableCell<String, Role> call(TableColumn<Person, String> param) {
//                        return new ComboBoxTableCell<String, Role>(){
//                            @Override
//                            public void updateItem(Role r, boolean e){
//                                if (r != null){
//                                    setText(r.getName());
//                                }
//                            }
//                        };
//                                
//                    }
//
//                })
//        );

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

        EventHandler events = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (e.getSource() == saveButton) {

                    for (int it = 0; it < staffTable.getItems().size(); it++) {
                        Person p = (Person) staffTable.getItems().get(it);
                        System.out.println(p.getIdRole() + " - " + p.getFirstName());
                    }
                    
                    System.out.println("nameColumn - " + firstNameColumn.getCellObservableValue(0));
                    System.out.println("functionColumn - " + functionColumn.getCellObservableValue(0));
                }
                if (e.getSource() == cancelButton) {

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
    
    
    
    class EditingCell extends TableCell<Person, String>{
        private TextField textField;
 
        public EditingCell() {
        }
 
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(null);
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
        
    

}
