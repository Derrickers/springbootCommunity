package com.example.community.cache;

import java.util.ArrayList;
import java.util.List;

public class TagCache {
    private List<String> emotion = new ArrayList<>();
    private List<String> study = new ArrayList<>();
    private List<String> life = new ArrayList<>();
    private List<String> surprise = new ArrayList<>();

    public List<String> getEmotion() {
        return emotion;
    }

    public List<String> getStudy() {
        return study;
    }

    public List<String> getLife() {
        return life;
    }

    public List<String> getSurprise() {
        return surprise;
    }

    public void InitTagCache(){
        InitEmotion();
        InitSurprise();
        InitStudy();
        InitLife();
    }

    private void InitStudy(){
        study.add("编程");
        study.add("数学");
        study.add("体育");
        study.add("英语");
    }
    private void InitEmotion(){
        emotion.add("开心");
        emotion.add("失落");
        emotion.add("愤怒");
    }
    private void InitLife(){
        life.add("做饭");
        life.add("运动");
        life.add("健身");
    }
    private void InitSurprise(){
        surprise.add("生日");
        surprise.add("节日");
        surprise.add("纪念日");
    }

}
