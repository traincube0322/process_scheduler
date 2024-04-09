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

        time++;
    }


}
