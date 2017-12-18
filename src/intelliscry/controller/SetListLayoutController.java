package intelliscry.controller;

import intelliscry.Main;
import intelliscry.model.CardSetModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.smartcardio.Card;

public class SetListLayoutController {

    private Main mainApp;

    @FXML
    private TableView<CardSetModel> cardSetTable;
    @FXML
    private TableColumn<CardSetModel, String> setNameColumn;

    public SetListLayoutController() {
    }


    @FXML
    private void initialize() {
        this.setNameColumn.setCellValueFactory((cellData) ->  cellData.getValue().setNameProperty());

        /*
        this.showPersonDetails((Person)null);
        this.personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.showPersonDetails(newValue);
        });
        */
    }


    @FXML
    private void handleNewCard() {
    }

    @FXML
    private void handleEditCard() {
    }

    @FXML
    private void handleDeleteCard() {
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        this.cardSetTable.setItems(mainApp.getCardSetList());
    }
}
