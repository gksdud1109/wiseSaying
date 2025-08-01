package com.back;

public class wiseSay {
    int id;
    String author;
    String content;

    wiseSay(int id, String author, String content){
        this.id = id;
        this.author = author;
        this.content = content;
    }
    wiseSay(String author, String content){
        this.author = author;
        this.content = content;
    }
}
