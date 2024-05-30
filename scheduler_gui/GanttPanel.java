package scheduler_gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class GanttPanel extends JPanel {
    private OutputPanel outputPanel;
    private int x = 0;
    private int y = 50;
    private int rectWidth = 10;
    private int rectHeight = 50;
    private final int D = 1; //간트차트 그리기위한 배율 상수
    private int totalWidth; // 화면 전체 너비
    List<List<String>> ganttArray;
    int totalTime = 0;
    double averageWaitingTime = 0;
    JLabel totalInfo;
    public GanttPanel() {
//        setLayout(new FlowLayout(FlowLayout.LEFT));
        totalInfo = new JLabel("전체 실행시간: "+"       평균 대기시간: ");


        add(totalInfo);
    }

    public void paintChart(List<List<String>> ganttArray){
        this.ganttArray = ganttArray;

        calculateTotalWidth(); // 화면 전체 너비 계산
        repaint();
    }
    private void calculateTotalWidth() {
        if (ganttArray != null) {
            totalWidth = 0;
            for (List<String> process : ganttArray) {
                int processTime = Integer.parseInt(process.get(2)); // 프로세스 실행 시간
                totalWidth += processTime * D; // 사각형 너비 누적
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth(); // 패널의 가로 너비 가져오기
        int currentTime = 0;
        int totalProcessTime = 0;
        if(ganttArray!=null){
            currentTime = Integer.parseInt(ganttArray.get(0).get(1));
        }

        // 각 프로세스에 대한 사각형 그리기
        int currentX = x;

        if (ganttArray != null && ganttArray.size() > 0) {


            // 전체 프로세스 실행 시간 계산
            for (List<String> process : ganttArray) {
                int processTime = Integer.parseInt(process.get(2)); // 프로세스 실행 시간
                totalProcessTime += processTime;
            }


            for (List<String> process : ganttArray) {
                int processTime = Integer.parseInt(process.get(2)); // 프로세스 실행 시간
                String processName = process.get(0); // 프로세스 이름

                // 각 프로세스를 사각형으로 그립니다.
                g.setColor(Color.GREEN); // 사각형 색상 설정
                int rectWidth = processTime * panelWidth / totalProcessTime; // 너비 동적 계산
                g.drawRect(currentX, y, rectWidth, rectHeight); // 사각형 그리기
                g.setColor(Color.GREEN); // 텍스트 색상 설정

                // 텍스트의 폭과 높이 계산
                FontMetrics metrics = g.getFontMetrics();
                int textWidth = metrics.stringWidth(processName);
                int textHeight = metrics.getHeight();

                // 텍스트를 사각형 안에 그리기
                int textX = currentX + (rectWidth - textWidth) / 2; // 텍스트의 x 좌표 계산
                int textY = y + (rectHeight - textHeight) / 2 + metrics.getAscent(); // 텍스트의 y 좌표 계산
                g.drawString(processName, textX, textY); // 프로세스 이름 출력

                g.drawString(Integer.toString(currentTime), currentX, y+rectHeight+15); // 시간 출력

                currentX += rectWidth; // 다음 사각형의 x 좌표 조정
                currentTime += processTime;
                //System.out.println("생성!");
            }
        }

        g.drawString(Integer.toString(currentTime), currentX-15, y+rectHeight+15);
        totalTime = totalProcessTime;
        totalInfo.setText("전체 실행시간: "+totalTime+"       평균 대기시간: "+averageWaitingTime);
    }

    public void setAverageWaitingTime(List<List<String>> output) {
        double n = output.size();
        double sum = 0;
        for(List<String> p : output){
            sum += Double.parseDouble(p.get(3));
        }

        averageWaitingTime = sum/n;
    }
}