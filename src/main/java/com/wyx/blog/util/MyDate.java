package com.wyx.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    public static String getDate(){
        SimpleDateFormat sdf= new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    public static String getDate2(){
        SimpleDateFormat sdf= new SimpleDateFormat("yyy-MM-dd");
        return sdf.format(new Date());
    }
}
