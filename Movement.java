import java.util.*;

public class Movement {

    // data
    private String name;
    private int movementType;
    private int duration;

    // pointer
    private List<Movement> nextMovements;
    private List<Movement> nextProhibitedMovements;

    /*public Movement (String n, int t, int d) {
        this.name = n;
        this.movementType = t;
        this.duration = d;
        this.nextMovements = new ArrayList<Movement>();
        this.nextProhibitedMovements = new ArrayList<Movement>();
    }*/

    public Movement (String n, int t, int d, List<Movement> nexts, List<Movement> prohibited) {
        this.name = n;
        this.movementType = t;
        this.duration = d;

        this.nextMovements = new ArrayList<Movement>(nexts);
        this.nextProhibitedMovements = new ArrayList<Movement>(prohibited);
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.movementType;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean checkNextValid (List<Movement> futureMovements) {
        if (this.nextMovements.size() == 0) {
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
        }
    }

    public boolean checkProhibitedValid (List<Movement> futureMovements) {
        for (int i = 0; i < futureMovements.size(); i = i + 1) {
            for (int j = 0; j < this.nextProhibitedMovements.size(); i = i + 1) {
                if (futureMovements.get(i).getName().equals(this.nextProhibitedMovements.get(j).getName())) {
                    return false;
                }
            }
        }

        return true;
    }
}