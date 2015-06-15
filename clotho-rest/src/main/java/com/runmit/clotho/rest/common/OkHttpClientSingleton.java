package com.runmit.clotho.rest.common;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Hongbin.cao on 2015/6/8.
 */

public class OkHttpClientSingleton {

    private volatile static OkHttpClient instance = null;

    private OkHttpClientSingleton(){
    }

    public static OkHttpClient getInstance(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if(instance == null){
            //同步块，线程安全的创建实例
            synchronized (OkHttpClientSingleton.class) {
                //再次检查实例是否存在，如果不存在才真正的创建实例
                if(instance == null){
                    instance = new OkHttpClient();
                }
            }
        }
        return instance;
    }
}