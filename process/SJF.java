package process;

import java.util.ArrayList;
import java.util.List;

public class SJF extends Scheduler{

    private final ProcessPriorityQueue pq;

    public SJF(ProcessPoll pp) {
        super(pp);
        pq = new ProcessPriorityQueue(new BurstTimeComparator());
    }

    @Override
    protected void intoReadyQueue() {
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

}