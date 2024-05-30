package process;

import java.util.ArrayList;

public class Process {
    private final int pid;
    private final int priority;
    private final int arriveTime;
    private final int burstTime;
    private int remainTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;
    private int score;
    public Process(int pid, int priority, int arriveTime, int burstTime) {
        this.pid = pid;
        this.priority = priority;
        this.arriveTime = arriveTime;
        this.burstTime = burstTime;
        this.remainTime = burstTime;
        this.waitingTime = 0;
        this.score = priority;
    }

    public int getPid() { return pid; }
    public int getPriority() { return priority; }
    public int getArriveTime() { return arriveTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainTime() { return remainTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getResponseTime() { return responseTime; }
    public int getScore() { return score; }

    public void cpuBurst() { remainTime--; }
    public void waiting() { waitingTime++; }
    public void reScore() { score = priority + waitingTime; }

    public void setTurnaroundTime(int endTime) { turnaroundTime = endTime - arriveTime; }
    public void setResponseTime(int startTime) { responseTime = startTime - arriveTime; }

    public int[] infoList() {
        int[] l = new int[4];
        l[0] = pid; l[1] = arriveTime; l[2] = burstTime; l[3] = priority;
        return l;
    }

    public ArrayList<String> output() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(Integer.toString(this.pid));
        tmp.add(Integer.toString(this.turnaroundTime));
        tmp.add(Integer.toString(this.responseTime));
        tmp.add(Integer.toString(this.waitingTime));
        return tmp;
    }



    @Override
    public String toString() {
        return "PID : " + pid;
    }
}


