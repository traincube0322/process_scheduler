import java.util.LinkedList;
import java.util.Queue;

public class ReadyQueue {

    Queue <Process> Q;

    public ReadyQueue() { Q = new LinkedList<>(); }
    public void enqueue(Process p) { Q.offer(p); }
    public Process dequeue() { return Q.poll(); }
    public boolean isEmpty() { return Q.isEmpty(); }
    public int size() { return Q.size(); }

    public void displayQueue ()
    {
        System.out.println("Ready Queue");
        for (Process p : Q)
            System.out.println(p);
    }


}
