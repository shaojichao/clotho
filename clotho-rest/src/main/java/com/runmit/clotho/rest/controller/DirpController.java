package com.runmit.clotho.rest.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.runmit.clotho.core.domain.drip.DripRecord;
import com.runmit.clotho.core.service.DripService;
import com.runmit.clotho.rest.common.OkHttpClientSingleton;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.CommonResp;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月2日
 */
@Controller
@RequestMapping(value = "/drip")
public class DirpController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DirpController.class);

	@Value("${drip.uc.check}")
	private String ucCheckUrl;
	@Value("${drip.pay.present}")
	private String payPresentUrl;
	@Value("${drip.rule.day}")
	private int ruleDay;
	@Value("${drip.rule.days.count}")
	private int daysCount;
	@Value("${drip.present.amount}")
	private int amount;
	
	@Autowired
	private DripService dripService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "drip";
	}

	@RequestMapping(value = "/active")
	public @ResponseBody CommonResp active(@RequestParam("acount") String acount,
			@RequestParam("code") String code) {
		CommonResp resp = new CommonResp();
		try {
			Pattern pattern = Pattern.compile("[0-9a-zA-Z]{12}");
			Matcher matcher = pattern.matcher(code);
			boolean b = matcher.matches();
			if (!b) {
				resp.setRtn(RestConst.RTN_DRIP_CODE_ILLEGAL);
				resp.setRtmsg("激活码不存在");
			} else {
				
				int uid = 382;
				DripRecord last = dripService.getLast(uid);
				if(null != last){
					long count = dripService.getCountByUid(uid, last.getCreatetime(), DateUtils.addDays(last.getCreatetime(), ruleDay));
					if(count>=daysCount){
						resp.setRtn(RestConst.RTN_DRIP_USER_INVALID);
						resp.setRtmsg("超过次数限制");
						return resp;
					}
				}
				
				JSONObject params = new JSONObject();
				params.put("uid", uid);
				params.put("amount", amount);
				params.put("currencyId", 6);
				params.put("countryCode", "");
				params.put("clientId", 5);
				params.put("presentType", 4);
				params.put("operator", "clotho-rest");
				
				OkHttpClient client = OkHttpClientSingleton.getInstance();
				Request request = new Request.Builder()
						.url(payPresentUrl)
						.post(RequestBody.create(
								MediaType.parse("application/json"),
								params.toJSONString())).build();
				Response response = client.newCall(request).execute();
				JSONObject result = JSON.parseObject(response.body().string());
				if(result.getIntValue("rtn")!=0){
					LOGGER.error("drip active pay present {}", result.getString("errMsg"));
					resp.setRtn(RestConst.RTN_ERROR);
					resp.setRtmsg("激活失败");
				}else{
					DripRecord record = new DripRecord();
					record.setAcount(acount);
					record.setUid(uid);
					record.setCode(code);
					record.setAmount(amount);
					dripService.saveDripRecord(record);
					resp.setRtn(RestConst.RTN_OK);
					resp.setRtmsg("激活成功");
				}
				
			}

		} catch (Exception e) {
			LOGGER.error("drip active error", e);
			resp.setRtn(RestConst.RTN_ERROR);
			resp.setRtmsg("系统异常");
		}

		return resp;
	}
}
