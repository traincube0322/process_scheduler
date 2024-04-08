// jiwon_gui 브랜치에서 커밋하기!!!

package scheduler_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JYScheduler extends JFrame{

    public JYScheduler(){
        setTitle("DeliciousCoffee"); // 창타이틀 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container frame = getContentPane(); // frame 컨테이너 생성

        setSize(500, 500); // 창 사이즈 설정
        setLocationRelativeTo(null);// JFrame을 화면 중앙에 위치시킴
        setVisible(true);

    }
    public static void main(String[] args){
        JYScheduler schedulerFrame = new JYScheduler();
    }
}