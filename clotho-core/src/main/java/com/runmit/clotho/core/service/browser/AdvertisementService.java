package com.runmit.clotho.core.service.browser;


import com.runmit.clotho.core.domain.browser.Advertisement;
import com.runmit.clotho.core.mapper.browser.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 开屏广告Service
 * @author lgz
 * @version 1.0
 * @date 2017-06-06
 */
@Service
@Transactional
@Component
public class AdvertisementService{

    @Autowired
    private AdvertisementMapper adMapper;

    @Value("${file.download.url}")
    private String downloadUrl;

    /**
     * 分页查找开屏广告信息集合
     * @param start
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public List<Advertisement> getList(int start, int limit){
        List<Advertisement> list = adMapper.getAdList(start, limit);
        for (Advertisement ad: list){
            ad.setImgUploadUrl(downloadUrl);
        }
        return list;
    }

    /**
     * 查找开屏广告信息总条数
     * @return
     */
    @Transactional(readOnly = true)
    public long getCount(){
        return adMapper.getAdCounts();
    }

    /**
     * 添加开屏广告信息
     * @param ad 开屏广告信息对象
     */
    @Transactional(readOnly = false)
    public void addAdInfo(Advertisement ad) throws Exception{
        adMapper.addAdvertisement(ad);
    }

    /**
     * 修改开屏广告信息
     * @param ad 开屏广告信息对象
     */
    @Transactional(readOnly = false)
    public void updateAdInfo(Advertisement ad) throws Exception{
        adMapper.updateAdvertisement(ad);
    }

    /**
     * 根据ID删除开屏广告信息
     * @param id 开屏广告id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public int delAdInfo(int id) throws Exception{
        return adMapper.deleteById(id);
    }

    /**
     * 根据ID查找开屏广告信息
     * @param id  ID
     * @return
     */
    public Advertisement getById(int id){
        return adMapper.getById(id);
    }
    /**
     * 根据机型ID查找已上架状态的开屏广告信息集合
     * @param modeId 机型ID
     * @return
     */
    public Advertisement getInfoByModeId(Integer modeId){
        return adMapper.getInfoByModeId(modeId);
    }

    /**
     * 根据分辨率查找开屏广告信息集合
     * @param width 分辨率宽
     * @param height 分辨率高
     * @return
     */
    @Transactional(readOnly = true)
    public List<Advertisement> getAdListByResolution(int width,int height){
        List<Advertisement> list = adMapper.getAdListByResolution(width, height);
        for (Advertisement ad: list){
            StringBuffer sb=new StringBuffer(downloadUrl);
            sb.append(ad.getAdURL());
            ad.setAdURL(sb.toString());
        }
        return list;
    }

}
