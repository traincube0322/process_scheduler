package process;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    protected int time;
    protected final ProcessPoll pp;
    protected final List<List<String>> output;
    protected final List<List<String>> gantt;
    protected List<String> tmp;
    protected Process runningProcess;

    public Scheduler(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        output = new ArrayList<>();
        gantt = new ArrayList<>();
        tmp = null;
        runningProcess = null;
    }

    protected abstract void intoReadyQueue();

    protected void processEnd() {
        runningProcess.setTurnaroundTime(time);
        output.add(runningProcess.output());
        int startTime = Integer.parseInt(tmp.get(1));
        tmp.add(String.valueOf(time - startTime));
        gantt.add(tmp);
        runningProcess = null;
    }
    public List<List<String>> getOutput() {
        return this.output;
    }

    public List<List<String>> getGantt() {
        return this.gantt;
    }


}
