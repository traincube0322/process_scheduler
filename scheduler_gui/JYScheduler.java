// jiwon_gui 브랜치에서 커밋하기!!!

package scheduler_gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JYScheduler extends JFrame{
    public JYScheduler(){
        setTitle("JYScheduler"); // 창타이틀 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container frame = getContentPane(); // frame 컨테이너 생성
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS)); // Boxlayout으로

        UIMaker();

        // 패널 생성 후 추가
        NameBar nameBar = new NameBar();
        InputPanel inputPanel = new InputPanel();
        OutputPanel outputPanel = new OutputPanel();
        GanttPanel ganttPanel = new GanttPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel(inputPanel, outputPanel, ganttPanel);
        nameBar.setPreferredSize(new Dimension(500, 50));
        inputPanel.setPreferredSize(new Dimension(500, 200));
        outputPanel.setPreferredSize(new Dimension(500, 200));
        ganttPanel.setPreferredSize(new Dimension(500, 150));
        buttonsPanel.setPreferredSize(new Dimension(500, 50));
        frame.add(nameBar);
        frame.add(inputPanel);
        frame.add(outputPanel);
        frame.add(ganttPanel);
        frame.add(buttonsPanel);

        // 창 생성
        setSize(500, 650); // 창 사이즈 설정
        setLocationRelativeTo(null);// JFrame을 화면 중앙에 위치시킴
        SwingUtilities.updateComponentTreeUI(frame);
        setVisible(true);
    }

    void UIMaker(){
        // UIManager를 사용하여 Swing 컴포넌트의 기본 속성 변경
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("TableHeader.font", new Font("Fixed", Font.PLAIN, 15));
        UIManager.put("Table.font", new Font("Fixed", Font.PLAIN, 15));
        UIManager.put("Table.gridColor", Color.GREEN);
        UIManager.put("Label.foreground", Color.GREEN);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.GREEN);
        UIManager.put("Button.font", new Font("Fixed", Font.PLAIN, 20));
        UIManager.put("Table.foreground", Color.GREEN); // 글자색
        UIManager.put("Table.background", Color.BLACK); // 배경색
        UIManager.put("TableHeader.foreground", Color.GREEN); // 테이블 헤더 글자색
        UIManager.put("TableHeader.background", Color.BLACK);
        UIManager.put("ScrollPane.border", new LineBorder(Color.green)); // 스크롤영역 테두리
        UIManager.put("ComboBox.background", Color.BLACK);
        UIManager.put("ComboBox.foreground", Color.GREEN);
    }
}