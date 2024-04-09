public class SFSC {

    private int time;
    private ProcessPoll pp;
    private ReadyQueue rq;
    public SFSC(ProcessPoll pp, ReadyQueue rq) {
        time = 0;
        this.pp = pp;
        this.rq = rq;
    }

    public void run() {
        // 프로세스 풀에 없을때 까지, Q에 없을때 까지
        while (!pp.isEmpty() && !rq.isEmpty()) {
            // 이 부분 first 말고 다른 프로세스도 arrive타임인지 확인해야함.
            // arriveTime 기준 min Heap 쓰는게 더 좋을듯?
            if (time == pp.first().getArriveTime()) {
                rq.enqueue(pp.remove());
            }
            // RQ 에서 프로세스 하나를 뽑아서 CPU Burst 시킴. : Running Process
            // 그 프로세스의 elapsed time == brust time 이면 RQ에서 새 프로세스 다시 뽑기.

            time++;
        }
    }
}
