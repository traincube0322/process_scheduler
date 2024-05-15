package scheduler_gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class GanttPanel extends JPanel {
    private OutputPanel outputPanel;
    private int x = 20;
    private int y = 50;
    private int rectWidth = 10;
    private int rectHeight = 50;
    private final int D = 20; //간트차트 그리기위한 배율 상수
    int totalTime = 5877;
    double averageWaitingTime = 4485;
    List<List<String>> outputArray;
    public GanttPanel(OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
//        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel total = new JLabel("전체 실행시간: "+totalTime+"       평균 대기시간: "+averageWaitingTime);
//        JLabel average = new JLabel();

        add(total);
//        add(average);
    }

    public void paintChart(){
        outputArray = outputPanel.getNewContents();
        System.out.println("간트차트");

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (outputArray != null) {
            int currentX = x;
            for (List<String> process : outputArray) {
                int processTime = Integer.parseInt(process.get(1)); // 프로세스 실행 시간
                String processName = process.get(0); // 프로세스 이름

                // 각 프로세스를 사각형으로 그립니다.
                g.setColor(Color.GREEN); // 사각형 색상 설정
                g.drawRect(currentX, y, processTime*D, rectHeight); // 사각형 그리기
                g.setColor(Color.GREEN); // 텍스트 색상 설정
                g.drawString(processName, currentX + D*processTime/2 -3, y + rectHeight/2+3); // 프로세스 이름 출력

                currentX += processTime*D; // 다음 사각형의 x 좌표 조정
                System.out.println("생성!");
            }
        }
    }
}
