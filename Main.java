import process.FCFS;
import process.Process;
import process.ProcessPoll;
import process.SJF;
import scheduler_gui.JYScheduler;

public class Main {

    public static void main(String[] args) {
        JYScheduler schedulerFrame = new JYScheduler();
    }
    
//    public static void main(String[] args) {
//        ProcessPoll pp = new ProcessPoll();
//        pp.add(new Process(1, 1, 1, 1));
//        pp.add(new Process(2, 2, 2, 2));
//        pp.add(new Process(4, 4, 4, 4));
//
//        FCFS fcfs = new FCFS(pp);
//        fcfs.run();
//        System.out.println("output : " + fcfs.getOutput());
//        System.out.println();
//        System.out.println("gantt " + fcfs.getGantt());
//
//        SJF sjf = new SJF(pp);
//        sjf.run();
//        System.out.println("output : " + sjf.getOutput());
//        System.out.println();
//        System.out.println("gantt " + sjf.getGantt());
//    }
}