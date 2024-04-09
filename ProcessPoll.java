import java.util.Comparator;
import java.util.TreeSet;

class ProcessComparator implements Comparator <Process> {
    @Override
    public int compare(Process p1, Process p2) {
        if (p1.getArriveTime() < p2.getArriveTime()) return 1;
        if (p1.getArriveTime() > p2.getArriveTime()) return -1;
        return 0;
    }
}
// arriveTime 기준으로 정렬된 Process 객체들의 집합.
// 각 정책이 실행될 때, ProcessPoll에서 ReadyQueue로 프로세스를 보내게 됨.
public class ProcessPoll {
    TreeSet <Process> ts;

    public ProcessPoll () {
        ts = new TreeSet<>(new ProcessComparator);
    }

}
