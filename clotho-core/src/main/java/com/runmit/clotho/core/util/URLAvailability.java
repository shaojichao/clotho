package com.runmit.clotho.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * @author sjc
 * @date 2017-10-31-15:37
 */
public class URLAvailability {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLAvailability.class);

    public static boolean isConnect(String urls) {
//        try {
//            URL url = new URL(urls);
//            URLConnection uc = url.openConnection();
//            InputStream inputStream = uc.getInputStream();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
        try {
            URL url = new URL(urls);
            InputStream inputStream = url.openStream();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
