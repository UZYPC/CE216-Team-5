package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.ToDoubleBiFunction;

public class TabPaneDeneme implements Initializable {
    @FXML
    private TabPane tabPane;

    /**Main Page Components**/
    @FXML
    private Tab mainTab;


    /**Item Page Components**/
    @FXML
    private Tab itemTab;
    @FXML
    private TableView<Items> itemsAttrTableView;
    @FXML
    private TableColumn<Items, String> itemAttrValue;
    @FXML
    private TableColumn<Types, String> typeAttrName;
    @FXML
    private Types types;
    @FXML
    private TextField enterItemsAttrValue;
    @FXML
    private TextField itemName;
    @FXML
    private ChoiceBox<String> tagNameChoiceBox;//TextField tagName da olabilir
    @FXML
    private ChoiceBox<String> typeNameChoiceBox;
    @FXML
    private VBox typeTitledPaneVbox;
    @FXML
    private VBox tagTitledPaneVbox;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button addValue;
    @FXML
    private Button deleteValue;
    @FXML
    private Button editValue;
    @FXML
    private Button save;
    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane typesTitledPane;
    @FXML
    private TitledPane tagsTitledPane;
//    @FXML
//    Item item;


    /**Type Page Components**/
    @FXML
    private Tab typeTab;
    @FXML
    private TextField typesTextField;
    @FXML
    private TextField attrNamesEntryTextField;
    @FXML
    private TableView<Types> attrNameTableView;
    @FXML
    private TableColumn<Types,String> typeAttributesNames;
    @FXML
    private ListView<Items> typesListView=new ListView<>();
    @FXML
    private ListView<String> tagsListView=new ListView<>();

    ObservableList<Types> list2 = FXCollections.observableArrayList();

    /**Tag Page Components**/

    @FXML
    private Tab tagTab;
    @FXML
    private TextField tagsTextField;



    /**Main Page Methods**/
    public void goToNewTypePage(){
        tabPane.getSelectionModel().select(typeTab);
    }
    public void goToNewItemPage(){
        tabPane.getSelectionModel().select(itemTab);
    }
    public void goToNewTagPage(){
        tabPane.getSelectionModel().select(tagTab);
    }

    /** Item Page Methods **/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeAttributesNames.setCellValueFactory(new PropertyValueFactory<Types, String>("typeAttributesNames"));
        this.itemAttrValue.setCellValueFactory(new PropertyValueFactory<Items,String>("attributeValues"));
        attrNameTableView.setItems(list2);
//        this.itemsAttrTableView.setOnMouseClicked((event) -> {
//            if (event.getClickCount() == 2) {
//                Items selectedItem = this.itemsAttrTableView.getSelectionModel().getSelectedItem();
//                this.itemAttrValue.setText(selectedItem.getAttributeValues());
//            }
//        });

    }
    public void addAttributeValue() {
        if(!this.enterItemsAttrValue.getText().isEmpty()) {
            String value = this.enterItemsAttrValue.getText();
            Items item = new Items(value);
            this.itemsAttrTableView.getItems().add(item);
            this.enterItemsAttrValue.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
    }
    public void deleteAttributeValue() {
        Items item = this.itemsAttrTableView.getSelectionModel().getSelectedItem();
        this.itemsAttrTableView.getItems().remove(item);
    }
    public void editAttributeValue() {
        Items selectedItem = itemsAttrTableView.getSelectionModel().getSelectedItem();
        if (enterItemsAttrValue.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
        else {
            selectedItem.setAttributeValues(enterItemsAttrValue.getText());
        }
        itemsAttrTableView.refresh();
    }




    /** Type Page Methods **/
    public void typeAktarma(){

        Types types= new Types(typesTextField.getText(),list2);//list2 yerine başka birşey geliyo da olabilir
        types.getTypesTitledPane().setText(typesTextField.getText());
        typeTitledPaneVbox.getChildren().addAll(types.getTypesTitledPane());
        typeNameChoiceBox.getItems().addAll(typesTextField.getText());
        Types.typesArrayList.add(types);
        typesTextField.clear();
        attrNameTableView.getItems().clear();
        attrNameTableView.refresh();

    }

    public void addAttributeNameToAttrNameTableView(){
        if (!attrNamesEntryTextField.getText().isEmpty()){
            String attrNames = attrNamesEntryTextField.getText();
            Types type = new Types(attrNames);
            attrNameTableView.getItems().add(type);
            attrNamesEntryTextField.clear();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute name");
            alert.showAndWait();
        }
    }


    public void deleteAttributeNameFromAttrNameTableView(){
        this.attrNameTableView.getItems().remove(this.attrNameTableView.getSelectionModel().getSelectedItem());
    }

    public void editAttributeNameFromAttrNameTableView(){
        Types selectedItem = attrNameTableView.getSelectionModel().getSelectedItem();
        selectedItem.setTypeAttributesNames(attrNamesEntryTextField.getText());
        this.attrNameTableView.refresh();

    }

    /** Tag Page Methods **/
    public void tagAktarma(){

        Tags tag = new Tags(tagsTextField.getText());
        tag.getTagsTitledPane().setText(tagsTextField.getText());
       // titledPane.setContent(tagsListView);
        tagTitledPaneVbox.getChildren().addAll(tag.getTagsTitledPane());
        tagNameChoiceBox.getItems().addAll(tagsTextField.getText());
        tagsTextField.clear();
        Tags.tagsArrayList.add(tag);



    }




    /** deneme methodları **/

    public void createItem(){
        String itemsName =itemName.getText();
        String tagsName = tagNameChoiceBox.getSelectionModel().getSelectedItem();
        String typesName = typeNameChoiceBox.getSelectionModel().getSelectedItem();
        Tags itemsTag= new Tags("");
        Types itemsType= new Types("");
        for(int i =0;i<Tags.tagsArrayList.size();i++){
            if(Tags.tagsArrayList.get(i).getTagName().equals(tagsName)){
                itemsTag = Tags.tagsArrayList.get(i);
            }
        }
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(typesName)){
                itemsType = Types.typesArrayList.get(i);
            }

        }
        if (itemsType.getTypeName().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select a type or create a type");
            alert.showAndWait();
            return;
        }
        if(itemsTag.equals(new Tags(""))){
            Items item = new Items( itemsType, itemsName, "attrs");
            //TODO: attribute valueları observable list olarak almamız gerek. Items classında private observale list -
            //TODO: itemattrvalue variableı oluşturup constructor oluşturulacak.

            itemsType.getTypesItems().add(item);


            itemsType.getTypesTitledPane().setContent(item.getItemTypeListView());
            item.getItemTypeListView().getItems().add(item.getItemName());

            itemName.clear();

        }

        else {
            Items item = new Items(itemsTag, itemsType, itemsName, "attrs");
            //TODO: attribute valueları observable list olarak almamız gerek. Items classında private observale list -
            //TODO: itemattrvalue variableı oluşturup constructor oluşturulacak.

            itemsType.getTypesItems().add(item);
            itemsTag.getTagsItems().add(item);


            itemsTag.getTagsTitledPane().setContent(item.getItemTagListView());
            item.getItemTagListView().getItems().add(item.getItemName());


            itemsType.getTypesTitledPane().setContent(item.getItemTypeListView());
            item.getItemTypeListView().getItems().add(item.getItemName());

            itemName.clear();
        }

        }

        public void showTagsArraylist(){
        for(int i=0;i<Tags.tagsArrayList.size();i++){
            tagsListView.getItems().add(Tags.tagsArrayList.get(i).getTagName());


        }

        }





    }



