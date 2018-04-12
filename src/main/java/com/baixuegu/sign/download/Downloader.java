package com.baixuegu.sign.download;

import com.baixuegu.sign.beans.A;
import com.baixuegu.sign.parse.ParseUtil;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by zhaojunjie on 18/4/13.
 */
public class Downloader {
    private final static Downloader instance = new Downloader();

    public static Downloader getInstance() {
        return instance;
    }

    public void download(String info, File xls, File outDir) {

        String line = "";
        try {
            List<A> aList = ParseUtil.readAlist(xls);


            for (int i = 0; i < aList.size(); i++) {

                if (line.length() > 0) {
                    System.out.print(String.format("\r%" + line.length() + "s\r", " "));
                }


                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                String percent = numberFormat.format((float)(i+1)/(float)aList.size());

                line = String.format("%s %s %s%%", info, xls.getName(), percent);
                System.out.print(line);

                A a = aList.get(i);
                File shopDir = new File(outDir, xls.getName().split("\\.")[0] + "/" + a.shop);
                shopDir.mkdirs();
                for (A.User user : a.users) {
                    org.apache.commons.io.FileUtils.copyURLToFile(new URL(user.url), new File(shopDir, user.name + ".jpg"));
                }
            }
            if (line.length() > 0) {
                System.out.print(String.format("\r%" + line.length() + "s\r", " "));
            }
            System.out.println(info + xls.getName() + " 成功！");
        } catch (Exception e) {
            if (line.length() > 0) {
                System.out.print(String.format("\r%" + line.length() + "s\r", " "));
            }
            System.out.println(info + xls.getName() + " 出错！" + e.getMessage());
        }
    }
}
