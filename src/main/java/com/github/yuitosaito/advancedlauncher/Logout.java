package com.github.yuitosaito.advancedlauncher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Logout extends JFrame {

    private JPanel contentPane;
    public static JTextArea textArea = new JTextArea();
    public static JScrollPane scrollPane = new JScrollPane();
    public static Logout frame = new Logout();

    /**
     * Launch the application.
     */
    public static void LogoutStart(ProcessBuilder p) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Log log = new Log(p);
        log.start();
    }

    /**
     * Create the frame.
     */
    public Logout() {
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        contentPane.add(scrollPane, BorderLayout.CENTER);
        textArea.setEditable(false);


        scrollPane.setViewportView(textArea);
    }


}

class Log extends Thread {
    public static ProcessBuilder p;

    Log(ProcessBuilder p) {
        Log.p = p;
    }

    @Override
    public void run() {
        try {
<<<<<<< HEAD
			// プロセスを開始する
			Process process = p.start();

			// 結果を受け取る
			try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("Shift-JIS")))) {
			    String line;
			    while ((line = r.readLine()) != null) {
			        Logout.textArea.setText(Logout.textArea.getText() + "\r\n" + line);
			        Logout.scrollPane.getVerticalScrollBar().setValue(Logout.scrollPane.getVerticalScrollBar().getMaximum());
			    }
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        p.command();
	}
}
=======
            // プロセスを開始する
            Process process = p.start();

            // 結果を受け取る
            try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("Shift-JIS")))) {
                String line;
                while ((line = r.readLine()) != null) {
                    Logout.textArea.setText(Logout.textArea.getText() + "\r\n" + line);
                    Logout.scrollPane.getVerticalScrollBar().setValue(Logout.scrollPane.getVerticalScrollBar().getMaximum());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
>>>>>>> cb16029254cbd5292ca3dba837bbbd680c78c826
