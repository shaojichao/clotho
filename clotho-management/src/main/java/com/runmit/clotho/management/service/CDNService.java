package com.runmit.clotho.management.service;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.runmit.clotho.core.domain.upgrade.Version;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月5日
 */
@Service
public class CDNService {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${cdn.dispatch.url}")
	private String cdnUrl;
	
	public void dispatchApp(Version version,String md5,long size){
		String url = cdnUrl+"appFileDispatch";
		JSONObject json = new JSONObject();
		json.put("taskId", version.getId());
		json.put("url", "http://192.168.20.176:28888/upload/20150205/790d528c506b4279b93d341888803a15test.jpg");
		json.put("appId",version.getClientid());
		json.put("appKey",version.getVersion());
		json.put("size",size);
		json.put("md5",md5);
		json.put("strategy",3);
		json.put("backUrl","");
		System.out.println(json.toString());
		String result = this.restTemplate.postForObject(url, json, String.class);
		System.out.println(result);
	}
}
