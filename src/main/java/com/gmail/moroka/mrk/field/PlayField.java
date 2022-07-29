package com.gmail.moroka.mrk.field;

import com.gmail.moroka.mrk.entity.card.Card;
import com.gmail.moroka.mrk.entity.deck.PlayedDeck;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;

@Data
public class PlayField {
    private LinkedList<LinkedList<Card>> slots;

    private final int countOfSlots;

    private final int slotSize;

    @Autowired
    @Qualifier("getPlayedDeckBean")
    private PlayedDeck playedDeck;

    //Constructor
    //Size is 4 by default rules
    public PlayField(int countOfSlots) {
        this.countOfSlots = countOfSlots;
        this.slotSize = 12;
        this.slots = getPlayingFieldList(this.countOfSlots);
    }

    //Used only once in constructor
    //Generates and returns empty "slot" list
    private LinkedList<LinkedList<Card>> getPlayingFieldList(int size) {
        LinkedList<LinkedList<Card>> result = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            result.add(new LinkedList<Card>());
        }
        return result;
    }

    //Puts card on any free slot in playing field
    //Returns true if operation was successful, false if not
    public boolean tryToPutCard(Card card) {
        //(На всякий случай)
        handleFullStack();

        boolean result = false;
        for (int i = 0; i < countOfSlots; i++) {
            int upperCardValue = slots.get(i).size();
            if (card.getValue() == 0) {
                result = true;
            } else if (slots.get(i).isEmpty() && card.getValue() == 1) {
                result = true;
            } else if (slots.get(i).isEmpty() && card.getValue() != 1) {
                result = false;
            } else if (upperCardValue == card.getValue() - 1) {
                result = true;
            }
            if (result) {
                slots.get(i).add(card);
                handleFullStack();
                return true;
            }
        }
        return false;
    }

    //Put all cards from slot to played deck if slot is full
    public void handleFullStack() {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).size() >= slotSize){
                playedDeck.add(slots.get(i));
                slots.get(i).clear();
            }
        }
    }


}
