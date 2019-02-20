package com.itiswho.ccb.rules;

import java.util.ArrayList;

public class Bomb implements Rule {
    ArrayList<Card> cards;

    public Bomb(ArrayList<Card> cardArrayList) {
        this.cards = cardArrayList;
    }

    @Override
    public boolean check() {
        return this.cards.size() == 4
                && this.cards.get(0).equals(this.cards.get(1))
                && this.cards.get(1).equals(this.cards.get(2))
                && this.cards.get(2).equals(this.cards.get(3));
    }

    @Override
    public int getVal() {
        return this.cards.get(0).getVal();
    }

    @Override
    public int getType() {
        return 1;
    }
}
