package intelliscry.controller;

import intelliscry.Main;
import intelliscry.model.CardSetModel;
import intelliscry.util.CardSetUtil;
import intelliscry.wrapper.CardSetWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

public class RootLayoutController {

    // Reference to the main application
    private Main mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty card set.
     */
    @FXML
    private void handleNew() {

        TextInputDialog dialog = new TextInputDialog("New Set");
        dialog.setTitle("New Set Name");
        dialog.setHeaderText("Set Name");
        dialog.setContentText("Please enter a name for the set:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()){
            return;
        }

        mainApp.getCardSetList().clear();
        mainApp.getCardSetList().addAll(new CardSetModel(result.get()));
        mainApp.setCardSetFilePath(null);

    }

    /**
     * Opens a FileChooser to let the user select a card set to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file == null) {
            return;
        }

        CardSetWrapper cardSetWrapper = CardSetUtil.loadCardSetDataFromFile(file);
        if(cardSetWrapper == null){
            return;
        }

        mainApp.dataLoadedFromFile(cardSetWrapper);

        // Save the file path to the registry.
        mainApp.setCardSetFilePath(file);
    }


    /**
     * Saves the file to the card set file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File cardSetFile = mainApp.getCardSetFilePath();
        if (cardSetFile != null) {
            CardSetUtil.saveCardSetDataToFile(mainApp.getCardSetList(), cardSetFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            CardSetUtil.saveCardSetDataToFile(mainApp.getCardSetList(), file);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
