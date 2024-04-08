// jiwon_gui 브랜치에서 커밋하기!!!

package scheduler_gui;

import javax.swing.*;
import java.awt.*;
class NameBar extends JPanel{
    // 상단 제목 바
    public NameBar(){
        setLayout(new BorderLayout());
        JLabel nameBar = new JLabel("JYScheduler");
        nameBar.setHorizontalAlignment(JLabel.CENTER);
        nameBar.setFont(new Font("MS Gothic", Font.BOLD, 20));

        add(nameBar, BorderLayout.CENTER);
    }
}
class InputPanel extends JPanel{
    public InputPanel(){
        setLayout(new BorderLayout());
        // 상단 제목 바
        JLabel inputBar = new JLabel("input");

        // 입력 받는 표 생성
        String header[] = {"1", "2", "3"};
        String contents[][] = {
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
        };
        JTable table = new JTable(contents, header);
        table.setFillsViewportHeight(true); // 테이블 꽉 차게
        JScrollPane scrollPane = new JScrollPane(table);
        add(inputBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
class OutputPanel extends JPanel{
    public OutputPanel(){
        setLayout(new BorderLayout());
        // 상단 제목 바
        JLabel outputBar = new JLabel("outnput");

        String header[] = {"1", "2", "3"};
        String contents[][] = {
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
                {"박지원", "24", "남"},
        };
        JTable table = new JTable(contents, header);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(outputBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
class GanttPanel extends JPanel{
    public GanttPanel(){
        setBackground(Color.YELLOW);
    }
}

public class JYScheduler extends JFrame{
    public JYScheduler(){
        setTitle("JYScheduler"); // 창타이틀 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container frame = getContentPane(); // frame 컨테이너 생성
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS)); // Gridlayout으로

        // UIManager를 사용하여 Swing 컴포넌트의 기본 속성 변경
        UIManager.put("Panel.background", Color.BLACK);
//        UIManager.put("ViewPort.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.GREEN);
        UIManager.put("Button.foreground", Color.GREEN);
        UIManager.put("Table.foreground", Color.GREEN); // 글자색
        UIManager.put("Table.background", Color.BLACK); // 배경색
        UIManager.put("TableHeader.foreground", Color.GREEN); // 테이블 헤더 글자색
        UIManager.put("TableHeader.background", Color.BLACK);

        NameBar nameBar = new NameBar();
        InputPanel inputPanel = new InputPanel();
        OutputPanel outputPanel = new OutputPanel();
        GanttPanel ganttPanel = new GanttPanel();
        nameBar.setPreferredSize(new Dimension(500, 50));
        inputPanel.setPreferredSize(new Dimension(500, 200));
        outputPanel.setPreferredSize(new Dimension(500, 200));
        ganttPanel.setPreferredSize(new Dimension(500, 150));
        frame.add(nameBar);
        frame.add(inputPanel);
        frame.add(outputPanel);
        frame.add(ganttPanel);

        setSize(500, 600); // 창 사이즈 설정
        setLocationRelativeTo(null);// JFrame을 화면 중앙에 위치시킴
        SwingUtilities.updateComponentTreeUI(frame);
        setVisible(true);
    }
    public static void main(String[] args){
        JYScheduler schedulerFrame = new JYScheduler();
    }
}