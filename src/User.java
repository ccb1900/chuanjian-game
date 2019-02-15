import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String name;
    String avatar;
    Room room;
    private ArrayList<Card> cardList = new ArrayList<>();
    User(String name,int id) {
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

    void removeCard(int val,int color) {
        this.cardList.remove(new Card(val, color));
    }
    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    boolean victory() {
        return this.cardList.isEmpty();
    }
}
