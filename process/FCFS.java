package process;

import java.util.ArrayList;

public class FCFS extends Scheduler {
    public FCFS(ProcessPoll pp) {
        super(pp);
        rq = new ReadyQueue();
    }

    public void run() {
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {
            intoReadyQueue();

            if (runningProcess != null && runningProcess.getRemainTime() == 0)
                    processEnd();

            pickProcess();

            timeElapse();
        }

    }
}