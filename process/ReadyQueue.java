package process;

import java.util.LinkedList;
import java.util.Queue;

public class ReadyQueue {

    final private Queue <Process> Q;

    public ReadyQueue() { Q = new LinkedList<>(); }
    public void enqueue(Process p) { Q.offer(p); }
    public Process dequeue() { return Q.poll(); }
    public boolean isEmpty() { return Q.isEmpty(); }
    public int size() { return Q.size(); }

    public void setWaiting() {
        for (Process p : Q)
            p.waiting();
    }
    public void displayQueue ()
    {
        if (Q.isEmpty()) { System.out.println("Ready Queue Empty"); }
        else {
            System.out.println("Ready Queue");
            for (Process p : Q)
                System.out.println(p);
            System.out.println();
        }
    }

}
