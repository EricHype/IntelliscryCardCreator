package intelliscry.wrapper;

import intelliscry.model.CardDefinitionModel;
import intelliscry.model.CardSetModel;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "CardSet")
public class CardSetWrapper {

    private List<CardDefinitionModel> cardsInSet;
    private String setName;

    @XmlElement(name = "Cards")
    public List<CardDefinitionModel> getCardsInSet() {
        return cardsInSet;
    }

    public void setCardsInSet(List<CardDefinitionModel> cardsInSet) {
        this.cardsInSet = cardsInSet;
    }

    @XmlAttribute
    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public CardSetModel getModelData(){
        CardSetModel model = new CardSetModel(setName);
        if(cardsInSet != null) {
            model.getCards().addAll(cardsInSet);
        }
        return model;
    }

}
