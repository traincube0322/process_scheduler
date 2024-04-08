package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class ButtonsPanel extends JPanel {
    private String[] comboIndex = {"정책 1", "정책 2", "정책 3"};

    public ButtonsPanel() {
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

        add(openBtn);
        add(runBtn);
        add(policyBox);
    }
}
