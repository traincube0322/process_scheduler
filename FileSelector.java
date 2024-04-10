import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileSelector {
    FileSelector(ProcessPoll pp) {
        File file = null;
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            file = fc.getSelectedFile();
        else {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Scanner sc = null;
        StringTokenizer st;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                st = new StringTokenizer(line, ", ");
                int pid = Integer.parseInt(st.nextToken());
                int priority = Integer.parseInt(st.nextToken());
                int arriveTime = Integer.parseInt(st.nextToken());
                int burstTime = Integer.parseInt(st.nextToken());
                pp.add(new Process(pid, priority, arriveTime, burstTime));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
