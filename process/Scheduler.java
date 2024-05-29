package process;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    protected int time;
    protected final ProcessPoll pp;
    protected final List<List<String>> output;
    protected final List<List<String>> gantt;
    protected Process runningProcess;

    public Scheduler(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        output = new ArrayList<>();
        gantt = new ArrayList<>();
        runningProcess = null;
    }

    protected abstract void intoReadyQueue();


    public List<List<String>> getOutput() {
        return this.output;
    }

    public List<List<String>> getGantt() {
        return this.gantt;
    }


}
