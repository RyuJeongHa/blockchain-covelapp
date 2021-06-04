package com.example.covel;

public class Covel_home_item {
    private int novel_cover;
    private String novel_name;
    private String novelist_name;
    private String novel_explain;


    public void main_list(int novel_cover, String novel_name, String novelist_name, String novel_explain){
        this.novel_cover = novel_cover;
        this.novel_name = novel_name;
        this.novelist_name = novelist_name;
        this.novel_explain = novel_explain;
    }

    public int getNovel_cover() {
        return novel_cover;
    }
    public void setNovel_cover(int novel_cover) {
        this.novel_cover = novel_cover;
    }

    public String getNovel_name(){
        return novel_name;
    }
    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovelist_name() {
        return novelist_name;
    }
    public void setNovelist_name(String novelist_name) {
        this.novelist_name = novelist_name;
    }

    public String getNovel_explain() {
        return novel_explain;
    }
    public void setNovel_explain(String novel_explain) {
        this.novel_explain = novel_explain;
    }
}