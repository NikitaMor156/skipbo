package com.gmail.moroka.mrk.entity.deck;

import com.gmail.moroka.mrk.entity.card.Card;
import com.gmail.moroka.mrk.entity.card.NumberCard;
import com.gmail.moroka.mrk.entity.card.SkipboCard;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.LinkedList;

@Data
public class MainDeck {

    private LinkedList<Card> deck;

    @Autowired
    @Qualifier("getPlayedDeckBean")
    private PlayedDeck playedDeck;

    public MainDeck(int countOfNumberCardsStacks, int countOfSkipboCards) {
        deck = init(countOfNumberCardsStacks, countOfSkipboCards);
    }

    //Used only once in constructor
    //Returns a new generated deck
    private LinkedList<Card> init(int numCardsStacks, int skipboCards) {
        LinkedList<Card> result = new LinkedList<>();
        //Add number cards to result list
        for (int i = 0; i < numCardsStacks; i++) {
            for (int j = 1; j <= 12; j++) {
                result.add(new NumberCard(j));
            }
        }
        //Add SkipBo cards to list
        for (int i = 0; i < skipboCards; i++) {
            result.add(new SkipboCard());
        }
        //Shuffle and return
        Collections.shuffle(result);
        return result;

    }

    //Returns count of cards from top of the deck
    //Deletes returned cards from this deck
    public LinkedList<Card> getCards(int count) {
        LinkedList<Card> result = new LinkedList<>();

        //Some logic if we need to take more cards from deck
        //than deck size
        int cardsWasTaken = 0;
        if (count > deck.size()) {
            int size = deck.size();
            for (int i = 0; i < size; i++) {
                result.add(deck.getLast());
                deck.removeLast();
                cardsWasTaken += 1;
            }
            //now deck must be empty
            if (!deck.isEmpty()){
                //LOG ERROR
                System.out.println("Something go wrong. Main deck had to be empty");
            }
            deck = playedDeck.getAllCardsWithShuffle();
        }
        count -= cardsWasTaken;

        //Deck is big enough, so we take cards
        for (int i = 0; i < count; i++) {
            result.add(deck.getLast());
            deck.removeLast();
        }
        return result;
    }

    @Override
    public String toString() {
        return "MainDeck{" +
                "deck=" + deck +
                ", playedDeck=" + playedDeck.getDeck() +
                '}';
    }
}
