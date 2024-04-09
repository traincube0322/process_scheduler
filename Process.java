public class Process {
    private int pid;
    private int priority;
    private int arriveTime;
    private int burstTime;

    public Process(int pid, int priority, int arriveTime, int burstTime) {
        this.pid = pid;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.burstTime = burstTime;
    }

    public int getPid() { return pid; }
    public int getPriority() { return priority; }
    public int getArriveTime() { return arriveTime; }
    public int getBurstTime() { return burstTime; }

    @Override
    public String toString() {
        return pid + ", " + priority + ", " + arriveTime + ", " + burstTime;
    }
}
