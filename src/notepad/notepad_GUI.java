package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class notepad_GUI extends JFrame{

    JTextArea area;
    JFileChooser chooser = new JFileChooser();

    notepad_GUI(){
        setTitle("메모장");
        setSize(400,600);
        create_menu();

        area = new JTextArea();
        add(new JScrollPane(area));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    void create_menu(){
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                open_file();
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save_file();
            }
        });

        menu.add(open);
        menu.add(save);
        menubar.add(menu);
        setJMenuBar(menubar);
    }
    void open_file(){
        int returnVal = chooser.showOpenDialog(this);   // 파일선택창을 열어줌
        if(returnVal == JFileChooser.APPROVE_OPTION)            // 파일창이 잘 실행됐다면
            try {
                File file = chooser.getSelectedFile();          //파일 고르기
                BufferedReader reader = new BufferedReader(new FileReader(file));   // 파일 읽기
                while (reader.ready()){
                    area.append(reader.readLine()+ "\n");   //파일읽은거를 텍스트창에 띄워줌
                }
            } catch (IOException ioException) {         // 예외 처리
                ioException.printStackTrace();
            }
    }
    void save_file(){
        int returnVal = chooser.showSaveDialog(this);   //파일선택창을 저장버전으로 열어줌
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){ //텍스트를 저장할 파일 지정
                    writer.write(area.getText());
            }catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
}
