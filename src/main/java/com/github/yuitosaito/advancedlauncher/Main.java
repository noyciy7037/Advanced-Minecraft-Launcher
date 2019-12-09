package com.github.yuitosaito.advancedlauncher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.yuitosaito.advancedlauncher.profiles.LoadProfile;

public class Main extends JFrame {

<<<<<<< HEAD
	private JPanel contentPane;
	public static JComboBox<String> comboBox = new JComboBox<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Minecraft Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 529);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		contentPane.add(panel_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 5));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));


		comboBox.setModel(new DefaultComboBoxModel<String>(LoadProfile.getNameList("")));
		comboBox.setBackground(Color.WHITE);
		panel_8.add(comboBox);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.GRAY);
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ver = comboBox.getSelectedItem().toString().substring(comboBox.getSelectedItem().toString().lastIndexOf(" - ")
						, comboBox.getSelectedItem().toString().length()).replace(" - ", "");

		        // 実行する外部プログラムを指定してProcessBuilderインスタンスを生成する
		        // Macの場合はこちら
		        //ProcessBuilder p = new ProcessBuilder("sh", "-c", "echo 'Hello!'");
		          // Windowsの場合はこちら
				//Launch.bat [MINECRAFT_ID]
				String libuuid = LibfromJson.Lib(ver, "C:/Users/琉球サクセス/AppData/Roaming/.minecraft", "windows");
				String JAVA_OPTIONS="-server -splash:splash.png -d64 -da -dsa -Xrs -Xms1G -Xmx1G -XX:NewSize=768M -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -XX:+DisableExplicitGC -Djava.library.path=bin/"+libuuid+" -cp " + CpfromJson.cp(ver, "C:\\Users\\琉球サクセス\\AppData\\Roaming\\.minecraft")+";"+"versions/"+ver+"/"+ver+".jar" + "  net.minecraft.client.main.Main";
		        String[] JAVA_OPTIONS_ARRAY = JAVA_OPTIONS.split(" ");
				//JAVA %JAVA_OPTIONS% --username %1 --version 1.7.10-Forge10.13.4.1614 --gameDir C:\minecraft\lantest --assetsDir assets --assetIndex 1.7 --uuid 899329f1a4574427aa9cab86ee8a7416 --accessToken dcc606b989b14e93b022783796066ac6 --userProperties {} --userType mojang --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --nativeLauncherVersion 307
				List<String> ol = new ArrayList<String>();
				ol.add("java");
				for(int i = 0;i<JAVA_OPTIONS_ARRAY.length;++i) {
					ol.add(JAVA_OPTIONS_ARRAY[i]);
				}

				ol.add("-username");
				ol.add("yuito_3110");
				ol.add("--version");
				ol.add(ver);
				ol.add("--gameDir");
				ol.add("C:\\minecraft\\lantest");
				ol.add("--assetsDir");
				ol.add("assets");
				ol.add("--assetIndex");
				ol.add(ver.substring(0, ver.lastIndexOf(".")));
				ol.add("--uuid");
				ol.add("899329f1a4574427aa9cab86ee8a7416");
				ol.add("--accessToken");
				ol.add("dcc606b989b14e93b022783796066ac6");
				ol.add("--userProperties");
				ol.add("{}");
				ol.add("--userType");
				ol.add("mojang");
				ol.add("-tweakClass");
				ol.add("cpw.mods.fml.common.launcher.FMLTweaker");
				ol.add("--nativeLauncherVersion");
				ol.add("307");
		        ProcessBuilder p = new ProcessBuilder(ol.toArray(new String[ol.size()]));
		        p.redirectErrorStream(true);
		        p.directory(new File("C:/Users/琉球サクセス/AppData/Roaming/.minecraft"));
		        Logout.LogoutStart(p);
			}
		});
		btnPlay.setForeground(new Color(0, 0, 0));
		btnPlay.setBackground(Color.WHITE);
		panel_9.add(btnPlay);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Client", "Server"}));
		panel_10.add(comboBox_1);
	}
