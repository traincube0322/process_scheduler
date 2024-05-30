package process;

public class ScoredScheduler extends Scheduler{
    public ScoredScheduler(ProcessPoll pp) {
        super(pp);
        rq = new ReadyQueue(new ScoreComparator());
    }

    @Override
    protected void timeElapse() {
        if (runningProcess != null)
            runningProcess.cpuBurst();
        rq.setWaiting();
        rq.reScore();
        time++;
    }

    public void run() {
        while (runningProcess != null | !pp.isEmpty() | !rq.isEmpty()) {
            intoReadyQueue();
            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0)
                    processEnd();
                else if (!rq.isEmpty() && runningProcess.getScore() < rq.peek().getScore())
                    changeProcess();
            }
            pickProcess();
            timeElapse();
        }
    }
}
