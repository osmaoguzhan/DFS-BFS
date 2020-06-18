package wizards;

/**
 *
 * @author Oguzhan Osma 
 * University of Lodz 
 * Advanced Algorithms
 */
public class Supervisor {

    public static void main(String args[]) throws InterruptedException {

        Labyrinth labyrinth = new Labyrinth(15);
        //Creating labyrinth
        labyrinth.addEdge(0, 1);
        labyrinth.addEdge(1, 3);
        labyrinth.addEdge(1, 4);
        labyrinth.addEdge(2, 4);
        labyrinth.addEdge(2, 5);
        labyrinth.addEdge(3, 6);
        labyrinth.addEdge(4, 6);
        labyrinth.addEdge(6, 7);
        labyrinth.addEdge(6, 10);
        labyrinth.addEdge(7, 8);
        labyrinth.addEdge(8, 13);
        labyrinth.addEdge(10, 9);
        labyrinth.addEdge(10, 12);
        labyrinth.addEdge(10, 14);
        labyrinth.addEdge(14, 11);
        //Setting exit point as a supervisor
        labyrinth.setEndPoint(11);
        //Creating wizards by specifying name,starting point and speed 
        Wizards harry = new Wizards("Harry Potter", 3, 10);
        Wizards voldemort = new Wizards("Lord Voldemort", 9, 20);
        Wizards ron = new Wizards("Ron Weasley", 5, 40);

        System.out.println("------------------END POINT--------------------");
        System.out.println("The end point of the Labyrinth is " + Labyrinth.getEndPoint());
        System.out.println("----------------------------------------------\n");

        System.out.println(harry.getWizard());
        System.out.println(voldemort.getWizard());
        System.out.println(ron.getWizard());
        //starting threads
        harry.start();
        voldemort.start();
        ron.start();
        //This part is getting wizards place to determine who won
        while (true) {
            if (Wizards.finishLine.get() == 4) {

                int order[] = new int[3];
                order[0] = harry.getPlace();
                order[1] = voldemort.getPlace();
                order[2] = ron.getPlace();

                int min = order[0];
                int temp = 0;
                for (int i = 1; i < order.length; i++) {
                    if (min > order[i]) {
                        min = order[i];
                        temp = i;
                    }
                }
                System.out.println("-----------------------------------WINNER------------------------------------");
                switch (temp) {
                    case 0:
                        System.out.println("Winner is Harry Potter, Total Time: " + harry.getTime()/1000 + " seconds");
                        break;
                    case 1:
                        System.out.println("Winner is Lord Voldemort, Total Time: " + voldemort.getTime()/1000 + " seconds");
                        break;
                    default:
                        System.out.println("Winner is Ron Weasley, Total Time: " + ron.getTime()/1000 + " seconds");
                        break;
                }
                System.out.println("----------------------------------------------------------------------------");
                break;
            }
        }

    }
}