package scheduler_gui;

import process.Process;
import process.ProcessPoll;

import javax.swing.*;
import java.io.*;

public class FileSelector {
    public FileSelector(ProcessPoll pp) {
        File file = selectFile();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
        }
        try {
            readProcessesFromFile(file, pp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File selectFile() {
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        return null;
    }

    private void readProcessesFromFile(File file, ProcessPoll pp) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length != 4) {
                    throw new IOException("파일 형식 오류: " + file.getName());
                }
                int pid = Integer.parseInt(tokens[0]);
                int priority = Integer.parseInt(tokens[1]);
                int arriveTime = Integer.parseInt(tokens[2]);
                int burstTime = Integer.parseInt(tokens[3]);
                pp.add(new Process(pid, priority, arriveTime, burstTime));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
