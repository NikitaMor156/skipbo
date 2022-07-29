package com.gmail.moroka.mrk.entity.deck;

import com.gmail.moroka.mrk.entity.card.Card;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.LinkedList;

@Data
public class PlayedDeck {

    private LinkedList<Card> deck;

    @Autowired
    @Qualifier("getMainDeckBean")
    private MainDeck mainDeck;

    public PlayedDeck() {
        deck = new LinkedList<>();
    }


    //Returns all cards from deck and clears it
    public LinkedList<Card> getAllCardsWithShuffle(){
        LinkedList<Card> result = (LinkedList<Card>) deck.clone();
        deck.clear();
        Collections.shuffle(result);
        return result;
    }

    //Put cards to played deck
    public void add(LinkedList<Card> cards){
        deck.addAll(cards);
    }


    @Override
    public String toString() {
        return "PlayedDeck{" +
                "deck=" + deck +
                ", mainDeck=" + mainDeck.getDeck() +
                '}';
    }
}
