package auntsnameday;

import auntsnameday.AuntsNameDay.Names;
import java.util.*;

public class AuntsNameDay {
    //To be able to specify names
    enum Names {
        Voldemort,
        Malfoy,
        Harry,
        Ron,
        Hermione,
        Snape
    }
    //Creating tables
    static Stack<Integer> table1 = new Stack<>();
    static Stack<Integer> table2 = new Stack<>();
    static Stack<Integer> table3 = new Stack<>();
    static Map<String, Stack<Integer>> tables = new HashMap<>();

    static class SeatPlan {
        
        //graph size and adjacency list
        int V;
        LinkedList<Integer>[] adj;
        //Constructor
        SeatPlan(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new LinkedList<>();
            }
            tables.put("Table1", table1);
            tables.put("Table2", table2);
            tables.put("GetOut", table3);

        }
        //Adding dislike connections
        void dislikeAdd(int v, int w) {
            adj[v].add(w);
        }
        //Checking the disliked person is sitting already
        boolean checkDislikePerson(LinkedList<Integer> adj, Stack<Integer> table) {
            for (int i : adj) {
                if (table.contains(i)) {
                    return true;
                }
            }
            return false;
        }
        //Making people sat
        void makePeopleSat(int i) {
            if (!checkDislikePerson(adj[i], tables.get("Table1"))) {
                tables.get("Table1").add(i);
            } else if (!checkDislikePerson(adj[i], tables.get("Table2"))) {
                tables.get("Table2").add(i);
            } else {
                tables.get("GetOut").add(i);
            }
        }
        //DFS
        void createPlan(int s, Vector<Boolean> visited) {
            Stack<Integer> stack = new Stack<>();
            stack.push(s);
            while (stack.empty() == false) {
                s = stack.peek();
                stack.pop();
                if (visited.get(s) == false) {
                    makePeopleSat(s);
                    visited.set(s, true);
                }
                Iterator<Integer> itr = adj[s].iterator();

                while (itr.hasNext()) {
                    int v = itr.next();
                    if (!visited.get(v)) {
                        stack.push(v);
                    }
                }

            }
        }
        //Setting whole nodes unvisited
        void startPlan() {
            Vector<Boolean> visited = new Vector<>(V);
            for (int i = 0; i < V; i++) {
                visited.add(false);
            }
            for (int i = 0; i < V; i++) {
                if (!visited.get(i)) {
                    createPlan(i, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        SeatPlan seat = new SeatPlan(6);//Creating plan
        //Adding disliked people
        seat.dislikeAdd(0, 2);
        seat.dislikeAdd(2, 0);
        seat.dislikeAdd(0, 3);
        seat.dislikeAdd(3, 0);
        seat.dislikeAdd(0, 4);
        seat.dislikeAdd(4, 0);
        seat.dislikeAdd(5, 0);
        seat.dislikeAdd(1, 2);
        seat.dislikeAdd(1, 3);
        seat.dislikeAdd(1, 4);
        seat.dislikeAdd(4, 1);

        seat.startPlan();
        //Showing final result
        tables.entrySet().forEach((mapElement) -> {
            String key = (String) mapElement.getKey();
            Stack<Integer> value = mapElement.getValue();
            System.out.println("-------------------" + key + "-------------------");
            for (int i : value) {
                for (Names j : Names.values()) {
                    if (j.ordinal() == i) {
                        System.out.println(j);
                    }
                }
            }
        });
    }
}
