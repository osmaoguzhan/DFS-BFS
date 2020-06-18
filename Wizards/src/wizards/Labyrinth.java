package wizards;

import java.util.LinkedList;

public class Labyrinth {

    public static int V;
    public static LinkedList<Integer> adj[];
    public static int endPoint;

    public Labyrinth(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }
    //Setting exit
    public void setEndPoint(int endPoint) {
        Labyrinth.endPoint = endPoint;
    }

    public static int getEndPoint() {
        return endPoint;
    }

    public static int getV() {
        return V;
    }

    public static LinkedList<Integer>[] getAdj() {
        return adj;
    }
    //Undirected graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
}