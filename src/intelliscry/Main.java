package intelliscry;

import intelliscry.controller.RootLayoutController;
import intelliscry.model.CardDefinitionModel;
import intelliscry.model.CardSetModel;
import intelliscry.wrapper.CardSetWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import static intelliscry.util.CardSetUtil.getPersonFilePath;
import static intelliscry.util.CardSetUtil.loadCardSetDataFromFile;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<CardSetModel> cardSetList = FXCollections.observableArrayList();
    private ObservableList<CardDefinitionModel> cardsList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initRootLayout();
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadCardSetDataFromFile(file);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


    public void setPrimaryStageTitle(String title){
        primaryStage.setTitle(title);
    }

    public ObservableList<CardSetModel> getCardSetList() {
        return cardSetList;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setCardSetFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            this.primaryStage.setTitle("Card Editor - " + file.getName());
        } else {
            prefs.remove("filePath");
            this.primaryStage.setTitle("Card Editor");
        }
    }

    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getCardSetFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        }
        return null;
    }



    /**
     * Reacts to data loaded from the file. The current card set data will
     * be replaced.
     *
     * @param cardSetWrapper
     */
    public void dataLoadedFromFile(CardSetWrapper cardSetWrapper) {
            cardSetList.clear();
            cardSetList.addAll(cardSetWrapper.getModelData());
    }

}
