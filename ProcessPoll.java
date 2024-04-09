import java.util.Comparator;
import java.util.TreeSet;

class ProcessComparator implements Comparator <Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.getArriveTime(), p2.getArriveTime());
    }
}
// arriveTime 기준으로 정렬된 Process 객체들의 집합.
// 각 정책이 실행될 때, ProcessPoll에서 Queue로 프로세스를 보내게 됨.
public class ProcessPoll {
    private final TreeSet <Process> ts;

    public ProcessPoll() {
        ts = new TreeSet<>(new ProcessComparator());
    }

    public void add(Process p) { ts.add(p); }
    public Process first() { return ts.first(); }
    public Process remove() { return ts.removeFirst(); }
    public boolean isEmpty() { return ts.isEmpty(); }

    public void displayProcessPoll() {
        System.out.println("Process Poll");
        for (Process p : ts)
            System.out.println(p);
    }
}
