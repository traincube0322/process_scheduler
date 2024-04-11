package scheduler_gui;

import javax.swing.*;
import java.awt.*;

class NameBar extends JPanel {
    // 상단 제목 바
    public NameBar() {
        setLayout(new BorderLayout());
        JLabel nameBar = new JLabel("JYScheduler");
        nameBar.setFont(new Font("Fixed", Font.BOLD, 20));
        nameBar.setHorizontalAlignment(JLabel.CENTER);

        add(nameBar, BorderLayout.CENTER);
    }
}
