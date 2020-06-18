package wizards;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

class Wizards implements Runnable {

    public static AtomicInteger finishLine = new AtomicInteger(1);
    private final String name;
    private int position;
    private final int speed;
    private final int V;
    private final LinkedList<Integer>[] adj;
    float time;
    private Thread thread;
    int place;

    Wizards(String name, int position, int speed) {
        V = Labyrinth.getV();
        adj = Labyrinth.getAdj();
        this.name = name;
        this.position = position;
        this.speed = speed;
        time = 0;
    }

    @Override
    public void run() {
        try {
            magicalWand();
        } catch (InterruptedException ex) {
            Logger.getLogger(Wizards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void magicalWand() throws InterruptedException {
        boolean visited[] = new boolean[V];
        Stack<Integer> path = new Stack<>();
        LinkedList<Integer> queue = new LinkedList<>();

        visited[position] = true;
        queue.add(position);
        while (!queue.isEmpty()) {
            position = queue.poll();
            if (position == Labyrinth.getEndPoint()) {
                break;
            }
            System.out.print(name + " is redirected to " + position + " by magical wand\n");
            path.add(position);
            Iterator<Integer> i = adj[position].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
            Thread.sleep((1000 / speed) * 10);
            time += ((1000 / speed) * 10);
        }
        System.out.println(name + " finished the labyrinth, used path is " + (path));
        place = finishLine.getAndIncrement();
        System.out.println(name + "'s Place: " + place);
        
        
    }

    public float getTime() {
        return time;
    }

    public String getWizard() {
        String wizard = "Wizard Name: " + name + "\n"
                + "Wizard Starting Point: " + position + "\n"
                + "Wizard Speed: " + speed + "\n";
        return wizard;
    }

    public int getPlace() {
        return place;
    }

    public void start() throws InterruptedException {
        if (thread == null) {
            thread = new Thread(this, name);
            thread.start();
        }
    }
}