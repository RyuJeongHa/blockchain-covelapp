package com.example.covel;

import android.os.Bundle;
import android.widget.ImageView;

public class Novel_list_item {
    public String novel_inname;


    public void novel_list(String novel_inname){
        this.novel_inname = novel_inname;
    }



    public String getNovel_inname() {
        return novel_inname;
    }
    public void getNovel_inname(String novel_inname) {
        this.novel_inname = novel_inname;
    }
}
