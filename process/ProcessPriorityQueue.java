package process;

import java.util.Comparator;
import java.util.PriorityQueue;

class BurstTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getBurstTime(), p2.getBurstTime());
    }
}

class PriorityComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getPriority(), p2.getPriority());
    }
}

class RemainTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getRemainTime(), p2.getRemainTime());
    }
}
public class ProcessPriorityQueue {
    PriorityQueue <Process> PQ;

    public ProcessPriorityQueue(BurstTimeComparator bc) { PQ = new PriorityQueue<>(new BurstTimeComparator()); }
    public ProcessPriorityQueue(PriorityComparator pc) { PQ = new PriorityQueue<>(new PriorityComparator()); }
    public ProcessPriorityQueue(RemainTimeComparator rc) { PQ = new PriorityQueue<>(new RemainTimeComparator()); }

    public void add(Process p) { PQ.add(p); }
    public Process peek() { return PQ.peek(); }
    public Process poll() { return PQ.poll(); }
    public boolean isEmpty() { return PQ.isEmpty(); }

    public void setWaiting() {
        for (Process p : PQ)
            p.waiting();
    }
}