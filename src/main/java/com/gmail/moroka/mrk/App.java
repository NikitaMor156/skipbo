package com.gmail.moroka.mrk;

import com.gmail.moroka.mrk.config.Config;
import com.gmail.moroka.mrk.entity.deck.MainDeck;
import com.gmail.moroka.mrk.entity.deck.PlayedDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersHand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@ComponentScan(basePackages = {"com.gmail.moroka.mrk", "com.gmail.moroka.mrk.entity"})
public class App {
    public static void main(String[] args) {



        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("MainDeck bean -> " + context.getBean("getMainDeckBean", MainDeck.class));
        System.out.println("PlayedDeck bean -> " + context.getBean("getPlayedDeckBean", PlayedDeck.class));
        System.out.println("Players Hand -> " + context.getBean("getPlayersHandBean", PlayersHand.class));
        System.out.println("PlayersDeck -> " + context.getBean("getPlayersDeckBean", PlayersDeck.class));
        System.out.println("PlayersField -> " + context.getBean("getPlayFieldBean"));

        context.close();
    }
}
