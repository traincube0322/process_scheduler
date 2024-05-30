package process;

public class SJF extends Scheduler{
    public SJF(ProcessPoll pp) {
        super(pp);
        rq = new ReadyQueue(new BurstTimeComparator());
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