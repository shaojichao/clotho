package com.runmit.clotho.core.util;

import java.io.File;

/**
 * @author zhipeng.tian
 * 
 * 2014年9月28日
 */

public class FileUtils {
	public static void ensureDirExist(String path){
		File file = new File(path);
		if(file.isDirectory()&&!file.exists()){
			file.mkdirs();
		}
	}
}
