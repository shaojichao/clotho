package com.runmit.clotho.core.service.browser;


import com.runmit.clotho.core.domain.browser.Advertisement;
import com.runmit.clotho.core.mapper.browser.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sjc
 * @date 2017-06-01-10:39
 */
@Service
@Transactional
@Component
public class AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    /**
     * 分页查出开屏广告信息
     * @param start
     * @param limit
     * @return
     */
//    @Transactional(readOnly = true)
//    public Page<Advertisement> getAdvertisementList(int limit, int page) {
//        int startPosition = 0;
//        if (1 < page) {
//            startPosition = (page - 1) * page;
//        }
//        List<Advertisement> phoneModelList = advertisementMapper.getAdvertisementList(startPosition, limit);
//        int total = advertisementMapper.getAdvertisementCount();
//        return new Page<>(phoneModelList, total);
//
//    }
    @Transactional(readOnly = true)
    public List<Advertisement> getAdvertisementList(int start, int limit){
        return advertisementMapper.getAdvertisementList(start, limit);
    }

    /**
     * 查找搜索引擎信息总条数
     * @return
     */
    @Transactional(readOnly = true)
    public long getAdvertisementCount(){
        return advertisementMapper.getAdvertisementCount();
    }

    /**
     * 更新广告状态
     * @param ad
     */
    public void updateAdvertisement(Advertisement ad) {
        advertisementMapper.updateAdvertisement(ad);
    }

    /**
     * 新增或修改机型信息
     * @param phoneModel
     */
//    @Transactional(readOnly = false)
//    public void savePhoneModel(PhoneModel phoneModel){
//        if(phoneModel.getId()==null||phoneModel.getId()==0){
//            phoneModelMapper.addPhoneModel(phoneModel);
//        }else{
//            phoneModelMapper.updatePhoneModel(phoneModel);
//        }
//    }
//
//    /**
//     *  通过id删除机型信息
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    @Transactional(readOnly = false)
//    public int deletePhone(Integer id) throws Exception{
//        return phoneModelMapper.deletePhoneModelById(id);
//    }
}
