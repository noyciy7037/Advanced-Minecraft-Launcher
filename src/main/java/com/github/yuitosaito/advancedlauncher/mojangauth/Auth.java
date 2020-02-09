package com.github.yuitosaito.advancedlauncher.mojangauth;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Auth {
    public static String postJson(String json, String urls) {
        HttpURLConnection uc;
        try {
            URL url = new URL(urls);
            uc = (HttpURLConnection) url.openConnection();
            uc.setRequestMethod("POST");
            uc.setUseCaches(false);
            uc.setDoOutput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(
                new BufferedOutputStream(uc.getOutputStream()));
            out.write(json);
            out.close();
            BufferedReader in;
            if(uc.getResponseCode() == 200) {
                in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            }else {
                in = new BufferedReader(new InputStreamReader(uc.getErrorStream()));
            }
            String line = in.readLine();
            String body = "";
            while (line != null) {
                body = body + line;
                line = in.readLine();
            }
            uc.disconnect();
            return body;

        } catch (IOException e) {
            e.printStackTrace();
            return "client - IOException : " + e.getMessage();
        }
    }
}
