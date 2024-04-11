package scheduler_gui;

import process.Process;
import process.ProcessPoll;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class OutputPanel extends JPanel {
    String header[] = {"프로세스", "실행시간", "대기시간"};
    String contents[][] = {{"", "", ""},};
    JTable table;
    public OutputPanel() {
        setLayout(new BorderLayout());
        // 상단 제목 바
        JLabel outputBar = new JLabel("output");
        table = new JTable(contents, header);

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

    void reTable(ProcessPoll pp){
//        java.util.List<java.util.List<String>> newContents = new ArrayList<>();
//        int pid[];
//
//        for (Process p : pp.getPq()){
//            pid = p.infoList();
//            java.util.List<String> newRow = new ArrayList<>();
//            for (int value : pid){
//                newRow.add(Integer.toString(value));
//            }
//            newContents.add(newRow);
//        }
//
//        // List<List<String>>을 Object[][]로 변환
//        Object[][] tableData = new Object[newContents.size()][];
//        for (int i = 0; i < newContents.size(); i++) {
//            List<String> row = newContents.get(i);
//            tableData[i] = row.toArray(new String[0]); // List<String>을 String[]로 변환하여 할당
//        }
//
//        // DefaultTableModel 사용하여 테이블 데이터 변경
//        DefaultTableModel model = new DefaultTableModel(tableData, header);
//        table.setModel(model);
    }
}
