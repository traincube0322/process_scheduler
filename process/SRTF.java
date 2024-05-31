package process;

public class SRTF extends Scheduler {
    public SRTF(ProcessPoll pp) {
        super(pp);
        rq = new ReadyQueue(new RemainTimeComparator());
    }

    public void run() {
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {
            intoReadyQueue();
            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0)
                    processEnd();
                else if (!rq.isEmpty()) {
                    rq.heapify();
                    if (runningProcess.getRemainTime() > rq.peek().getRemainTime())
                        changeProcess();
                }
            }
            pickProcess();
            timeElapse();
        }
    }
}
