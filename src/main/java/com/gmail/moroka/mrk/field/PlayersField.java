package com.gmail.moroka.mrk.field;

import com.gmail.moroka.mrk.entity.card.Card;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.LinkedList;

@Data
public class PlayersField {

    private LinkedList<LinkedList<Card>> slots;

    //TODO countOfSlots is hardcoded
    private final int countOfSlots = 4;

    public PlayersField() {
        this.slots = getEmptySlots();
    }

    //Used only once in constructor
    //Generates and returns empty slots list
    private LinkedList<LinkedList<Card>> getEmptySlots() {
        LinkedList<LinkedList<Card>> result = new LinkedList<>();
        for (int i = 0; i < countOfSlots; i++) {
            result.add(new LinkedList<Card>());
        }
        return result;
    }

    //Returns card form defined slot without deleting it
    @Nullable
    public Card getCard(int slotIndex){
        if (slots.get(slotIndex).isEmpty()){
            return null;
        }
        return slots.get(slotIndex).getLast();
    }

    //Takes (returns) card from defined slot (deletes it from list)
    @Nullable
    public Card takeCard(int slotIndex){
        if (slots.get(slotIndex).isEmpty()){
            return null;
        }
        Card result = slots.get(slotIndex).getLast();
        slots.get(slotIndex).removeLast();
        return result;
    }

    public void putCard(Card card, int slotIndex){
        slots.get(slotIndex).add(card);
    }

}
