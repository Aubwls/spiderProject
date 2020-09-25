package com.hwt.spider.service.impl;

import com.hwt.spider.entity.param.SpiderMusicParam;
import com.hwt.spider.entity.pojo.SpiderMusic;
import com.hwt.spider.mapper.SpiderMusicMapper;
import com.hwt.spider.service.SpiderMusicService;
import com.hwt.spider.spiderUtils.spiderMusic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: hwt
 * @date: 2020/9/24 22:28
 * @description:
 */
@Service
public class SpiderMusicServiceImpl implements SpiderMusicService {
    @Resource
    private SpiderMusicMapper spiderMusicMapper;

    @Override
    public List<SpiderMusic> getList(String keyword) {
        List<SpiderMusic> spiderMusics = null;
        spiderMusics = spiderMusicMapper.selectByParam(keyword);
        if (spiderMusics.size() == 0){
            spiderMusic.musicList(keyword);
            if (spiderMusics.size() != 0){
                spiderMusicMapper.insertList(spiderMusics);
            }
        }
        return spiderMusics;
    }

    @Override
    public List<SpiderMusic> getPrecis(SpiderMusicParam spiderMusicParam) {
        List<SpiderMusic> spiderMusics = null;
        String author = spiderMusicParam.getAuthor();
        String musicName = spiderMusicParam.getMusicName();
        SpiderMusic spiderMusicTemp = new SpiderMusic();
        spiderMusicTemp.setAuthor(author);
        spiderMusicTemp.setMusicName(musicName);
        spiderMusics = spiderMusicMapper.selectPrecise(spiderMusicTemp);
        if (spiderMusics.size() == 0) {
            if (author != null && !"".equals(author)) {
                List<SpiderMusic> spiderMusicsForAuthor = spiderMusic.musicList(author);
                if (spiderMusicsForAuthor.size() != 0) {
                    spiderMusicMapper.insertList(spiderMusicsForAuthor);
                }
            }
            if (musicName != null && !"".equals(musicName)) {
                List<SpiderMusic> spiderMusicsForMusicName = spiderMusic.musicList(musicName);
                if (spiderMusicsForMusicName.size() != 0) {
                    spiderMusicMapper.insertList(spiderMusicsForMusicName);
                }
            }
        }
        spiderMusics = spiderMusicMapper.selectPrecise(spiderMusicTemp);
        return spiderMusics;
    }

}
