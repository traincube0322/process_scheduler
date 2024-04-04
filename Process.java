public class Process {
    private int pid;
    private int priority;
    private int arrive_time;
    private int response_time;
    private int finish_time;

    public Process(int pid, int priority, int arrive_time, int response_time, int finish_time) {
        this.pid = pid;
        this.priority = priority;
        this.arrive_time = arrive_time;
        this.response_time = response_time;
        this.finish_time = finish_time;
    }

    public int getPid() { return pid; }
    public int getPriority() { return priority; }
    public int getArrive_time() { return arrive_time; }
    public int getResponse_time() { return response_time; }
    public int getFinish_time() { return finish_time; }

    @Override
    public String toString() {
        return pid + ", " + priority + ", " + arrive_time + ", " + response_time + ", " + finish_time;
    }
}
