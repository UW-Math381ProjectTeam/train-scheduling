public class Movement {

    // data
    private String name;
    private String[] movementTypes;
    private int difficulty;

    public Movement (String n, String[] t, int d) {
        this.name = n;
        this.movementTypes = t;
        this.difficulty = d;
    }

    public String getName() {
        return this.name;
    }

    public String[] getType() {
        return this.movementTypes;
    }

    public String toString() {
        return this.name;
    }

    public int getDifficulty() {
      return difficulty;
    }
}
