package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class OutputPanel extends JPanel {
    public OutputPanel() {
        setLayout(new BorderLayout());
        // 상단 제목 바
        JLabel outputBar = new JLabel("output");

        String header[] = {"프로세스", "실행시간", "대기시간"};
        String contents[][] = {
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
        };
        JTable table = new JTable(contents, header);
        // header테두리 변경
        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(null);
        table.getTableHeader().setDefaultRenderer(renderer);
        table.getTableHeader().setBorder(new LineBorder(Color.green));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true); // 테이블 꽉 차게
        JScrollPane scrollPane = new JScrollPane(table);
        table.getTableHeader().setReorderingAllowed(false); // 열 이동 금지 설정
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 수직 스크롤 바를 절대로 표시하지 않음
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 수평 스크롤 바를 절대로 표시하지 않음
        add(outputBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
