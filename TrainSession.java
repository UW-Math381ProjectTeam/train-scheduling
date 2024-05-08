import java.util.*;

public class TrainSession{
    private int seeesionDate;
    private List<Movement> movements;

    public TrainSession (int d, List<Movement> m) {
        this.seeesionDate = d;
        this.movements = new ArrayList<Movement>(m);
    }

    public int checkNextValid () {
        int violations = 0;

        for (int i = 0; i < this.movements.size(); i = i + 1) {
            ArrayList<Movement> currNext = this.movements.subList(i, movements.size());
        }
        /*if (this.nextMovements.size() == 0) {
            return true;
        } else if (this.nextMovements.size() != futureMovements.size()) {
            return false;
        } else {
            int sz = this.nextMovements.size();

            for (int i = 0; i < sz; i = i + 1) {
                if (!this.nextMovements.get(i).getName().equals(futureMovements.get(i).getName())) {
                    return false;
                }
            }

            return true;
        }*/
        return violations;
    }
}