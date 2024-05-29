package process;

import java.util.ArrayList;

public class RoundRobin extends Scheduler {

    private final int quantum;
    private final ReadyQueue rq;
    public RoundRobin(ProcessPoll pp, int quantum) {
        super(pp);
        this.quantum = quantum;
        rq = new ReadyQueue();
    }

    @Override
    protected void intoReadyQueue() {
        while (!pp.isEmpty() && time >= pp.peek().getArriveTime()) {
            rq.enqueue(pp.poll());
        }
    }

    public void run() {
        int tmpTime = 0;
        ArrayList<String> tmp = null;
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {
            intoReadyQueue();

            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0) {
                    runningProcess.setTurnaroundTime(time);
                    output.add(runningProcess.output());
                    int startTime = Integer.parseInt(tmp.get(1));
                    tmp.add(String.valueOf(time - startTime));
                    gantt.add(tmp);
                    runningProcess = null;
                    tmpTime = 0;
                    continue;
                }
                if (!rq.isEmpty() && tmpTime == quantum) {
                    int startTime = Integer.parseInt(tmp.get(1));
                    tmp.add(String.valueOf(time - startTime));
                    gantt.add(tmp);
                    System.out.println("time : " + time + " tmpTime : " + tmpTime + " tmp : " + tmp);
                    rq.enqueue(runningProcess);
                    runningProcess = null;
                    tmpTime = 0;
                }
            }
            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null) {
                runningProcess.cpuBurst();
                tmpTime++;
            }
            rq.setWaiting();
            time++;
        }
    }
}
