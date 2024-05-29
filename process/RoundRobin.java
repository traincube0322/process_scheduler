package process;

import java.util.ArrayList;

public class RoundRobin extends Scheduler {

    private final int quantum;
    private int tmpTime;
    private final ReadyQueue rq;
    public RoundRobin(ProcessPoll pp, int quantum) {
        super(pp);
        this.quantum = quantum;
        rq = new ReadyQueue();
    }

    @Override
    protected void intoReadyQueue() {
        while (!pp.isEmpty() && time >= pp.peek().getArriveTime()) {
            rq.enqueue(pp.poll());
        }
    }

    public void run() {
        tmpTime = 0;
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0) {
                    processEnd();
                    tmpTime = 0;
                    continue;
                }
                if (tmpTime == quantum) {
                    if (!rq.isEmpty()) {
                        int startTime = Integer.parseInt(tmp.get(1));
                        tmp.add(String.valueOf(time - startTime));
                        gantt.add(tmp);
                        rq.enqueue(runningProcess);
                        runningProcess = null;
                    }
                    tmpTime = 0;
                }
            }

            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
            }

            if (runningProcess != null) {
                runningProcess.cpuBurst();
                tmpTime++;
            }
            rq.setWaiting();
            time++;
        }
    }
}
