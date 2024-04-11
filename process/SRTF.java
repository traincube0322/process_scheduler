package process;

public class SRTF {
    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue ppq;

    public SRTF(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        ppq = new ProcessPriorityQueue(new RemainTimeComparator());
    }

    public void run() {
        System.out.println("아직 안만듦 메롱 ㅋ");
    }

}
