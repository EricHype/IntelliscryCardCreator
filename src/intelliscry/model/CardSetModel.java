package intelliscry.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class CardSetModel {

    private final StringProperty setName;
    private final ListProperty<CardDefinitionModel> cards;


    public CardSetModel(){
        this(null);
    }

    public CardSetModel(String setName){
        this.setName = new SimpleStringProperty(setName);
        this.cards = new SimpleListProperty<>();
    }


    public String getSetName() {
        return setName.get();
    }

    public StringProperty setNameProperty() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName.set(setName);
    }

    public ObservableList<CardDefinitionModel> getCards() {
        return cards.get();
    }

    public ListProperty<CardDefinitionModel> cardsProperty() {
        return cards;
    }

    public void setCards(ObservableList<CardDefinitionModel> cards) {
        this.cards.set(cards);
    }
}
