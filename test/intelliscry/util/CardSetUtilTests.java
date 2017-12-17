package intelliscry.util;


import intelliscry.model.CardSetModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CardSetUtilTests {

    @Test
    public void shouldSaveSetToAFile(){

        try {
            new File("." + File.separator + "testFiles").mkdirs();
            File file = new File("." + File.separator + "testFiles" + File.separator + "testFile.xml");
            CardSetModel cardSet = new CardSetModel("Test set");

            List<CardSetModel> models = new ArrayList<>();
            models.add(cardSet);
            CardSetUtil.saveCardSetDataToFile(models, file);

            Assertions.assertTrue(file.exists());

        } catch (Exception exception){
            Assertions.fail("Exception thrown in saving to file: " + exception.getMessage());
        }

    }

}
