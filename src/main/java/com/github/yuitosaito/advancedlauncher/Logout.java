package com.github.yuitosaito.advancedlauncher;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class Logout extends JFrame {

	public static void LogoutStart(ProcessBuilder p,String uuid) {
		Log log = new Log(p,uuid);
		log.start();
	}
}

class Log extends Thread{
	public static ProcessBuilder p;
	public static String uuid;
Log(ProcessBuilder p,String uuid){
	Log.p = p;
	Log.uuid = uuid;
}
	@Override
	public void run() {
	    List<String> ol = p.command();
        try {
            Map<String, String> env = p.environment(); //環境変数を取得
            String command = "";
            for(int i = 0;i<ol.size();++i) {
                command += ol.get(i) + " ";
            }
            command = command.substring(0, command.length() - 1);
            env.put("command", command);
            env.put("minecraftdir", p.directory().getAbsolutePath());
            env.put("libuuid", uuid);
			// プロセスを開始する
            p.directory(new File("."));
            p.command("cmd","/c","start","cmd","/c",new File(".").getAbsolutePath()+"\\Launch.bat");
            System.out.println(p.command());
			p.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}