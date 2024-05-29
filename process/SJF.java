package process;

import java.util.ArrayList;
import java.util.List;

public class SJF {

    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue pq;
    private final List<List<String>> output;
    private final List<List<String>> gantt;
    private Process runningProcess;


    public SJF(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        pq = new ProcessPriorityQueue(new BurstTimeComparator());
        output = new ArrayList<>();
        gantt = new ArrayList<>();
        runningProcess = null;
    }

    void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
            pq.add(pp.poll());
        }
    }

    public void run() {
        ArrayList<String> tmp = null;

        while (runningProcess != null || !pp.isEmpty() || !pq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null) {
                //System.out.println("Running Process is not null");
                if (runningProcess.getRemainTime() == 0) {
                    runningProcess.setTurnaroundTime(time);
                    output.add(runningProcess.output());
                    int startTime = Integer.parseInt(tmp.get(1));
                    tmp.add(String.valueOf(time - startTime));
                    gantt.add(tmp);
                    runningProcess = null;
                }
            }
            if (runningProcess == null) {
                //System.out.println("Running Process is null");
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

    public List<List<String>> getOutput() {
        return this.output;
    }

    public List<List<String>> getGantt() {
        return this.gantt;
    }
}