package com.github.yuitosaito.advancedlauncher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import com.github.yuitosaito.advancedlauncher.mojangauth.Auth;
import com.github.yuitosaito.advancedlauncher.mojangauth.Login;
import com.github.yuitosaito.advancedlauncher.profiles.LoadProfile;

public class Main extends JFrame {

	private JPanel contentPane;
	public static JComboBox<String> comboBox = new JComboBox<String>();
	public static String  MinecraftDir = "";
	public static String UserName = "";
	public static Boolean isAuth = false;
	public static String AccessToken;

	public static void main(String[] args) {

	    MinecraftDir = System.getenv("appdata") + "/.minecraft";

	    isAuth = LoadProfile.isAuth(MinecraftDir);

	    if(!isAuth) {
	        Login.main(null);
	    }else {
	        String LastAccessToken = LoadProfile.getLastAccessToken(MinecraftDir);
	        String ClientToken = LoadProfile.getClientToken(MinecraftDir);
	        if(LastAccessToken != null && ClientToken != null) {
	            String res = Auth.postJson("{\"accessToken\": \"" +
                        LastAccessToken +
                        "\",\"clientToken\": \"" +
                        ClientToken +
                        "\"}"
                 , "https://authserver.mojang.com/refresh");
	            JSONObject jo = new JSONObject(res);
	            if(jo.isNull("error")) {
	                System.out.println(res);
	                LoadProfile.setAccessToken(MinecraftDir, jo.getString("accessToken"));
	                AccessToken = jo.getString("accessToken");
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
	            }else {
	                LoadProfile.deleteAuth(MinecraftDir);
	                Login.main(null);
	            }
	        }else {
	            Login.main(null);
	        }

	    }
	}


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


		comboBox.setModel(new DefaultComboBoxModel<String>(LoadProfile.getNameList(MinecraftDir)));
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
				//https://launchermeta.mojang.com/mc/game/version_manifest.json     verlist
				//https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml      forge
				//https://files.minecraftforge.net/maven/net/minecraftforge/forge/さっきのXMLの中身のどれか/forge-さっきのXMLの中身のどれか-installer.jar
		        // 実行する外部プログラムを指定してProcessBuilderインスタンスを生成する
		        // Macの場合はこちら
		        //ProcessBuilder p = new ProcessBuilder("sh", "-c", "echo 'Hello!'");
		          // Windowsの場合はこちら
				//Launch.bat [MINECRAFT_ID]
				String jarname = CpfromJson.jarname(ver, MinecraftDir);
				String Arguments = CpfromJson.Arguments(ver,MinecraftDir);
				String libuuid = LibfromJson.Lib(ver, MinecraftDir, "windows");
				String JAVA_OPTIONS="-server -splash:splash.png -d64 -da -dsa -Xrs -Xms3G -Xmx3G -XX:NewSize=768M -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -XX:+DisableExplicitGC -Djava.library.path=bin/"+libuuid+" -cp " + CpfromJson.cp(ver, MinecraftDir)+";"+"versions/"+jarname+"/"+jarname+".jar" + "  "+CpfromJson.mainClass(ver, MinecraftDir);
				String Command = "java "+
				JAVA_OPTIONS+" "+Arguments
				.replace("${auth_player_name}", "yuito_3110")
				.replace("${version_name}", ver)
				.replace("${game_directory}", "C:\\minecraft\\boueibu-maruchi")
				.replace("${assets_root}", "assets")
				.replace("${assets_index_name}", CpfromJson.assetindex(ver, MinecraftDir))
				.replace("${auth_uuid}", "899329f1a4574427aa9cab86ee8a7416")
				.replace("${auth_access_token}", AccessToken)
				.replace("${user_properties}", "{}")
				.replace("${user_type}", "mojang")
				;
				System.out.println(Command);
		        Logout.LogoutStart(Command,libuuid,MinecraftDir);
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

}
