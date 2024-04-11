package process;
public class SJF {

    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue ppq;

    public SJF(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        ppq = new ProcessPriorityQueue(new BurstTimeComparator());
    }

    public void run() {
        Process runningProcess = null;

        while (runningProcess != null || !pp.isEmpty() || !ppq.isEmpty()) {
            System.out.println("time : " + time);

            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                ppq.add(pp.poll());
            }

            if (runningProcess != null && runningProcess.getRemainTime() == 0) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess = null;
            }

            if (runningProcess == null) {
                if (!ppq.isEmpty()) {
                    runningProcess = ppq.poll();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            time++;
            System.out.println();
        }
        System.out.println("SJF END");
    }
}