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
        pp.displayProcessPoll();
        rq.displayQueue();
        int elapsedTime = 0;
        Process runningProcess = null;
        // 프로세스 풀에 없을때 까지, Q에 없을때 까지
        while (!pp.isEmpty() || !rq.isEmpty()) {
            System.out.println("time : " + time);
            rq.displayQueue();
            // 이 부분 first 말고 다른 프로세스도 arrive타임인지 확인해야함.
            // arriveTime 기준 min Heap 쓰는게 더 좋을듯?
            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                rq.enqueue(pp.poll());
            }
            if (runningProcess == null) {
                if (!rq.isEmpty()) {
                    runningProcess = rq.dequeue();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);
                    elapsedTime = 0;
                }
            }
            else elapsedTime++;
            if (!(runningProcess == null) && elapsedTime == runningProcess.getBurstTime()) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);
                runningProcess = null;
            }
            // RQ 에서 프로세스 하나를 뽑아서 CPU Burst 시킴. : Running Process
            // 그 프로세스의 elapsed time == brust time 이면 RQ에서 새 프로세스 다시 뽑기.

            time++;
            System.out.println();
        }
        System.out.println("SFSC END");
    }
}
