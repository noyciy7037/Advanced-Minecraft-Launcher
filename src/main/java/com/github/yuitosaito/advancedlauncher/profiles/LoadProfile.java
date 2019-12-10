package com.github.yuitosaito.advancedlauncher.profiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.json.JSONObject;

public class LoadProfile {

    static String profiles = "";

    public static String[] getNameList(String MinecraftDir) {
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

}
