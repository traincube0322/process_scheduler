package process;
public class Process {
    private int pid;
    private int priority;
    private int arriveTime;
    private int burstTime;
    private int remainTime;

    public Process(int pid, int priority, int arriveTime, int burstTime) {
        this.pid = pid;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.burstTime = burstTime;
        this.remainTime = burstTime;
    }

    public int getPid() { return pid; }
    public int getPriority() { return priority; }
    public int getArriveTime() { return arriveTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainTime() { return remainTime; }

    public void cpuBurst() { remainTime--; }

    public int[] infoList() {
        int[] l = new int[4];
        l[0] = pid; l[1] = arriveTime; l[2] = burstTime; l[3] = priority;
        return l;
    }
    @Override
    public String toString() {
        return pid + ", " + priority + ", " + arriveTime + ", " + burstTime;
    }
}


