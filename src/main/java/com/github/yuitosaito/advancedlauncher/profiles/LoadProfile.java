package com.github.yuitosaito.advancedlauncher.profiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.json.JSONObject;

public class LoadProfile {

    public static String profiles;

    public static String[] getNameList(String MinecraftDir) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        Object[] oa = jo.getJSONObject("profiles").keySet().toArray();
        String[] stra = new String[oa.length];
        for (int i = 0; i < oa.length; ++i) {
            if (jo.getJSONObject("profiles").getJSONObject(oa[i].toString()).getString("type").equals("latest-release")) {
                stra[i] = "最新のリリース";
            } else if (jo.getJSONObject("profiles").getJSONObject(oa[i].toString()).getString("type").equals("latest-snapshot")) {
                stra[i] = "最新のスナップショット";
            } else {
                stra[i] = jo.getJSONObject("profiles").getJSONObject(oa[i].toString()).getString("name");
                stra[i] = stra[i] + " - " + jo.getJSONObject("profiles").getJSONObject(oa[i].toString()).getString("lastVersionId");
            }

        }
        Arrays.sort(stra);
        return stra;
    }

    public static Boolean isAuth(String MinecraftDir) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        if(!jo.isNull("authenticationDatabase")) {
            return true;
        }else {
            return false;
        }
    }

    public static String getLastAccessToken(String MinecraftDir) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        if(!jo.isNull("authenticationDatabase")) {
            String authuuid = jo.getJSONObject("authenticationDatabase").keySet().toArray()[0].toString();
            String lastaccesstoken = jo.getJSONObject("authenticationDatabase").getJSONObject(authuuid).getString("accessToken");
            return lastaccesstoken;
        }
        return null;
    }

    public static String getClientToken(String MinecraftDir) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        if(!jo.isNull("clientToken")) {
            return jo.getString("clientToken");
        }
        return null;
    }

    public static void deleteAuth(String MinecraftDir) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        if(!jo.isNull("authenticationDatabase")) {
            jo.remove("authenticationDatabase");
            profiles = jo.toString(4);
            writeProfilesJson(MinecraftDir);
        }
    }

    public static void writeProfilesJson(String MinecraftDir,String json) {
        profiles = json;
        writeProfilesJson(MinecraftDir);
    }

    public static void setAccessToken(String MinecraftDir,String Token) {
        if(profiles == null || profiles == "") {
            getProfilesJson(MinecraftDir);
        }
        JSONObject jo = new JSONObject(profiles);
        if(!jo.isNull("authenticationDatabase")) {
            String authuuid = jo.getJSONObject("authenticationDatabase").keySet().toArray()[0].toString();
            jo.getJSONObject("authenticationDatabase").getJSONObject(authuuid).put("accessToken", Token);
            profiles = jo.toString(4);
            writeProfilesJson(MinecraftDir);
        }
    }

    public static String getProfilesJson(String MinecraftDir) {
        // ファイルのパスを指定する
        File json = new File(MinecraftDir + "/launcher_profiles.json");
        String jsonstr = "";
        try {


            // ファイルが存在しない場合に例外が発生するので確認する
            if (!json.exists()) {
                System.out.print("No File");
                return null;
            }

            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(json);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                jsonstr = jsonstr + data;
            }

            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        profiles = jsonstr;
        return profiles;
    }

    public static void writeProfilesJson(String MinecraftDir) {
        try {
            // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter(MinecraftDir + "/launcher_profiles.json");
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));

            //ファイルに書き込む
            pw.println(profiles);

            //ファイルを閉じる
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
