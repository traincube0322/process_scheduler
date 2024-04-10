public class Main {
    public static void main(String[] args) {
        ProcessPoll pp = new ProcessPoll();
        new FileSelector(pp);
        SFSC sfsc = new SFSC(pp, new ReadyQueue());
        sfsc.run();
    }
}
