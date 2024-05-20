package process;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class ScheduleringPolicy {
    private int time;
    private final ProcessPoll pp;
    private final ReadyQueue rq;
    private final List<List<String>> gantt;
    private final List<List<String>> output;

    public ScheduleringPolicy(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        rq = new ReadyQueue();
        gantt = new ArrayList<>();
        output = new ArrayList<>();
    }

    void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
            rq.enqueue(pp.poll());
        }
    }


}
