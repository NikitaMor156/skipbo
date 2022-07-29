package com.gmail.moroka.mrk.entity.card;

import lombok.Data;

@Data
public class NumberCard implements Card{

    private final int value;

    public NumberCard(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
