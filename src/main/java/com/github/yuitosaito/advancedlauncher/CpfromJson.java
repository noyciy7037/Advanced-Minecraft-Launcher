package com.github.yuitosaito.advancedlauncher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class CpfromJson {

    public static String cp(String versionid, String minecraftdir) {
        String JsonData = getVersionJson(versionid,minecraftdir);
        String res = "";

        JSONObject jo = new JSONObject(JsonData);
        JSONArray ja = jo.getJSONArray("libraries");
        int len = ja.length();
        String s = ((JSONObject) ja.get(0)).get("name").toString();
        int index = s.indexOf(":");
        s = s.substring(0, index).replace(".", "/") + "/" + s.substring(index + 1, s.length());
        index = s.indexOf(":");
        s = "libraries/" + s.substring(0, index) + "/" + s.substring(index + 1, s.length());
        File[] fa = new File(minecraftdir + "/" + s).listFiles();
        res = s + "/" + fa[0].getName();
        for (int i = 1; i < len; ++i) {
            s = ((JSONObject) ja.get(i)).get("name").toString();
            index = s.indexOf(":");
            s = s.substring(0, index).replace(".", "/") + "/" + s.substring(index + 1, s.length());
            index = s.indexOf(":");
            s = "libraries/" + s.substring(0, index) + "/" + s.substring(index + 1, s.length());
            fa = new File(minecraftdir + "/" + s).listFiles();

            if (new File(minecraftdir + "/" + s).exists()) {
                res += ";";
                res += s + "/" + fa[0].getName();
            }
        }
        if(!jo.isNull("jar")) {
            res = res + ";" + cp(jo.getString("jar"),minecraftdir);
        }
        return res;
    }

    public static String assetindex(String versionid, String minecraftdir) {
        String JsonData = getVersionJson(versionid,minecraftdir);
        String res = "";
        JSONObject jo = new JSONObject(JsonData);
        if(!jo.isNull("assetIndex")) {
            jo = jo.getJSONObject("assetIndex");
            res = jo.getString("id");
        }else {
            res = jo.getString("assets");
        }
        return res;
    }

    public static String jarname(String versionid, String minecraftdir) {
        String JsonData = getVersionJson(versionid,minecraftdir);
        JSONObject jo = new JSONObject(JsonData);
        if(!jo.isNull("jar")){
            return jo.getString("jar");
        }else {
            return versionid;
        }
    }

    public static String mainClass(String versionid, String minecraftdir) {
        String JsonData = getVersionJson(versionid,minecraftdir);
        JSONObject jo = new JSONObject(JsonData);
        return jo.getString("mainClass");
    }


    public static String Arguments(String versionid, String minecraftdir) {
        String JsonData = getVersionJson(versionid,minecraftdir);
        String res = "";
        JSONObject jo = new JSONObject(JsonData);
        if(!jo.isNull("minecraftArguments")) {
            res = jo.getString("minecraftArguments");
        }else {
            res = jo.getString("minecraftArguments");
        }
        return res;
    }

    public static String getVersionJson(String versionid,String minecraftdir) {
        String JsonData = "";
        try {
            // ファイルのパスを指定する
            File file = new File(minecraftdir + "/versions/" + versionid + "/" + versionid + ".json");

            // ファイルが存在しない場合に例外が発生するので確認する
            if (!file.exists()) {
                System.out.print("No File");
                return null;
            }

            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                JsonData = JsonData + data;
            }

            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonData;
    }
}
