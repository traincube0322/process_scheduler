package scheduler_gui;

import process.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class ButtonsPanel extends JPanel {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private GanttPanel ganttPanel;
    private ProcessPoll pp = new ProcessPoll();
    private String[] comboIndex = {"FCFS", "SJF", "SRTF", "RR", "PSRR"};
    JComboBox<String> policyBox;
    // Create a SpinnerModel with initial value 1, min 1, max Integer.MAX_VALUE, step 1
    SpinnerModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);

    // Create a JSpinner with the model
    JSpinner timesliceBox;

    public ButtonsPanel(InputPanel inputPanel, OutputPanel outputPanel, GanttPanel ganttPanel) {
        this.outputPanel = outputPanel;
        this.inputPanel = inputPanel;
        this.ganttPanel = ganttPanel;
        setLayout(new FlowLayout());

        JButton openBtn = new JButton("file open");
        JButton runBtn = new JButton("run");
        policyBox = new JComboBox<>();
        JLabel tsbLabel = new JLabel("TimeSlice : ");
        timesliceBox = new JSpinner(model);
        timesliceBox.setPreferredSize(new Dimension(60, timesliceBox.getPreferredSize().height));
        for (String s : comboIndex) policyBox.addItem(s);

        openBtn.setBorder(new LineBorder(Color.green));
        openBtn.setPreferredSize(new Dimension(100, 40));
        runBtn.setBorder(new LineBorder(Color.green));
        runBtn.setPreferredSize(new Dimension(100, 40));
        policyBox.setBorder(new LineBorder(Color.green));
        policyBox.setPreferredSize(new Dimension(100, 40));

        openBtn.addActionListener(new SelectFile());
        runBtn.addActionListener(new runFile());
        add(openBtn);
        add(runBtn);
        add(policyBox);
        add(tsbLabel);
        add(timesliceBox);
    }

    class SelectFile implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new FileSelector(pp);

            // 버튼 눌렀을 때 input패널창 변경
            inputPanel.reTable(pp);
        }
    }
    class runFile implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(pp.isEmpty()) return;
            List<List<String>> output = new ArrayList<>();
            List<List<String>> gantt = new ArrayList<>();

            // 타임슬라이스 값 얻어오기
            int timeslice = (Integer) timesliceBox.getValue();
            
            FCFS fcfs = new FCFS(pp);
            SJF sjf = new SJF(pp);
            SRTF srtf = new SRTF(pp);
            RoundRobin rr = new RoundRobin(pp, timeslice);
            PriorityScoredRR psrr = new PriorityScoredRR(pp, timeslice);

            // 현재 선택된 정책에 따라 다른 정책을 실행
            String selectedPolicy = (String) policyBox.getSelectedItem();
            switch (selectedPolicy) {
                case "FCFS":
                    fcfs.run();
                    output = fcfs.getOutput();
                    gantt = fcfs.getGantt();
                    break;
                case "SJF":
                    sjf.run();
                    output = sjf.getOutput();
                    gantt = sjf.getGantt();
                    break;
                case "SRTF" :
                    srtf.run();
                    output = srtf.getOutput();
                    gantt = srtf.getGantt();
                    break;
                case "RR" :
                    rr.run();
                    output = rr.getOutput();
                    gantt = rr.getGantt();
                    break;
                case "PSRR" :
                    psrr.run();
                    output = psrr.getOutput();
                    gantt = psrr.getGantt();
                    break;
                default:
                    // 기본으로 FCFS 실행
                    fcfs.run();
                    break;
            }
            outputPanel.reTable(output);
            ganttPanel.paintChart(gantt);
            ganttPanel.setAverageWaitingTime(output);
        }
    }
}
