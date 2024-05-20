package process;

import java.util.ArrayList;
import java.util.List;

public class FCFS {

    private int time;
    private final ProcessPoll pp;
    private final ReadyQueue rq;
    private final List<List<String>> gantt;
    private final List<List<String>> output;

    public FCFS(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        rq = new ReadyQueue();
        gantt = new ArrayList<>();
        output = new ArrayList<>();
    }

    public void run() {
        Process runningProcess = null;
        // 프로세스 풀에 없고, READY QUEUE가 비고, RUNNING PROCESS가 없다면 종료
        while (runningProcess != null || (!pp.isEmpty() || !rq.isEmpty())) {
            // 도착 시간이 된 프로세스는 PP에서 RQ로 옮김.
            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                rq.enqueue(pp.poll());
            }
            // running 프로세스가 있고, 프로세스가 시간을 다 썼다면?
            // runningProcess is null.
            if (runningProcess != null && runningProcess.getRemainTime() == 0) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess.setTurnaroundTime(time);
                output.add(runningProcess.output());
                runningProcess = null;
            }
            // 현재 실행중인 프로세스가 없다면?
            // RQ 에서 프로세스 하나를 뽑아서 CPU Burst 시킴. : Running process.Process
            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            rq.setWaiting();
            time++;
        }
        System.out.println("process FCFS END");
    }

    public List<List<String>> getOutput() {
        if (this.output.isEmpty())
            return null;
        return this.output;
    }

//    public List<List<String>> getGantt() {
//
//    }
}
