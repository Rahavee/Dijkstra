import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class DJA {
    private LinkedList<Nodes> adj[]; //For creating the adjacency list
    private PriorityQueue<Nodes> Pqueue; // The priority queue
    private int vertices;
    private ArrayList<Nodes> set=new ArrayList<Nodes>();
    private ArrayList<Integer> distance=new ArrayList<Integer>();
    private ArrayList<Integer> explored = new ArrayList<Integer>();
    private ArrayList<Integer> prev = new ArrayList<Integer>();

    //Method for creating the adjacency list
    DJA(int V) {
        vertices = V;
        adj = new LinkedList[vertices];

        for(int i = 0; i < vertices ; i++){
            adj[i] = new LinkedList<Nodes>();
        }

        Pqueue=new PriorityQueue<Nodes>();
    }
    //adding the edges to the adjacency list
    void addEdge(int a, Nodes b){
        adj[a-1].add(b);

    }
    //method to add nodes to the explored set
    void setExplored(int n){

        explored.add(n);
    }
    //method for printing the progress of the program on the command line
    int printPath(int b){

        if(b==7){
            System.out.print("For the node t, a path was found along ");
        }
        else
            System.out.print("For the node "+(b+1)+", a path was found along ");
        int n=b;
        while(n!=0){
            n=prev.get(n);
            if(n!=0)
                System.out.print((n+1)+"-");

        }
        if(n==0)
            System.out.println("s"+" of length "+ distance.get(b));

        return 1;
    }
    //Dijkstra's algorithm
    void algorithm(int s){
        Pqueue.add(new Nodes(1,0));
        for(int i=0; i<vertices; i++){
            //space fillers for the value
            distance.add(i,10000);
            prev.add(i,42);
        }
        explored.clear();
        distance.set(s-1,0);
        System.out.println("Starting with node s");

        int b=0;
        while(b<8){
            //remove from the prioirty queue
           Nodes v=Pqueue.remove();
           while(explored.contains(v.getNode())){
               v=Pqueue.remove();
           }

           if(b!=0)
               System.out.println("The next node removed from the priority queue is "+v.getNode());
           //set it explored
           setExplored(v.getNode());
           int curr=v.getNode()-1;
           //for all the edges from the node
           for (int i=1; i<adj[curr].size();i++){
               //if the node is not explored
               if(!(explored.contains(adj[curr].get(i).getNode()))){
                   //if there exists a shorter path to that node
                   if(distance.get(curr)+adj[curr].get(i).getLength()<distance.get(adj[curr].get(i).getNode()-1)){
                       distance.set(adj[curr].get(i).getNode()-1,distance.get(curr)+adj[curr].get(i).getLength());
                       //add to the priority queue
                       Pqueue.add(new Nodes(adj[curr].get(i).getNode(),distance.get(curr)+adj[curr].get(i).getLength()));
                       prev.set(adj[curr].get(i).getNode()-1,adj[curr].get(0).getNode()-1);
                       printPath(adj[curr].get(i).getNode()-1);

                   }
               }
           }
           b++;

        }

    }

    public static void main(String[] args) {
        System.out.println("This uses a adjaceny list for this program");
        //Adding edges
        DJA d=new DJA(8);
        d.addEdge(1, new Nodes(1,0));
        d.addEdge(1, new Nodes(2,9));
        d.addEdge(1, new Nodes(6,14));
        d.addEdge(1, new Nodes(7,15));
        d.addEdge(2, new Nodes(2,0));
        d.addEdge(2, new Nodes(3,23));
        d.addEdge(3, new Nodes(3,0));
        d.addEdge(3, new Nodes(8,19));
        d.addEdge(3, new Nodes(5,2));
        d.addEdge(4, new Nodes(4,0));
        d.addEdge(4, new Nodes(3,6));
        d.addEdge(4, new Nodes(8,6));
        d.addEdge(5, new Nodes(5,0));
        d.addEdge(5, new Nodes(8,16));
        d.addEdge(5, new Nodes(4,11));
        d.addEdge(6, new Nodes(6,0));
        d.addEdge(6, new Nodes(3,18));
        d.addEdge(6, new Nodes(7,5));
        d.addEdge(6, new Nodes(5,30));
        d.addEdge(7, new Nodes(7,0));
        d.addEdge(7, new Nodes(8,44));
        d.addEdge(7, new Nodes(5,20));
        d.addEdge(8, new Nodes(8,0));

        d.algorithm(1);

    }
}
//Node class for the adjacency list
class Nodes implements Comparable<Nodes>{
    private int node;
    private int length;
    public Nodes( int node, int length){
        this.node=node;
        this.length=length;
    }

    public int getLength() {
        return length;
    }

    public int getNode() {
        return node;
    }

    public void setLength(int l){
        length=l;
    }

    @Override
    public int compareTo(Nodes n) {
        if(length<n.length){
            return -1;
        }
        if(length>n.length){
            return 1;
        }
        return 0;
    }
}
