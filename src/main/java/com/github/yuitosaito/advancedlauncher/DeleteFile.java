package com.github.yuitosaito.advancedlauncher;

import java.io.File;

public class DeleteFile{
    public static void DelFile(File TestFile) {

        if (TestFile.exists()) {

            //ファイル存在チェック
            if (TestFile.isFile()) {
                //存在したら削除する
                if (TestFile.delete()) {
                }
            //対象がディレクトリの場合
            } else if(TestFile.isDirectory()) {

                //ディレクトリ内の一覧を取得
                File[] files = TestFile.listFiles();

                //存在するファイル数分ループして再帰的に削除
                for(int i=0; i<files.length; i++) {
                	DelFile(files[i]);
                }

                //ディレクトリを削除する
                if (TestFile.delete()) {
                }else{
                    System.out.println("delete failed");
                }
            }
        } else {
            System.out.println("no directory");
        }
    }
}