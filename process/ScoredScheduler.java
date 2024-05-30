package process;

public class ScoredScheduler extends Scheduler{
    private final int quantum;
    private int tmpTime;

    public ScoredScheduler(ProcessPoll pp, int quantum) {
        super(pp);
        this.quantum = quantum;
        rq = new ReadyQueue(new ScoreComparator());
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
        while (runningProcess != null | !pp.isEmpty() | !rq.isEmpty()) {
            intoReadyQueue();
            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0) {
                    processEnd();
                    tmpTime = 0;
                }
                else if (tmpTime == quantum) {
                    rq.reScore();
                    if (!rq.isEmpty() && runningProcess.getScore() < rq.peek().getScore())
                        changeProcess();
                    tmpTime = 0;
                }
            }
            pickProcess();
            timeElapse();
        }
    }
}
