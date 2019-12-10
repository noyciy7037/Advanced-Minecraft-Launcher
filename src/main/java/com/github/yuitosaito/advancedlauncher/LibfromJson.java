package com.github.yuitosaito.advancedlauncher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public class LibfromJson {
	public static String Lib(String version,String minecraftDir,String osid) {
		String[] sa = libdata(version,minecraftDir,osid);
		UUID uuid = UUID.randomUUID();
		System.out.println(minecraftDir + "/bin/"+uuid.toString());
		for(int i = 0;i<sa.length;++i) {
			ZipUnCompressUtils.unzip(minecraftDir + "/" + sa[i], minecraftDir + "/bin");
			String name = new File(minecraftDir + "/" + sa[i]).getName().replace(".jar", "");
			File[] fa = new File(minecraftDir + "/bin/"+name).listFiles();
			for(int i1 = 0;i1<fa.length;++i1) {
				if(fa[i1].getName().contains(".dll") || fa[i1].getName().contains(".so") || fa[i1].getName().contains(".dylib") || fa[i1].getName().contains(".jnilib"))
					try {
						new File(minecraftDir + "/bin/"+uuid.toString()).mkdirs();
						Files.copy(Paths.get(fa[i1].getAbsolutePath()), Paths.get(minecraftDir + "/bin/"+uuid.toString()+"/"+fa[i1].getName()));
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			DeleteFile.DelFile(new File(minecraftDir + "/bin/" + name));
		}
		return uuid.toString();
	}

	public static String[] libdata(String versionid,String minecraftdir,String osid/*windows or osx or linux*/) {
		// ファイルのパスを指定する
		File json = new File(minecraftdir + "/versions/"+versionid+"/"+versionid+".json");
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
        JSONObject jo = new JSONObject(jsonstr);
        if(!jo.isNull("jar")) {
            return libdata(jo.getString("jar"),minecraftdir,osid);
        }
        JSONArray ja = jo.getJSONArray("libraries");
        List<String> sl = new ArrayList<String>();
        for(int i = 0;i<ja.length();++i) {
        	if(ja.getJSONObject(i).has("natives")){
        		String os = ja.getJSONObject(i).getJSONObject("natives").getString(osid);
        		if(os != null) {
        			String s = ja.getJSONObject(i).getString("name");
        			String sr = ja.getJSONObject(i).getString("name");
            		int index = s.indexOf(":");
            		s = s.substring(0, index).replace(".", "/")+"/"+s.substring(index+1,s.length());
            		int index1 = s.indexOf(":");
            		s = "libraries/"+s.substring(0, index1)+"/"+s.substring(index1+1,s.length());
            		String libfile = s + "/" + sr.substring(index + 1, sr.length()).replace(":", "-") + "-" + os.replace("${arch}", "64"/* BIT */) + ".jar";
            		if(new File(minecraftdir + "/" + libfile).exists()) {
            			sl.add(libfile);
            		}
        		}
    		}
        }
        return sl.toArray(new String[sl.size()]);
	}
}
