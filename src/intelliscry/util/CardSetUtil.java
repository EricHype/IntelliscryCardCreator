package intelliscry.util;

import intelliscry.Main;
import intelliscry.model.CardSetModel;
import intelliscry.wrapper.CardSetWrapper;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

public class CardSetUtil {

    public static CardSetWrapper loadCardSetDataFromFile(File file){

        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSetWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CardSetWrapper wrapper = (CardSetWrapper) um.unmarshal(file);

            // Save the file path to the registry.
            setPersonFilePath(file);

            return wrapper;

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }

        return null;
    }

    public static void saveCardSetDataToFile(CardSetModel model, File file){
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CardSetWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CardSetWrapper wrapper = new CardSetWrapper();
            wrapper.setSetName(model.getSetName());
            wrapper.setCardsInSet(model.getCards());

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public static void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public static File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

}
