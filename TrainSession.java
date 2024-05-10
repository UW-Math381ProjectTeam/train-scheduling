import java.util.*;

public class TrainSession {
    private final int THRESHOLD = 3;
    private int numMovements;
    private int dayNum;
    private List<Movement> movements;
    private Map<String, Integer> countParts;

    /**
     * Constructor of TrainSession. 
     * @param numMovements: The number of movements in one training session
     * @param dayNum: the index of the day of the training session
     */
    public TrainSession(int numMovements, int dayNum) {
        this.numMovements = numMovements;
        this.dayNum = dayNum;
        List<Movement> movements = new ArrayList<>();
        this.countParts = new HashMap<>();
        this.movements = movements;
    }

    /**
     * Add a movement to the session if movement is valid.  
     * @param movement: the movement to be added
     * @return boolean: whether the add is successful
     */
    public boolean addMovement(Movement movement) {
        if (this.movements.contains(movement)) {
            return false;
        }

        for (String element : movement.getType()) {
            if (this.countParts.get(element) != null && this.countParts.get(element) >= this.THRESHOLD) {
                return false;
            } 
        }

        if (this.movements.size() >= this.numMovements) {
            return false;
        }

        this.movements.add(movement);

        for (String element : movement.getType()) {
            if (this.countParts.containsKey(element)) {
                countParts.put(element, countParts.get(element) + 1);
            } else {
                countParts.put(element, 1);
            }
        }
        
        return true;
    }

    /**
     * Add a movement to the session if movement is valid.  
     * @param movement: the movement to be added
     * @return boolean: whether the add is successful
     */
    public boolean removeMovement(Movement movement) {
        if (!this.movements.contains(movement)) {
            return false;
        }

        for (String element : movement.getType()) {
            countParts.put(element, countParts.get(element) - 1);
        }

        this.movements.remove(movement);
        return true;
    }

    /**
     * Returns the current size of the movements list
     * @return int: the current size of the movements list
     */
    public int getCurrSize() {
        return this.movements.size();
    }

    /**
     * check if the session is filled or not
     * @return int: if the session is filled, return true, otherwise return false
     */
    public boolean isFilled() {
        return getCurrSize() == this.numMovements; 
    }

    /**
     * Returns a String representation of the TrainSession object. 
     * @return String: the String representation of the TrainSession object
     */
    public String toString() {
        String list = "";
        for (int i = 0; i < movements.size() - 1; i++) {
            list = list + movements.get(i) + ", ";
        }
        list = list + movements.get(movements.size() - 1);
        return "Day " + this.dayNum + ": " + list;
    }

    public boolean equals(TrainSession other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TrainSession)) {
            return false;
        }

        return this.numMovements == other.numMovements 
                  && this.dayNum == other.dayNum 
                  && this.movements.equals(other.movements);

    }


    public List<Movement> getMovements() {
        return this.movements;
    }

    public String printAllMovements() {
        String result = "";
        List<Movement> convertmovement = (List<Movement>) movements;
        for (Movement move : convertmovement) {
            result = result + move.toString();
            result = result + ",";
        }
        return result;
    }
    

    public int getNumMovements() {
        return this.numMovements;
    }
}