package com.pengcheng.alg4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateFilesForTest {
    /*
    思路：
    1、获取系统临时文件夹路径
    2、创建test.txt
    3、将数据写入文件并关闭流
     */
    static String tmpPath = System.getProperty("java.io.tmpdir");
    static String filename = tmpPath+"//test.txt";

    static void createArrayData(){
        //创建文件
        File testfile = new File(filename);
        if(testfile.exists()) {
            testfile.delete();
            try {
                testfile.createNewFile();
            } catch (IOException e) {
                System.err.print(filename);
                System.err.println("创建失败");
                return;
            }
        }

        //写入数据
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(testfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int a[] = {84,48,68,10,18,98,12,23,54,57,33,16,77,11,29};
        for (int anA : a) {
            pw.write(anA);
            pw.write(" ");
        }
        pw.close();
    }

    static void destroyFile(){
        File file = new File(CreateFilesForTest.filename);
        if(file.exists())
            file.delete();
    }


}
