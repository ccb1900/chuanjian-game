/**
 * @author ccb
 */
public class Card implements Comparable<Card> {
    int val;
    int color;
    static final int black = 1;
    static final int red = 2;
    static final int flower = 3;
    static final int slice = 4;

    Card(int val, int color){
        this.val = val;
        this.color = color;
    }

    int getVal() {
        return val;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.val, o.val);
    }

    @Override
    public String toString() {
        return  val+"*"+color;
    }

    boolean equals(Card obj) {
        return Boolean.getBoolean(Integer.toString(Integer.compare(this.val,obj.val)));
    }
}
