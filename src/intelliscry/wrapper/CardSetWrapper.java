package intelliscry.wrapper;

import intelliscry.model.CardDefinitionModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "CardSet")
public class CardSetWrapper {

    private List<CardDefinitionModel> cardsInSet;

    @XmlElement(name = "Cards")
    public List<CardDefinitionModel> getCardsInSet() {
        return cardsInSet;
    }

    public void setCardsInSet(List<CardDefinitionModel> cardsInSet) {
        this.cardsInSet = cardsInSet;
    }
}
