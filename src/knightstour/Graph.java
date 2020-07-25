package knightstour;
import java.util.LinkedList;

public class Graph{
    static int sum = 0;
    static int nodes = 0;
    int vertices;
    int edges;
    LinkedList<Integer> adjListArray[]; 
    LinkedList<Integer> path;
    boolean[] visited;

    Graph(int vertices, String[] edges) 
    { 
        this.vertices = vertices;
        this.edges = edges.length;
        adjListArray = new LinkedList[vertices];
        for(int i = 0; i < vertices; i++){
            adjListArray[i] = new LinkedList();
        }
        for(String s : edges){
            String[] vert = s.split(",");
            addEdge(Integer.parseInt(vert[0]), Integer.parseInt(vert[1]));
        }
    } 

    public void addEdge( int v1, int v2){
        adjListArray[v1].add(v2);
    }

    //Prints the adjacency list of the graph/////////////
    public void print(){
        for(int i = 0; i < adjListArray.length; i++){
            System.out.print(i + " --> ");
            for (int j : adjListArray[i]){
                System.out.print(j + "-");
            }
            System.out.println();
        }
    }

    public int numVertices(){
        return vertices;
    }
    
    //Returns the vertex with the greatest Euclidean distance from the center of the board/////
    public int rothTieBreak(int v1, int v2){
        
        int N = (int) Math.sqrt(numVertices());
        int v1x = v1 %N;
        int v1y = (int) Math.floor(v1/N);
        
        int v2x = v2 %N;
        int v2y = (int) Math.floor(v2/N);
        
        int center = (int) numVertices()/2;
        int centerx = center %N;
        int centery = (int) Math.floor(center/N);
        
        double v1Dist = Math.sqrt((Math.pow(centerx - v1x, 2)+Math.pow(centery - v1y, 2)));
        double v2Dist = Math.sqrt((Math.pow(centerx - v2x, 2)+Math.pow(centery - v2y, 2)));
        
        if (v1Dist >= v2Dist){
            return v1;
        }
        else{
            return v2;
        }
    }
    
    //Returns the vertex that meets the specifications of the Warnsdorff heurisitic//////
    public int findNextWithWarnsdorffs(int v){
        int minVertex = -1;
        int minDegree = 10;
        for (int k : adjListArray[v]){
            if(!visited[k]){
                int kDegree = getLegalDegree(k);
                if (kDegree <= minDegree){      
                    if(kDegree == minDegree){                        
                        minVertex = rothTieBreak(k, minVertex);
                    }
                    else{
                        minVertex = k;
                        minDegree = kDegree;
                    }
                }
            }
        }
        return minVertex;
    }
    
    //Returns the number of available, legal moves through the vertex///////
    public int getLegalDegree(int v){
        int sum = 0;
        for (int i : adjListArray[v]){
            if(!visited[i])
                sum++;
        }
        return sum;
    }

    public LinkedList<Integer> adjacentVertices(int v){
        return adjListArray[v];
    }
    
    //The DFS algorithm with a few alterations, can be changed from Warnsdorff to baacktracking on line 133//////
    public void DFS(int start){
        //Static ints for printing sum of paths and nodes traversed//
        sum = 0;
        nodes = 0;
        visited = new boolean[this.numVertices()];
        for (boolean v : visited){
            v = false;
        }
        //Stores path of the knight for printing //////////////
        path = new LinkedList();
        path.add(start);
        visited[start] = true;
        //CHANGE TO SearchFrom BELOW TO USE DFS WITHOUT WARNSDORFF
        SearchFromW(start);
        System.out.println("Tours found: " + sum);
        System.out.println("Number of nodes traversed: " + nodes);
    }
    
    //Basic recursive, backtracking function of DFS, altered from original by making vertex 
    //undiscovered when returning up recusrive chain//
    public void SearchFrom(int start){

        nodes++;
        if(path.size() == numVertices()){
            if(adjListArray[0].contains(start)){
                for(int p : path){
                System.out.print(p + " ");
                }
                System.out.println();
                sum++;
            }
            return;
        }

        for(int k : adjacentVertices(start)){
            if(!visited[k]){
                path.add(k);
                visited[k] = true;
                SearchFrom(k);
                visited[k] = false;
                path.remove(path.size() - 1);
            }
        }
    }
    
    //Warnsdorff version of DFS utility function, NOT recusrsive for efficiency reasons//
    public void SearchFromW(int start){
        nodes++;
        if (sum == 1 ) return;
        if (path.size() == numVertices()){
                sum++;
                printBoard(path);
                System.out.println(CheckPath(path));
        }
        
        int nextVertex = findNextWithWarnsdorffs(start);
        if(nextVertex != -1 && !visited[nextVertex]){
            path.add(nextVertex);
            visited[nextVertex] = true;
            SearchFromW(nextVertex);
            visited[nextVertex] = false;
            path.remove(path.size() - 1);
        }
    }
    
    //Utility function to check if path is valid, not guaranteed for Warnsdorff//
    public boolean CheckPath(LinkedList<Integer> path){
        
        for (boolean v : visited){
            if(v == false)
                return false;
        }
        for (int i = 0; i < numVertices() -1 ; i++)
        {
            if(!adjListArray[path.get(i)].contains(path.get(i+1))){
                System.out.println(path.get(i) + "should not proceed " + path.get(i + 1));
                return false;
            }
        }
        return true;
    }
    
    //Utility funstion to print board with path of knight////////////////////////
    public void printBoard(LinkedList<Integer> path){
        int N = (int) Math.sqrt(numVertices());
        int[][] board = new int[N][N];
        for(int i = 0; i < numVertices(); i++){
            int x = path.get(i)%N;
            int y = (int) Math.floor(path.get(i)/N);
            board[y][x] = i + 1;
        }
        for (int[] row : board){
            for (int out : row){
                System.out.printf("%-5d", out);
            }
            System.out.println();
        } 
    }
} 
