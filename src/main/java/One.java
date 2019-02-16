import java.util.ArrayList;

public class One implements Rule {
    One(ArrayList<Card> cardArrayList) {

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
