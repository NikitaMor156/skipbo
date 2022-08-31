package com.gmail.moroka.mrk.entity.player;

import com.gmail.moroka.mrk.entity.card.Card;
import com.gmail.moroka.mrk.entity.deck.PlayersDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersHand;
import com.gmail.moroka.mrk.field.PlayField;
import com.gmail.moroka.mrk.field.PlayersField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;

public class Player {

    private String name;
    boolean isWinner = false;

    @Autowired
    @Qualifier("getPlayersDeckBean")
    private PlayersDeck playersDeck;

    @Autowired
    @Qualifier("getPlayersHandBean")
    private PlayersHand playersHand;

    @Autowired
    @Qualifier("getPlayersFieldBean")
    private PlayersField playersField;

    @Autowired
    @Qualifier("getPlayFieldBean")
    private PlayField playField;


    public Player(String name) {
        this.name = name;
    }

    public void playRound() {

    }

    //Tries to put card from player's deck to play field.
    //Returns true if action was successful, false if wasn't
    private boolean tryToPlayCardFromPlayersDeck() {
        boolean isPut = false;
        Card card = playersDeck.getDeck().getLast();
        isPut = playField.tryToPutCard(card);

        if (isPut) {
            //card was successfully put to the play field, so we remove it
            //from player's deck.
            playersDeck.getDeck().removeLast();
        }
        return isPut;
    }

    //Tries to put any (only one) card from hand to playing field.
    //Returns true if action was successful, false if wasn't
    private boolean tryToPutAnyCardFromHand() {
        boolean isPut = false;
        for (int i = 0; i < playersHand.getHand().size(); i++) {
            if (playField.tryToPutCard(playersHand.getHand().get(i))) {
                playersHand.getHand().remove(i);
                return true;
            }
        }
        return false;
    }

    //Try to put upper card from players deck to defined slot on playing field
    //Returns true if action was successful, false if wasn't
    //Removes card from player's deck if card was put (when result = true)
    private boolean tryToPutCardFromPlayersDeck() {
        if (playersDeck.getDeck().isEmpty()) {
            isWinner = true;
            return false;
        }
        for (int i = 0; i < playField.getCountOfSlots(); i++) {
            boolean result = playField.tryToPutCard(playersDeck.getDeck().getLast(), i);
            if (result) {
                playersDeck.getDeck().removeLast();
                return true;
            }
        }
        return false;
    }

    //Tries to put one or more cards from hand to playing deck
    //for player's deck card to be abe to be put to the playing field.
    //If it is not possible for method result to be finished with player's deck card
    //to be put to the any slot of playing field, returns false and does nothing.
    //If action was successful, executes th operation and returns true.
    public boolean tryToPutStackBeforeCardFromPlayersDeck(int fieldDeckIndex) {
        Card fieldCard = playField.getSlots().get(fieldDeckIndex).getLast();
        Card deckCard = playersDeck.getDeck().getLast();
        //Return false if player's deck card > field card
        if (deckCard.getValue() <= fieldCard.getValue()) {
            return false;
        }
        //TODO проверить, можно ли положить карты из руки так, чтобы стопка удовлетворяла требованиям PlayersDeck card


        return false;// DELETE
    }

    //Returns the copy of player's hand
    private LinkedList<Card> getCopyOfPlayersHand() {
        LinkedList<Card> result = new LinkedList<>();
        for (Card card : playersHand.getHand()) {
            result.add(card);
        }
        return result;
    }

    ////Returns the copy of play field
    private LinkedList<Card> getCopyOfPlayFieldSlot(int slotNumber) {
        LinkedList<Card> result = new LinkedList<>();
        for (Card card : playField.getSlots().get(slotNumber)) {
            result.add(card);
        }

        return result;
    }

    //Used in tryToPutStackBeforeCardFromPlayersDeck method to
    //understand if operation is possible (work with copy of objects) and
    //if it is, execute it with real pl. hand and pl. field
    private boolean process(LinkedList<Card> playersHand, LinkedList<Card> playField, int slotNumber) {
        //Put all skipbo cards to another LinkedList
        LinkedList<Card> skipbos = new LinkedList<>();
        for (int i = 0; i < playersHand.size(); i++) {
            if (playersHand.get(i).getValue() == 0){
                skipbos.add(playersHand.get(i));
                playersHand.remove(i);
            }
        }

        for (int i=0;i<playersHand.size();i++){

        }

    return false;
    }

    //Player does a turn (all logic is here)
    public void doTurn() {

        boolean wasCardPut = tryToPutCardFromPlayersDeck();
        while (wasCardPut = true) {
            wasCardPut = tryToPutCardFromPlayersDeck();
        }
        //TODO

    }


}