=======
    private JPanel contentPane;
    public static JComboBox<String> comboBox = new JComboBox<String>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setTitle("Minecraft Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 529);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.GRAY);
        contentPane.add(panel_1);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(1, 5));

        JPanel panel_8 = new JPanel();
        panel_8.setBackground(Color.GRAY);
        panel.add(panel_8);
        panel_8.setLayout(new GridLayout(0, 1, 0, 0));


        comboBox.setModel(new DefaultComboBoxModel<String>(LoadProfile.getNameList("")));
        comboBox.setBackground(Color.WHITE);
        panel_8.add(comboBox);

        JPanel panel_9 = new JPanel();
        panel_9.setBackground(Color.GRAY);
        panel.add(panel_9);
        panel_9.setLayout(new GridLayout(0, 1, 0, 0));

        JButton btnPlay = new JButton("PLAY");
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ver = comboBox.getSelectedItem().toString().substring(comboBox.getSelectedItem().toString().lastIndexOf(" - ")
                    , comboBox.getSelectedItem().toString().length()).replace(" - ", "");

                // 実行する外部プログラムを指定してProcessBuilderインスタンスを生成する
                // Macの場合はこちら
                //ProcessBuilder p = new ProcessBuilder("sh", "-c", "echo 'Hello!'");
                // Windowsの場合はこちら
                //Launch.bat [MINECRAFT_ID]
                String JAVA_OPTIONS = "-server -splash:splash.png -d64 -da -dsa -Xrs -Xms1G -Xmx1G -XX:NewSize=768M -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -XX:+DisableExplicitGC -Djava.library.path=bin\\12345 -cp " + CpfromJson.cp(ver, "C:\\Users\\琉球サクセス\\AppData\\Roaming\\.minecraft") + ";" + "versions/" + ver + "/" + ver + ".jar" + "  net.minecraft.client.main.Main";
                String[] JAVA_OPTIONS_ARRAY = JAVA_OPTIONS.split(" ");
                //JAVA %JAVA_OPTIONS% --username %1 --version 1.7.10-Forge10.13.4.1614 --gameDir C:\minecraft\lantest --assetsDir assets --assetIndex 1.7 --uuid 899329f1a4574427aa9cab86ee8a7416 --accessToken dcc606b989b14e93b022783796066ac6 --userProperties {} --userType mojang --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --nativeLauncherVersion 307
                List<String> ol = new ArrayList<String>();
                ol.add("java");
                for (int i = 0; i < JAVA_OPTIONS_ARRAY.length; ++i) {
                    ol.add(JAVA_OPTIONS_ARRAY[i]);
                }

                ol.add("-username");
                ol.add("yuito_3110");
                ol.add("--version");
                ol.add(ver);
                ol.add("--gameDir");
                ol.add("C:\\minecraft\\lantest");
                ol.add("--assetsDir");
                ol.add("assets");
                ol.add("--assetIndex");
                ol.add("1.7.10");
                ol.add("--uuid");
                ol.add("899329f1a4574427aa9cab86ee8a7416");
                ol.add("--accessToken");
                ol.add("dcc606b989b14e93b022783796066ac6");
                ol.add("--userProperties");
                ol.add("{}");
                ol.add("--userType");
                ol.add("mojang");
                ol.add("-tweakClass");
                ol.add("cpw.mods.fml.common.launcher.FMLTweaker");
                ol.add("--nativeLauncherVersion");
                ol.add("307");
                ProcessBuilder p = new ProcessBuilder(ol.toArray(new String[ol.size()]));
                p.redirectErrorStream(true);
                p.directory(new File("C:/Users/琉球サクセス/AppData/Roaming/.minecraft"));
                Logout.LogoutStart(p);
            }
        });
        btnPlay.setForeground(new Color(0, 0, 0));
        btnPlay.setBackground(Color.WHITE);
        panel_9.add(btnPlay);

        JPanel panel_10 = new JPanel();
        panel_10.setBackground(Color.GRAY);
        panel.add(panel_10);
        panel_10.setLayout(new GridLayout(0, 1, 0, 0));

        JComboBox<String> comboBox_1 = new JComboBox<String>();
        comboBox_1.setBackground(Color.WHITE);
        comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[]{"Client", "Server"}));
        panel_10.add(comboBox_1);
    }
>>>>>>> cb16029254cbd5292ca3dba837bbbd680c78c826

}
