package com.itiswho.chuanjian.game.chuanjian.rules;

import com.itiswho.chuanjian.game.chuanjian.Card;

import java.util.ArrayList;

public class One implements Rule {
    public One(ArrayList<Card> cardArrayList) {

    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public int getVal() {
        return 0;
    }

    @Override
    public int getType() {
        return 0;
    }
}
