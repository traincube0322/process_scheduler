package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class InputPanel extends JPanel {
    public InputPanel() {
        setLayout(new BorderLayout());
        // 상단 제목 바
        JLabel inputBar = new JLabel("input");

        // 입력 받는 표 생성
        String header[] = {"프로세스", "도착시간", "실행시간", "우선순위"};
        String contents[][] = {
                {"박지원", "24", "남", "a"},
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
        add(inputBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
