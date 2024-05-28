package process;

import java.util.ArrayList;
import java.util.List;

public class FCFS {

    private int time;
    private final ProcessPoll pp;
    private final ReadyQueue rq;
    private final List<List<String>> gantt;
    private final List<List<String>> output;
    private Process runningProcess;

    public FCFS(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        rq = new ReadyQueue();
        gantt = new ArrayList<>();
        output = new ArrayList<>();
        runningProcess = null;
    }

    void intoReadyQueue() {
        while (!pp.isEmpty() && time >= pp.peek().getArriveTime()) {
            rq.enqueue(pp.poll());
        }
    }

    public void run() {
        ArrayList<String> tmp = null;
        while (runningProcess != null || !pp.isEmpty() || !rq.isEmpty()) {

            intoReadyQueue();

            if (runningProcess != null) {
                if (runningProcess.getRemainTime() == 0) {
                    runningProcess.setTurnaroundTime(time);
                    output.add(runningProcess.output());
                    assert tmp != null;
                    int startTime = Integer.parseInt(tmp.getLast());
                    tmp.remove(1);
                    tmp.add(String.valueOf(time - startTime));
                    tmp.add(String.valueOf(time));
                    gantt.add(tmp);
                    runningProcess = null;
                } else
                    runningProcess.cpuBurst();
            }
            if (runningProcess == null)
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    tmp = new ArrayList<>();
                    tmp.add(String.valueOf(runningProcess.getPid()));
                    tmp.add(String.valueOf(time));
                    runningProcess.setResponseTime(time);
                }
        }
        rq.setWaiting();
        time++;
    }



    public List<List<String>> getOutput() {
        return this.output;
    }

    public List<List<String>> getGantt() {
        return this.gantt;
    }
}
