package com.itiswho.chuanjian.game.chuanjian;

import com.itiswho.chuanjian.entity.Room;
import com.itiswho.chuanjian.entity.User;
import com.itiswho.chuanjian.game.chuanjian.rules.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author ccb
 */
public class Main {
    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws Exception {
        Room room = new Room("1", "1");

        User c1 = new User("ccb1",1);
        User c2 = new User("ccb2",2);
        User c3 = new User("ccb3",3);
        User c4 = new User("ccb4",4);
        room.add(c1.getId(),c1);
        room.add(c2.getId(),c2);
        room.add(c3.getId(),c3);
        room.add(c4.getId(),c4);

        ArrayList<Card> cardArrayList = room.shuffle();

        room.dispatch(cardArrayList);

        for (int i = 1; i <5 ; i++) {
            System.out.println(i+"号的牌："+room.getUser(i).getCardList());
        }
        System.out.println("请"+room.getStart()+"号出牌");

        int current = room.getStart();

        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("please input number: ");
            int number = Integer.parseInt(bufferedReader.readLine());

            if (number != current) {
                continue;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("please input card list: (use space to seq)");

            String cardString = bufferedReader.readLine();

            if (cardString.equals("s")) {
                System.out.println(number + "没有出牌，跳过");
                continue;
            }
            String[] cardSplit = cardString.split(" ");

            ArrayList<Card> cardArrayList1 = new ArrayList<>(cardSplit.length);
            for (int i = 0; i < cardSplit.length; i++) {
                String[] card = cardSplit[i].split("\\*");
                cardArrayList1.add(new Card(Integer.parseInt(card[0]), Integer.parseInt(card[1])));
            }
            Rule userRule;
            switch (cardArrayList1.size()) {
                case 1:
                    userRule = new One(cardArrayList1);
                    break;
                case 2:
                    userRule = new Couples();
                    break;
                case 3:
                    userRule = new Three();
                    userRule = new Small();
                    userRule = new Big();
                    userRule = new King();
                    break;
                case 4:
                    userRule = new Shun();
                    userRule = new Bomb(cardArrayList1);
                    break;
                default:
                    throw new Exception("");

            }
            if (room.getCurrent() == null) {
                room.setCurrent(userRule);
            } else {
                Rule currentRule = room.getCurrent();

                if (currentRule.getType() == 1) {
//                    if (currentRule.compare(userRule)) {
//                        for (com.itiswho.chuanjian.game.chuanjian.Card card : cardArrayList1) {
//                            room.getUser(current).removeCard(card);
//                        }
//                    }
                }

                room.setCurrent(userRule);
            }
        }
    }

    /**
     * 小炮
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean small(Card a, Card b, Card c) {
        return a.equals(b) && b.equals(c) && a.getVal()==6;
    }

    /**
     * 穿剑
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean chuanjian(Card a, Card b, Card c) {
        return a.getVal()==1 && b.equals(c) && b.getVal()==4;
    }

    /**
     * 大炮
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean big(Card a,Card b,Card c) {
        return a.equals(b) && b.equals(c) && a.getVal()==12;
    }

    /**
     * 对子
     * @param a
     * @param b
     * @return
     */
    static boolean cuples(Card a,Card b) {
        return a.equals(b);
    }

    /**
     * 三个
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean three(Card a,Card b,Card c) {
        return a.equals(b) && b.equals(c);
    }

    /**
     * 顺子
     * @param cardArrayList
     * @return
     */
    static boolean shunzi(ArrayList<Card> cardArrayList) {
        for (int i = 1; i < cardArrayList.size(); i++) {
            if (cardArrayList.get(i).getVal()-cardArrayList.get(i-1).getVal() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 炸弹
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    static boolean bomb(Card a,Card b,Card c,Card d) {
        return  a.equals(b) && b.equals(c) && c.equals(d);
    }
}

