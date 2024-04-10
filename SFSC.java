import javax.swing.*;

public class SFSC {

    private int time;
    private final ProcessPoll pp;
    private final ReadyQueue rq;
    public SFSC(ProcessPoll pp, ReadyQueue rq) {
        time = 0;
        this.pp = pp;
        this.rq = rq;
    }

    public void run() {
        int elapsedTime = 0;
        Process runningProcess = null;
        // 프로세스 풀에 없고, READY QUEUE가 비고, RUNNING PROCESS가 없다면 종료
        while (runningProcess != null || (!pp.isEmpty() || !rq.isEmpty())) {
            System.out.println("time : " + time);

            // 도착 시간이 된 프로세스는 PP에서 RQ로 옮김.
            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                rq.enqueue(pp.poll());
            }
            //pp.displayProcessPoll();
            //rq.displayQueue();
            // running 프로세스가 있고, 프로세스가 시간을 다 썼다면?
            if (runningProcess != null && elapsedTime == runningProcess.getBurstTime()) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess = null;
            }
            // 현재 실행중인 프로세스가 없다면?
            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                    elapsedTime = 0;
                }
            }
            // 프로세스 있으면 시간 증가.
            elapsedTime++;

            // RQ 에서 프로세스 하나를 뽑아서 CPU Burst 시킴. : Running Process
            // 그 프로세스의 elapsed time == brust time 이면 RQ에서 새 프로세스 다시 뽑기.

            time++;
            System.out.println();
        }
        System.out.println("SFSC END");
    }
}
