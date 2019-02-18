package com.itiswho.chuanjian.entity;

import com.itiswho.chuanjian.game.chuanjian.Card;
import com.itiswho.chuanjian.game.chuanjian.rules.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author ccb
 */
public class Room {
    private String no;
    private String id;
    private HashMap<Integer,User> userMap = new HashMap<>();

    private int start;
    private int turns;
    private Rule current;

    public Room(String no, String id) {
        this.no = no;
        this.id = id;
    }

    public void add(int pos, User user) {
        user.setRoom(this);
        userMap.put(pos,user);
    }

    public ArrayList<Card> shuffle() {
        ArrayList<Card> cardList = new ArrayList<>(52);
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 14; j++) {
                Card card = new Card(j,i);
                if (j == 1 || j == 2 || 3 == j) {
                    card.setWeight(13 + j);
                } else {
                    card.setWeight(j);
                }
                cardList.add(card);
            }
        }
        Collections.shuffle(cardList);
        return cardList;
    }

    public void dispatch(ArrayList<Card> cardArrayList) {
        for (int i = 0; i < 13; i++) {
            dispatchCard(cardArrayList.get(4 * i), 1);
            dispatchCard(cardArrayList.get(4 * i + 1), 2);
            dispatchCard(cardArrayList.get(4 * i + 2), 3);
            dispatchCard(cardArrayList.get(4 * i + 3), 4);
        }

        Collections.sort(userMap.get(1).getCardList());
        Collections.sort(userMap.get(2).getCardList());
        Collections.sort(userMap.get(3).getCardList());
        Collections.sort(userMap.get(4).getCardList());
    }

    public int getStart() {
        return start;
    }


    public User getUser(int index) {
        return userMap.get(index);
    }

    public Rule getCurrent() {
        return current;
    }

    public void setCurrent(Rule current) {
        this.current = current;
    }

    private void dispatchCard(Card card, int userIndex) {
        if (card.getVal() == 4 && card.getColor() == 2) {
            start = userIndex;
            turns = userIndex;
        }

        userMap.get(userIndex).scratch(card);
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    void start() {

    }

    void end() {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
