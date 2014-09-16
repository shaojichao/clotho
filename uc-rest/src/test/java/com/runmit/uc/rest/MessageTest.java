package com.runmit.uc.rest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by gg on 2014/9/15.
 */
public class MessageTest {
    /*
 * 发送方法  其他方法同理
 */
    public static int sendSMS(String Mobile,String Content,String send_time) throws MalformedURLException, UnsupportedEncodingException {
        URL url = null;
        String CorpID="TEST02332";//账户名
        String Pwd="123456";//密码
        String send_content= URLEncoder.encode(Content.replaceAll("<br/>", " "), "UTF-8");//发送内容
        System.out.println("http://42.96.149.47:1086/sdk/BatchSend.aspx?CorpID="+CorpID+"&Pwd="+Pwd+"&Mobile="+Mobile+"&Content="+send_content+"&Cell=&SendTime="+send_time);
        url = new URL("http://42.96.149.47:1086/sdk/BatchSend.aspx?CorpID="+CorpID+"&Pwd="+Pwd+"&Mobile="+Mobile+"&Content="+send_content+"&Cell=&SendTime="+send_time);
        BufferedReader in = null;
        int inputLine = 0;
        try {
            System.out.println("开始发送短信手机号码为 ："+Mobile);
            System.out.println("开始发送短信地址为 ："+url.getPath());
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = new Integer(in.readLine()).intValue();
        } catch (Exception e) {
            System.out.println("网络异常,发送短信失败！");
            inputLine=-9;
        }
        System.out.println("结束发送短信返回值：  "+inputLine);
        return inputLine;
    }

    @Test
    public void sendmsg() {
//        try {
//            sendSMS("18611991876", "Java Http方式短信调试已经成功!!!!!", "");
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }
}
