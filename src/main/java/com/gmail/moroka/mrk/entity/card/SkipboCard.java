package com.gmail.moroka.mrk.entity.card;

import lombok.Data;

@Data
public class SkipboCard implements Card{

    private final int value;

    public SkipboCard() {
        this.value = 0;
    }

    @Override
    public int getValue() {
        return value;
    }
}
