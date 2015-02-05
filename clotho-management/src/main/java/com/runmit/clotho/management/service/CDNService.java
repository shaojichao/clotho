package com.runmit.clotho.management.service;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import com.runmit.clotho.core.domain.upgrade.Version;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月5日
 */
@Service
public class CDNService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CDNService.class);

	@Autowired
	private RestTemplate restTemplate;
	@Value("${cdn.dispatch.url}")
	private String cdnUrl;
	@Value("${cdn.dispatch.backurl}")
	private String backurl;
	@Value("${cdn.gslb.url}")
	private String gslburl;
	@Value("${cdn.gslb.key}")
	private String gslbkey;

	public int dispatchApp(Version version, String md5, long size) {
		try {
			String url = cdnUrl + "appFileDispatch";
			JSONObject json = new JSONObject();
			json.put("taskId", version.getId());
			json.put("url", version.getPkgurl());
			json.put("appId", version.getClientid());
			json.put("appKey", version.getVersion());
			json.put("size", size);
			json.put("md5", md5);
			json.put("strategy", 3);
			json.put("backUrl", backurl);
			json.put("callbackContext", "clotho file dispatch");
			LOGGER.debug("cdn request {}",url);
			String result = this.restTemplate.postForObject(url, json,
					String.class);
			LOGGER.debug(result);
			JSONObject response = JSONObject.fromObject(result);
			if (response.getInt("status") == 0) {
				return 0;
			} else {
				LOGGER.error("cdn dispatch error", result);
				return 1;
			}
		} catch (Exception ex) {
			LOGGER.error("cdn dispatch request error", ex);
			return -1;
		}
	}

	public String getGSLBUrl(Version version) {
		long ts = System.currentTimeMillis() + 3600000 * 24 * 7300;
		String hwid = version.getId() + "";
		int appid = version.getClientid();
		String appkey = version.getVersion();
		String key = DigestUtils.md5DigestAsHex((hwid+version.getPkgurl()+gslbkey+appid+appkey).getBytes());
		StringBuffer sb = new StringBuffer();
		sb.append(gslburl).append(version.getPkgurl()).append("?appid=")
				.append(appid).append("&appkey=").append(appkey)
				.append("&hwid=").append(hwid).append("kv=1.0&bt=bt&ts=")
				.append(ts).append("&key=").append(key);
		return sb.toString();
	}
}
