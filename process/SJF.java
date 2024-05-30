package process;

import java.util.ArrayList;

public class SJF extends Scheduler{

    private final ProcessPriorityQueue pq;

    public SJF(ProcessPoll pp) {
        super(pp);
        pq = new ProcessPriorityQueue(new BurstTimeComparator());
    }

    @Override
    protected void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
            pq.add(pp.poll());
        }
    }

    public void run() {
        while (runningProcess != null || !pp.isEmpty() || !pq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null && runningProcess.getRemainTime() == 0)
                    processEnd();

            if (runningProcess == null) {
                if (!pq.isEmpty()) {
                    runningProcess = pq.poll();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            pq.setWaiting();
            time++;
        }
    }

}