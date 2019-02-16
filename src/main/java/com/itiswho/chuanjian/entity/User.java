package com.itiswho.chuanjian.entity;

import com.itiswho.chuanjian.game.chuanjian.Card;

import java.util.ArrayList;

/**
 * @author ccb
 */
public class User {
    int id;
    String name;
    String avatar;
    Room room;
    private ArrayList<Card> cardList = new ArrayList<>();

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    void send() {
        // 判断是否合法
    }

    void scratch(Card card) {
        this.cardList.add(card);
    }

    void removeCard(Card card) {
        this.cardList.remove(card);
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    boolean victory() {
        return this.cardList.isEmpty();
    }
}
