package com.example.covel;

public class Covel_home_item {
    String novelName;
    String novelistName;
    String novelExplain;

    public void main_list(String novelName, String novelistName, String novelExplain){
        this.novelName = novelName;
        this.novelistName = novelistName;
        this.novelExplain = novelExplain;
    }

    public String getNovelName(){
        return novelName;
    }

    public String getNovelistName(){
        return novelistName;
    }

    public String getNovelExplain(){
        return novelExplain;
    }

}


