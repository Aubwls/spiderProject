package com.hwt.spider.spiderUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author hwt
 * @Date:11:30 2020/9/25
 * @Description
 */
public class spiderFiction {
    private static final String HttpPath = "http://www.bookshuku.com/";
    private static void parse(String keyword){
        try {
            Element ele = Jsoup.connect(HttpPath + "search.html").data("searchkey", keyword).get().select("div.searchTopic").get(0);
            String href = ele.select("a").attr("href");
            String [] str = ele.select("a").text().trim().split("/");
            String fictionName = str[0];
            String [] authorTemp  = str[1].split("：");
            String author = authorTemp[authorTemp.length - 1 ];
            String [] split = href.split("/");
            String downloadPath = Jsoup.connect(HttpPath+"down/"+split[split.length-1]).get()
                    .select("div.globalBox div#Frame tbody tr")
                    .get(5)
                    .select("td a").get(2).attr("href");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //下载在本地
    private static void downloadFiction(String downloadPath, String keyword){
        File file = new File("src/downloadFictionPath/"+keyword+".txt");
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try{
            bos = new BufferedOutputStream(new FileOutputStream(file));
            URL url = new URL(downloadPath);
            bis = new BufferedInputStream(url.openConnection().getInputStream());
            byte[] arr = new byte[1024*8];
            int bytesRead = 0;
            while((bytesRead = bis.read(arr)) != -1){
                bos.write(arr,0,bytesRead);
            }
            bos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                bos.close();
                bis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String keyword = null;
        keyword = new Scanner(System.in).nextLine();
        parse(keyword);
    }
}
