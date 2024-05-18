package process;

import java.util.ArrayList;
import java.util.List;

public class Custom {

    private int time;
    private final ProcessPoll pp;
    private final ProcessPriorityQueue ppq;
    private final List<List<String>> output;

    public Custom(ProcessPoll pp) {
        time = 0;
        this.pp = pp;
        ppq = new ProcessPriorityQueue(new BurstTimeComparator());
        output = new ArrayList<>();
    }

    public void run() {
        Process runningProcess = null;
        int startTime= 0;

        while (runningProcess != null || !pp.isEmpty() || !ppq.isEmpty()) {

            while (!pp.isEmpty() && time == pp.peek().getArriveTime()) {
                ppq.add(pp.poll());
            }

            if (runningProcess != null && runningProcess.getRemainTime() == 0) {
                System.out.println("pid : " + runningProcess.getPid() + " end at " + time);

                output.get(output.size()-1).add(Integer.toString(time-startTime)); // 실행시간 추가
                startTime = time;

                runningProcess.setTurnaroundTime(time);
                runningProcess = null;
            }

            if (runningProcess == null) {
                if (!ppq.isEmpty()) {
                    runningProcess = ppq.poll();
                    System.out.println("pid : " + runningProcess.getPid() + " start at " + time);

                    output.add(new ArrayList<>()); // 새로운 한줄 생성
                    output.get(output.size()-1).add(Integer.toString(runningProcess.getPid())); // pid 추가
                    startTime = time;

                    runningProcess.setResponseTime(time);
                }
            }
            if (runningProcess != null)
                runningProcess.cpuBurst();
            ppq.setWaiting();
            time++;
        }

        for(List<String> l : output){
            for(String s : l){
                System.out.print(s+ "  ");
            }
            System.out.println();
        }
        System.out.println("SJF END");
    }

    public List<List<String>> getGanttOutput() { // 결과 배열 반환
        if (this.output.isEmpty())
            return null;
        return this.output;
    }
}
