package process;

import java.util.Comparator;
import java.util.PriorityQueue;

class ProcessComparator implements Comparator <Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getArriveTime(), p2.getArriveTime());
    }
}
// arriveTime 기준으로 정렬된 process.Process 객체들의 집합.
// 각 정책이 실행될 때, ProcessPoll에서 Queue로 프로세스를 보내게 됨.
public class ProcessPoll {
    private final PriorityQueue <Process> pq;

    public ProcessPoll() {
        pq = new PriorityQueue<>(new ProcessComparator());
    }

    public void add(Process p) { pq.add(p); }
    public Process peek() { return pq.peek(); }
    public Process poll() { return pq.poll(); }
    public boolean isEmpty() { return pq.isEmpty(); }
    public PriorityQueue<Process> getPq(){
        return pq;
    }
}
