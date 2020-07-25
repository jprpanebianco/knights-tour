package knightstour;
import java.util.Random;

public class KnightsTour {
    
    //Returns array of edge list for any board by checking legal moves of the knight
    //Used for entry into adjacency list when initializing graph////////////
    public static String[] printEdges(int row, int column){
        int sum = 0;
        int arraySize = 8*(row - 2)*(column - 1);
        String[] array = new String[arraySize];
        for(int y = 0; y< row; y++){
            for (int x = 0; x < column; x++){
                for(int y1 = -2; y1 < 3; y1++){
                    for (int x1 = -2; x1 < 3; x1++){
                        if (Math.abs(y1)!=Math.abs(x1)){
                            if((Math.abs(y1) == 2 && Math.abs(x1) == 1) || (Math.abs(y1) == 1 && Math.abs(x1) == 2)){
                                if (y + y1 >= 0 && y +y1 <= (row - 1)){
                                    if(x +x1 >= 0 && x +x1 <= (column - 1)){
                                        array[sum] = (x + (y * row)) + "," + (x + x1 + ((y + y1) * row));
                                        sum++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return array;
    }
    
    //Shuffles edge lists, Warnsdorff is deterministic to some degree, so edge lists must be shuffled to 
    //create new tour from a given vertex (not the most efficient of empirical way to do this)////////////////
    public static void shuffleArray(String[] arr){
        
        int n = arr.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            String temp = arr[i];
            arr[i] = arr[change];
            arr[change] = temp;
        }
    }
    
    public static void main(String[] args) {
        
        for (int i = 5; i <= 60; i ++){
            System.out.println("---------------------------------------------------------");
            //System.out.println("ADJACENCY LIST FOR "  + i + "-x-" + i + " BOARD:");
            System.out.println("KNIGHT TOUR FOR " + i + "-x-" + i + " BOARD STARTING FROM SQUARE 0:");
            System.out.println("---------------------------------------------------------");
            int row = i;
            int column = i;
            String[] edges = printEdges(row, column);
            Graph g1 = new Graph(row*column, edges);
            //g1.print();
            g1.DFS(0);
        }
    }
}
