package process;

import java.util.ArrayList;
import java.util.List;
public class FCFS extends Scheduler {

    private final ReadyQueue rq;

    public FCFS(ProcessPoll pp) {
        super(pp);
        rq = new ReadyQueue();
    }

    protected void intoReadyQueue() {
        while (!pp.isEmpty() && time >= pp.peek().getArriveTime()) {
            rq.enqueue(pp.poll());
        }
    }

    public void run() {
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {
            intoReadyQueue();

            if (runningProcess != null && runningProcess.getRemainTime() == 0)
                    processEnd();

            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            rq.setWaiting();
            time++;
        }

    }
}