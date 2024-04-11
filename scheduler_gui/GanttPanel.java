package scheduler_gui;

import javax.swing.*;
import java.awt.*;

class GanttPanel extends JPanel {
    int totalTime = 5877;
    double averageWaitingTime = 4485;
    public GanttPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel total = new JLabel("전체 실행시간: "+totalTime+"       평균 대기시간: "+averageWaitingTime);
//        JLabel average = new JLabel();

        add(total);
//        add(average);
    }
}
