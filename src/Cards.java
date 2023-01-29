import java.util.ArrayList;

public class Cards extends ArrayList<Integer> { //牌堆用此维护

    public Cards() {
        fillMain();
    }

    public void fillMain() {
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                this.add(i);
            }
        }
    }

    public int onePicked() {
        if (this.size() == 0) {
            fillMain();
        }
        int random = (int) (Math.random() * this.size());
        Integer result = this.get(random);
        this.remove(result);
        return result;
    }

    public ArrayList<Integer> fivePicked() { //only beginning
        ArrayList<Integer> five = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            five.add(onePicked());
        }
        return five;
    }

}
