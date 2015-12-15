package com.example.addgroup;

/**
 * Created by Administrator on 2015/9/21.
 */
public class User {

    private String name;
    private int header;

    public User(String name, int header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public int getHeader() {
        return header;
    }

}
