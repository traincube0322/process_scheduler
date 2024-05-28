package process;

import java.util.ArrayList;
import java.util.List;

public class SJF {

    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue ppq;
    private final List<List<String>> output;

    public SJF(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        ppq = new ProcessPriorityQueue(new BurstTimeComparator());
        output = new ArrayList<>();
    }

    void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
            ppq.add(pp.poll());
        }
    }

    public void run() {
        Process runningProcess = null;

        while (runningProcess != null || !pp.isEmpty() || !ppq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null && runningProcess.getRemainTime() == 0) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess.setTurnaroundTime(time);

                output.add(runningProcess.output());
                runningProcess = null;
            }

            if (runningProcess == null) {
                if (!ppq.isEmpty()) {
                    runningProcess = ppq.poll();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            ppq.setWaiting();
            time++;
        }
        System.out.println("SJF END");
    }
}