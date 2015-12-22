package com.example.addgroup;

/**
 * Created by Administrator on 2015/9/21.
 */
public class User {

    public static final int TYPE_CHECKED = 1;
    public static final int TYPE_NOCHECKED = 0;

    public String name;
    public int header;
    public int type;

    public User(String name, int header,int type) {
        this.name = name;
        this.header = header;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getHeader() {
        return header;
    }

}
