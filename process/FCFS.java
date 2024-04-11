package process;

public class FCFS {

    private int time;
    private final ProcessPoll pp;
    private final ReadyQueue rq;
    public FCFS(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        rq = new ReadyQueue();
    }

    public void run() {
        Process runningProcess = null;
        // 프로세스 풀에 없고, READY QUEUE가 비고, RUNNING PROCESS가 없다면 종료
        while (runningProcess != null || (!pp.isEmpty() || !rq.isEmpty())) {
            // 도착 시간이 된 프로세스는 PP에서 RQ로 옮김.
            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                rq.enqueue(pp.poll());
            }
            //pp.displayProcessPoll();
            //rq.displayQueue();
            // running 프로세스가 있고, 프로세스가 시간을 다 썼다면?
            // runningProcess is null.
            if (runningProcess != null && runningProcess.getRemainTime() == 0) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess = null;
            }
            // 현재 실행중인 프로세스가 없다면?
            // RQ 에서 프로세스 하나를 뽑아서 CPU Burst 시킴. : Running process.Process
            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            time++;
        }
        System.out.println("process FCFS END");
    }
}
