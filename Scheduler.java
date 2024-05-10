import java.util.*;
import java.io.FileReader;  
import java.io.IOException;  

public class Scheduler{
    public static final boolean DEBUG = false;
    public static List<Movement> movements;
    public final int PUNISHMENT_VAL = 5;
    private static TrainSession<Movement>[] schedule;

    public Graph<TrainSession<Movement>> buildGraph(List<TrainSession<Movement>> vertices) {
        Graph<TrainSession<Movement>> ans = new Graph<>();

        for (TrainSession<Movement> t : vertices) {
            ans.addVertex(t);
        }

        for (int i = 0; i < vertices.size() - 1; i++) {
            ans.addEdge(vertices.get(i), vertices.get(i + 1));
        }
        
        return ans;
    }

    public int calcPunishment(Graph<TrainSession<Movement>> g) {
        Set<TrainSession<Movement>> vertices = g.getVertices();
        int punishment = 0;

        for (TrainSession<Movement> t1 : vertices) {
            for (TrainSession<Movement> t2 : vertices) {
                if (!t1.equals(t2) && g.isNeighbor(t1, t2)) {
                    List<Movement> list1 = t1.getMovements();
                    List<Movement> list2 = t2.getMovements();

                    if (list1.size() != list2.size()) {
                        throw new IllegalStateException
                            ("WRONG: Two Sessions have different numMovements");
                    }

                    // Assuming t1 and t2 have same movement size
                    for (int i = 0; i < t1.getNumMovements(); i++) {
                        if (list1.get(i).equals(list2.get(i))) {
                            punishment += PUNISHMENT_VAL;
                        }
                    }
                }
            }
        }
        return punishment;
    }


    public static void main(String[] args) {
        // read data
        List<String[]> data = new ArrayList<>();
        try {
            Scanner file = new Scanner(new FileReader("Test.csv"));  
            String line = "";  
            String splitBy = ",";  
            line = file.nextLine();
            while (file.hasNextLine())  {  
                line = file.nextLine();
                data.add(line.split(splitBy));
            }  
    
            file.close();
        } catch (IOException e) {
            e.printStackTrace();  
        } 
        if (DEBUG) {
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).length; j++) {
                    System.out.print(data.get(i)[j] + " ");
                }
                System.out.println("");
            }
        }

        movements = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String[] movList = data.get(i);
            if (movList.length != 3) {
                // System.out.println(Arrays.toString(movList));
                throw new IllegalArgumentException("CSV file has wrong format at line:" + i);
            }
            Movement mov = new Movement(movList[0], movList[1].split(" "), Integer.parseInt(movList[2]));
            movements.add(mov);
        }


        // generate schedule - add train days
        boolean[] trainDays = new boolean[14];
        try (Scanner inputScanner = new Scanner(System.in)) {
            System.out.println("Welcome to train session scheduling system,");
            System.out.println("enter train days from a 14 day chunk (1 - 14)");
            System.out.println("enter integers, seperate by spaces");

            String newTrainDays = inputScanner.nextLine();
            System.out.println("Train days are: " + newTrainDays);

            try (Scanner trainDayScanner = new Scanner(newTrainDays)) {
                while(trainDayScanner.hasNextInt()) {
                    int newTrainDay = trainDayScanner.nextInt();

                    if (newTrainDay >= 15 || newTrainDay < 1) {
                        throw new IllegalArgumentException("Wrong train day: " + newTrainDay);
                    }

                    if (DEBUG) {
                        System.out.println("Add new train day: " + newTrainDay);
                    }

                    trainDays[newTrainDay - 1] = true;
                }
            }

            // generate schedule - get information
            System.out.println("How many movements everyday?");
            int numMovementEveryday = inputScanner.nextInt();

            // generate schedule - recursive generation + compute chengfashuzhi
            int numTD = 0;
            for (int i = 0; i < trainDays.length; i++) {
                if (trainDays[i]) {
                    numTD++;
                }
            }

            if (DEBUG) {
                System.out.println("Total Train Days: " + numTD + " , every train day, do " + numMovementEveryday + " movements");
            }
            
            
            schedule = new TrainSession[numTD];
            TrainSession<Movement> testTrainningSession = new TrainSession<>(numMovementEveryday, numTD);
            buildSessionHelper(testTrainningSession);
        }
    }

    private static void buildSessionHelper(TrainSession<Movement> session) {
        if (session.isFilled()) {
            System.out.println("Session:" + session.printAllMovements());
            return;
        }
        for (int i = 0; i < movements.size(); i++) {
            Movement movement = movements.get(i);
            if (session.addMovement(movement)) {
                buildSessionHelper(session);
                session.removeMovement(movement);
            }
        }
        return;
    }
}