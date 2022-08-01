package com.gmail.moroka.mrk.entity.player;

import com.gmail.moroka.mrk.entity.card.Card;
import com.gmail.moroka.mrk.entity.deck.PlayersDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersHand;
import com.gmail.moroka.mrk.field.PlayField;
import com.gmail.moroka.mrk.field.PlayersField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Player {

    private String name;

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

    private boolean tryToPlayCardFromPlayersDeck() {
        boolean isPut = false;
        Card card = playersDeck.getDeck().getLast();
        isPut = playField.tryToPutCard(card);

        if (isPut) {
            //card was succesfuly put to the play field, so we remove it
            //from player's deck
            playersDeck.getDeck().removeLast();
        }
        return isPut;
    }

    private boolean tryToPutCardFromHand() {
        boolean isPut = false;
        for (int i = 0; i < playersHand.getHand().size(); i++) {
            if (playField.tryToPutCard(playersHand.getHand().get(i))){
                playersHand.getHand().remove(i);
                return true;
            }
        }
        return false;
    }

}
