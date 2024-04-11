package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import process.*;

class ButtonsPanel extends JPanel {
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private ProcessPoll pp = new ProcessPoll();
    private String[] comboIndex = {"SFSC", "정책 2", "정책 3"};

    public ButtonsPanel(InputPanel inputPanel, OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
        this.inputPanel = inputPanel;
        setLayout(new FlowLayout());

        JButton openBtn = new JButton("file open");
        JButton runBtn = new JButton("run");
        JComboBox<String> policyBox = new JComboBox<>();
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

            // 버튼 눌렀을 때 output패널창 변경
//            outputPanel.reTable(pp);
//            SFSC sfsc = new SFSC(pp, new ReadyQueue());
//            sfsc.run();
            FCFS fcfs = new FCFS(pp, new ReadyQueue());
            fcfs.run();
        }
    }
}
