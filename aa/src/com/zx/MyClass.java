package com.example.lib;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class MyClass {


    public String times;
    public String md5_after;

    public MyClass() {
        //获取当前时间戳
        Date date = new Date();
        times = date.getTime() + "";
        //固定值
        String appkey = "Zz99GmPCGZ3trYebcimb1JrgsrDstnIY";
        String str = appkey + times;
        // md5加密
        md5_after = DigestUtils.md5Hex(str);   // md5加密
    }

}