package com.runmit.clotho.rest.controller;

import com.runmit.clotho.core.domain.browser.Advertisement;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import com.runmit.clotho.core.service.browser.AdvertisementService;
import com.runmit.clotho.core.service.browser.PhoneModelService;
import com.runmit.clotho.rest.common.RestConst;
import com.runmit.clotho.rest.domain.AdvertisementResp;
import com.runmit.clotho.rest.domain.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 浏览器管理后台Controller
 * @author lgz
 * @version 1.0
 * @date 2017-06-07
 */
@RestController
public class BrowserController{
	private static final Logger LOGGER = LoggerFactory.getLogger(BrowserController.class);

    @Autowired
    private PhoneModelService modelService;
    @Autowired
    private AdvertisementService adService;

	/**
	 *根据机型ID获取开屏广告信息列表
	 * @param id 机型ID
	 * @param version 版本号
	 * @return
	 */
	@RequestMapping(value = "/ad/getAdList/{id}",method = RequestMethod.GET)
	public CommonResp getAdList(@PathVariable int id, @RequestParam(required = false) String version){
		CommonResp resp = new CommonResp();
		try{
			PhoneModel model = modelService.getModelById(id);
			if(model == null){
				resp.setRtn(RestConst.RTN_FAILED);
				resp.setRtmsg("ID为【"+id+"】的机型不存在!");
				return resp;
			}
			List<Advertisement> adList = adService.getAdListByModeId(id,version);
			AdvertisementResp adResp=new AdvertisementResp();
			adResp.setId(id);
			adResp.setModel(model.getModel());
			adResp.setAdList(adList);

			resp.setData(adResp);
			resp.setRtn(RestConst.RTN_OK);
			resp.setRtmsg("success");
		}catch(Exception e){
			LOGGER.error("BrowserController.getAdList error", e);
			resp.setRtn(RestConst.RTN_ERROR);
			resp.setRtmsg("failed");
		}
		return resp;
	}
}
