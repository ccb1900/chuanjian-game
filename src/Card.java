public class Card implements Comparable<Card> {
    int val;
    int color;
    Card(int val,int color){
        this.val = val;
        this.color = color;
    }
    public int getVal() {
        return val;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.val, o.val);
    }

    public String toString() {
        return  val+"*"+color;
    }

    public boolean equals(Card obj) {
        return Boolean.getBoolean(Integer.toString(Integer.compare(this.val,obj.val)));
    }
}
