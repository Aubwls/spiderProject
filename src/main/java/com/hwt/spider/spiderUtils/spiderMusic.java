package com.hwt.spider.spiderUtils;

import com.hwt.spider.entity.pojo.SpiderMusic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: hwt
 * @date: 2020/9/24 22:51
 * @description:
 */
public class spiderMusic {
    private final static String path = "https://www.sq688.com";
    private static int pageSize = 1;
    private static List<com.hwt.spider.entity.pojo.SpiderMusic> spiderMusics = new ArrayList<>();
    private static void parseMusic(String keyword,Integer pageNum){
        BufferedWriter bw = null;
        try{
            Document doc = null;
            if(pageNum == 1){
                doc = Jsoup.connect(path+"/search.php?key="+keyword)
                        .get();
            }else{
                doc = Jsoup.connect(path+"/search.php?key="+keyword+"&page="+pageNum)
                        .get();
            }
            Elements select = doc.select("div.song").select("tr");
            if(select.size() != 0){
                for (int i = 1; i < select.size() ; i++) {
                    com.hwt.spider.entity.pojo.SpiderMusic spiderMusic = new com.hwt.spider.entity.pojo.SpiderMusic();
                    Element element = select.get(i);
                    Elements song = element.select("td");
                    String downloadUrl = path + song.get(7).select("a[href]").attr("href");
                    Document uri = Jsoup.connect(downloadUrl)
                            .get();
                    spiderMusic.setMusicName(song.get(0).text());
                    spiderMusic.setAuthor(song.get(1).text());
                    spiderMusic.setUrl(uri.select("div.url p.downurl").text());
                    Date date = new Date();
                    spiderMusic.setCreateTime(date);
                    spiderMusics.add(spiderMusic);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bw != null){
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static void pageSize(String keyword){
        try{
            Document pageDoc = Jsoup.connect(path+"/search.php?key="+keyword)
                    .get();
            Elements pageSizeEle = pageDoc.select("div.pagewarp a");
            pageSize = Integer.parseInt(pageSizeEle.get(pageSizeEle.size()-2).text());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static List<SpiderMusic> musicList(String keyword){
        spiderMusics.clear();
        pageSize(keyword);
        for (int i = 1 ; i <= pageSize ; i++){
            parseMusic(keyword,i);
        }
        return spiderMusics;
    }
}
