package com.gmail.moroka.mrk.entity.deck;

import com.gmail.moroka.mrk.entity.card.Card;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@Data
public class PlayersHand {

    private LinkedList<Card> hand;

    @Autowired
    @Qualifier("getMainDeckBean")
    private MainDeck mainDeck;

    private int maxSize;

    public PlayersHand() {
        hand = new LinkedList<>();
        this.maxSize = 5;
    }

    @PostConstruct
    private void init(){
        fillHandWithCards();
    }

    //Fills card with hands
    public void fillHandWithCards() {
        if (hand.size() > maxSize) {
            //LOG ERROR
            System.out.println("ERROR! There are more log than expected");
            return;
        }
        if (hand.size() == maxSize) {
            //hand is full
            return;
        }
        int toTake = maxSize - hand.size();
        hand.addAll(mainDeck.getCards(toTake));
    }

}
