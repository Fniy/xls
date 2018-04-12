package com.baixuegu.sign;

import com.baixuegu.sign.download.Downloader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    public App() {
    }

    public static void main(String[] args) throws InterruptedException, IOException {


        File inDir = new File("./原始表格");
        File outDir = new File("./自动化导出");

        //清空outDir
        if(outDir.exists()){
            FileUtils.forceDelete(outDir);
        }
        outDir.mkdirs();

        File[] xlsList = inDir.listFiles();
        for (int i = 0; i < xlsList.length; i++) {

            File xls = xlsList[i];

            try {
                String info = String.format("(%s/%s) ", i + 1, xlsList.length);
                Downloader.getInstance().download(info, xls, outDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("执行完毕！");

//        for(int i=0;i<11;i++){
//            System.out.println((i/10d) * 100);
//        }


    }



}
