public class Main {
    public static void main(String[] args) {
        ProcessPoll pp = new ProcessPoll();
        ReadyQueue rq = new ReadyQueue();

        Process a, b, c, d;
        a = new Process(100, 1, 1, 1);
        b = new Process(2147, 2, 2, 2);
        c = new Process(314, 3, 3, 3);
        d = new Process(450, 4, 4, 4);

        pp.add(d);
        pp.add(b);
        pp.add(c);
        pp.add(a);

        SFSC sfsc = new SFSC(pp, rq);
        sfsc.run();
    }
}
