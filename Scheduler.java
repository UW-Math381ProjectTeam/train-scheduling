import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.io.FileReader;  
import java.io.IOException;  

public class Scheduler{
    public static final boolean DEBUG = false;
    public static List<Movement> movements;
    public static int numMovementEveryday;
    public static int numTD;
    public final static int PUNISHMENT_VAL = 5;
    private static TrainSession[] schedule;

    public static Graph<TrainSession> buildGraph(List<TrainSession> vertices) {
        Graph<TrainSession> ans = new Graph<>();

        for (TrainSession t : vertices) {
            ans.addVertex(t);
        }

        for (int i = 0; i < vertices.size() - 1; i++) {
            ans.addEdge(vertices.get(i), vertices.get(i + 1));
        }
        
        return ans;
    }

    public static int calcPunishment(Graph<TrainSession> g) {
        Set<TrainSession> vertices = g.getVertices();
        int punishment = 0;

        for (TrainSession t1 : vertices) {
            for (TrainSession t2 : vertices) {
                if (g.isNeighbor(t1, t2) && t1.getDayNum() < t2.getDayNum()) {
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
            numMovementEveryday = inputScanner.nextInt();

            // generate schedule - recursive generation + compute chengfashuzhi
            numTD = 0;
            for (int i = 0; i < trainDays.length; i++) {
                if (trainDays[i]) {
                    numTD++;
                }
            }

            if (DEBUG) {
                System.out.println("Total Train Days: " + numTD + " , every train day, do " + numMovementEveryday + " movements");
            }
            
            
            schedule = new TrainSession[numTD];
            // TrainSession testTrainningSession = new TrainSession(numMovementEveryday, numTD);
            buildSession();
        }
    }

    private static void buildSession() {
        int ans = Scheduler.CombinationIteratorTask.allCombos();
        System.out.println(ans);
    }

    private static int buildSessionHelper(TrainSession session, Graph<TrainSession> g, int min) {
        if (session.isFilled() && g.getVertexCount() == numTD) {
            // if (sessions.get(0).getNumMovements() != sessions.get(1).getNumMovements()) {
            //     System.out.println("WARNING");
            // }
            // Graph<TrainSession> g = buildGraph(sessions);
            int ans = calcPunishment(g);
            
            if (ans < min) {
                return ans;
            }

            return min;
            // if (ans != 0) {
            //     System.out.println(ans);
            // }
            // System.out.println(calcPunishment(g));
            
        }

        if (session.isFilled()) {
            TrainSession t = new TrainSession(numMovementEveryday, g.getVertexCount() + 1);
            g.addVertex(t);
            g.addEdge(session, t);
            // System.out.println("Session:" + session.printAllMovements());
            int ans = buildSessionHelper(t, g, min);
            g.removeVertex(t);
            if (ans < min) {
                // System.out.println("OK");
                return ans;
            }
            return min;
        }
        
        for (int i = 0; i < movements.size(); i++) {
            Movement movement = movements.get(i);
            if (session.addMovement(movement)) {
                int ans = buildSessionHelper(session, g, min);
                // System.out.println(ans);
                if (ans < min) {
                    min = ans;  
                }
                session.removeMovement(movement);
            }
        }
        return min;
    }


    public static class CombinationIteratorTask extends RecursiveTask<Integer> {

        public static final ForkJoinPool pool = new ForkJoinPool();
        public static final int CUTOFF = 5;
      
      
        public static int allCombos() {
            int ans = pool.invoke(new CombinationIteratorTask(0, movements.size()));
            return ans;
        }
      
        private final int lo, hi;
      
        public int sequential(int lo, int hi) {
            TrainSession t = new TrainSession(numMovementEveryday, 1);
            
            List<TrainSession> sessions = new ArrayList<>();
            Graph<TrainSession> g = buildGraph(sessions);
            int min = 2147483647;
            g.addVertex(t);
            for (int i = lo; i < hi; i++) {
                t.addMovement(movements.get(i));
                int ans = buildSessionHelper(t, g, 2147483647);
                if (ans < min) {
                    min = ans;
                }
                t.removeMovement(movements.get(i));
            }

            return min;
        }
      
        public CombinationIteratorTask(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
      
        protected Integer compute() {
          if (this.hi - this.lo <= CUTOFF) {
            return sequential(lo, hi);
          } else {
            int mid = lo + (hi - lo) / 2;
            CombinationIteratorTask left = new CombinationIteratorTask(lo, mid);
            CombinationIteratorTask right = new CombinationIteratorTask(mid, hi);
            left.fork();
            int rightAns = right.compute();
            int leftAns = left.join();
            return Math.min(leftAns, rightAns);
          }
        }
      }
      
}