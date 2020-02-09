package com.github.yuitosaito.advancedlauncher;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;

public class Logout extends JFrame {

	public static void LogoutStart(String s,String uuid,String MinecraftDir) {
		Log log = new Log(s,uuid,MinecraftDir);
		log.start();
	}
}

class Log extends Thread{
	public static String s;
	public static String uuid;
	public static String MinecraftDir;
Log(String s,String uuid,String MinecraftDir){
	Log.s = s;
	Log.uuid = uuid;
	Log.MinecraftDir = MinecraftDir;
}
	@Override
	public void run() {
	    ProcessBuilder p = new ProcessBuilder("cmd","/c","start","cmd","/c",new File(".").getAbsolutePath()+"/Launch.bat");
        try {
            Map<String, String> env = p.environment(); //環境変数を取得
            env.put("command", s);
            env.put("minecraftdir", MinecraftDir);
            env.put("libuuid", uuid);
			// プロセスを開始する
            p.directory(new File("."));
            p.command("cmd","/c","start","cmd","/c",new File(".").getAbsolutePath()+"/Launch.bat");
            System.out.println(p.command());
			p.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}