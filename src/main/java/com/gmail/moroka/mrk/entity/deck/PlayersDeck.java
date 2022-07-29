package com.gmail.moroka.mrk.entity.deck;

import com.gmail.moroka.mrk.entity.card.Card;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@Data
public class PlayersDeck {

    private LinkedList<Card> deck;

    @Autowired
    @Qualifier("getMainDeckBean")
    private MainDeck mainDeck;

    public PlayersDeck() {
        //TODO change hardcoded "20"
    }

    @PostConstruct
    private void init(){
        //TODO change hardcoded "20"
        this.deck = mainDeck.getCards(20);
    }

    //Just get upper Card object
    public Card getCard(){
        return deck.getLast();
    }

    //Get upper Card object and delete it form deck
    public Card takeCard(){
        Card result = deck.getLast();
        deck.removeLast();
        return result;
    }

}
