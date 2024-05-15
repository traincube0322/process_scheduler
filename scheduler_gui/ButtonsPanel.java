package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import process.*;

class ButtonsPanel extends JPanel {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private GanttPanel ganttPanel;
    private ProcessPoll pp = new ProcessPoll();
    private String[] comboIndex = {"FCFS", "Priority", "SJF", "Custom"};
    JComboBox<String> policyBox;

    public ButtonsPanel(InputPanel inputPanel, OutputPanel outputPanel, GanttPanel ganttPanel) {
        this.outputPanel = outputPanel;
        this.inputPanel = inputPanel;
        this.ganttPanel = ganttPanel;
        setLayout(new FlowLayout());

        JButton openBtn = new JButton("file open");
        JButton runBtn = new JButton("run");
        policyBox = new JComboBox<>();
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

            FCFS fcfs = new FCFS(pp, new ReadyQueue());
            Priority priority = new Priority(pp);
            SJF sjf = new SJF(pp);
            Custom custom = new Custom(pp, new ReadyQueue());

            // 현재 선택된 정책에 따라 다른 정책을 실행
            String selectedPolicy = (String) policyBox.getSelectedItem();
            switch (selectedPolicy) {
                case "FCFS":
                    fcfs.run();
                    break;
                case "Priority":
                    priority.run();
                    break;
                case "SJF":
                    sjf.run();
                    break;
                case "Custom":
                    custom.run();
                    output = custom.getOutput();
                    break;
                default:
                    // 기본으로 FCFS 실행
                    fcfs.run();
                    break;
            }
            outputPanel.reTable(output);
            ganttPanel.paintChart();
        }
    }
}
