public class Main {
    public static void main(String[] args) {
        ProcessPoll pp = new ProcessPoll();
        ReadyQueue rq = new ReadyQueue();
        new FileSelector(pp);
        SFSC sfsc = new SFSC(pp, rq);
        sfsc.run();
    }
}
