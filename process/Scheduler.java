package process;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    protected int time;
    protected final ProcessPoll pp;
    protected ReadyQueue rq;
    protected final List<List<String>> output;
    protected final List<List<String>> gantt;
    protected List<String> tmp;
    protected Process runningProcess;

    public Scheduler(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        rq = null;
        output = new ArrayList<>();
        gantt = new ArrayList<>();
        tmp = null;
        runningProcess = null;
    }

    protected void intoReadyQueue() {
        while (!pp.isEmpty() && time == pp.peek().getArriveTime())
            rq.offer(pp.poll());
    }

    protected void processEnd() {
        runningProcess.setTurnaroundTime(time);
        output.add(runningProcess.output());
        int startTime = Integer.parseInt(tmp.get(1));
        tmp.add(String.valueOf(time - startTime));
        gantt.add(tmp);
        runningProcess = null;
    }

    protected void changeProcess() {
        int startTime = Integer.parseInt(tmp.get(1));
        tmp.add(String.valueOf(time - startTime));
        gantt.add(tmp);
        rq.offer(runningProcess);
        runningProcess = null;
    }

    protected void pickProcess() {
        if (runningProcess == null && !rq.isEmpty()) {
            runningProcess = rq.poll();
            tmp = new ArrayList<>();
            tmp.add(String.valueOf(runningProcess.getPid()));
            tmp.add(String.valueOf(time));
            runningProcess.setResponseTime(time);
        }
    }

    protected void timeElapse() {
        if (runningProcess != null)
            runningProcess.cpuBurst();
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
