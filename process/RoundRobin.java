package process;

import java.util.ArrayList;

public class RoundRobin extends Scheduler {

    private final int quantum;
    private int tmpTime;
    public RoundRobin(ProcessPoll pp, int quantum) {
        super(pp);
        this.quantum = quantum;
        rq = new ReadyQueue();
    }

    @Override
    protected void timeElapse() {
        if (runningProcess != null) {
            runningProcess.cpuBurst();
            tmpTime++;
        }
        rq.setWaiting();
        time++;
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
                    if (!rq.isEmpty())
                        changeProcess();

                    tmpTime = 0;
                }
            }

            if (runningProcess == null)
                pickProcess();

            timeElapse();
        }
    }
}
