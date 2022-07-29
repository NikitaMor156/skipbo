package com.gmail.moroka.mrk.config;

import com.gmail.moroka.mrk.entity.deck.MainDeck;
import com.gmail.moroka.mrk.entity.deck.PlayedDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersDeck;
import com.gmail.moroka.mrk.entity.deck.PlayersHand;
import com.gmail.moroka.mrk.field.PlayField;
import com.gmail.moroka.mrk.field.PlayersField;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"com.gmail.moroka.mrk", "com.gmail.moroka.mrk.entity"})
public class Config {

    @Bean
    @Scope("singleton")
    public MainDeck getMainDeckBean(){
        return new MainDeck(12,16);
    }

    @Bean
    @Scope("singleton")
    public PlayedDeck getPlayedDeckBean(){
        return new PlayedDeck();
    }

    @Bean
    @Scope("singleton")
    public PlayersDeck getPlayersDeckBean(){
        return new PlayersDeck();
    }

    @Bean
    @Scope("singleton")
    public PlayersHand getPlayersHandBean(){
        return new PlayersHand();
    }

    @Bean
    @Scope("singleton")
    public PlayersField getPlayersFieldBean(){
        return new PlayersField();
    }

    @Bean
    @Scope("singleton")
    public PlayField getPlayFieldBean(){
        return new PlayField(4);
    }

}
