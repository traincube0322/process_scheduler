package process;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class BurstTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getBurstTime(), p2.getBurstTime());
    }
}

class RemainTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getRemainTime(), p2.getRemainTime());
    }
}

class ScoreComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) { return Integer.compare(p2.getScore(), p1.getScore()); }
}

public class ReadyQueue {

    private Queue <Process> RQ;

    public ReadyQueue() { RQ = new LinkedList<>(); }
    public ReadyQueue(BurstTimeComparator bc) { RQ = new PriorityQueue<>(new BurstTimeComparator()); }
    public ReadyQueue(RemainTimeComparator rc) { RQ = new PriorityQueue<>(new RemainTimeComparator()); }
    public ReadyQueue(ScoreComparator sc) { RQ = new PriorityQueue<>(new ScoreComparator()); }

    public void offer(Process p) { RQ.offer(p); }
    public Process poll() { return RQ.poll(); }
    public Process peek() { return RQ.peek(); }
    public boolean isEmpty() { return RQ.isEmpty(); }
    public int size() { return RQ.size(); }

    public void setWaiting() {
        for (Process p : RQ)
            p.waiting();
    }

    public void reScore() {
        Queue<Process> RQ = new PriorityQueue<>(new ScoreComparator());
        while (!this.RQ.isEmpty()) {
            Process tmp = this.RQ.poll();
            tmp.reScore();
            RQ.offer(tmp);
        }
        this.RQ = RQ;
    }

}
