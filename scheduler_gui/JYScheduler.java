// jiwon_gui 브랜치에서 커밋하기!!!

package scheduler_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JYScheduler extends JFrame{

    public JYScheduler(){
        setTitle("JYScheduler"); // 창타이틀 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container frame = getContentPane(); // frame 컨테이너 생성
        frame.setLayout(new BorderLayout()); // borderlayout으로

        JLabel welcome = new JLabel("윤교바보"); // 라벨 생성
        frame.add(welcome, BorderLayout.NORTH); // welcomflowFrame은 North로

        setSize(700, 800); // 창 사이즈 설정
        setLocationRelativeTo(null);// JFrame을 화면 중앙에 위치시킴
        setVisible(true);

    }
    public static void main(String[] args){
        JYScheduler schedulerFrame = new JYScheduler();
    }
}