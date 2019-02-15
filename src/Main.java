import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        start();
    }
    private static void start() {
        Room room = new Room();

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


        room.getUser(current).removeCard(1,1);
    }

    /**
     * 小炮
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean small(Card a,Card b,Card c) {
        return a.equals(b) && b.equals(c) && a.getVal()==6;
    }

    /**
     * 穿剑
     * @param a
     * @param b
     * @param c
     * @return
     */
    static boolean chuanjian(Card a,Card b,Card c) {
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

