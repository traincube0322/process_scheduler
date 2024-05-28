package process;

import java.util.ArrayList;
import java.util.List;

public class SJF {

    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue pq;
    private final List<List<String>> output;
    private final List<List<String>> gantt;

    public SJF(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        pq = new ProcessPriorityQueue(new BurstTimeComparator());
        output = new ArrayList<>();
        gantt = new ArrayList<>();
    }

    void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
            pq.add(pp.poll());
        }
    }

    public void run() {
        ArrayList<String> tmp = null;
        Process runningProcess = null;

        while (runningProcess != null || !pp.isEmpty() || !pq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0) {
                    runningProcess.setTurnaroundTime(time);
                    output.add(runningProcess.output());
                    int startTime = Integer.parseInt(tmp.getLast());
                    tmp.removeLast();
                    tmp.add(String.valueOf(time - startTime));
                    gantt.add(tmp);
                    runningProcess = null;
                }
                else
                    runningProcess.cpuBurst();
            }
            else {
                if (!pq.isEmpty()) {
                    runningProcess = pq.poll();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
            }
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