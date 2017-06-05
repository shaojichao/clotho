package com.runmit.clotho.core.service.browser;


import com.runmit.clotho.core.domain.Page;
import com.runmit.clotho.core.domain.browser.PhoneModel;
import com.runmit.clotho.core.mapper.browser.PhoneModelMapper;
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
public class PhoneModelService {

    @Autowired
    private PhoneModelMapper phoneModelMapper;

    /**
     * 分页查出机型信息
     * @param model
     * @param limit
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public Page<PhoneModel> getPhoneModelList(String model, int limit, int page) {
        int startPosition = 0;
        if (1 < page) {
            startPosition = (page - 1) * page;
        }
        List<PhoneModel> phoneModelList = phoneModelMapper.getPhoneModelList(model, startPosition, limit);
        for(PhoneModel phoneModel: phoneModelList){
            phoneModel.setResolution(phoneModel.getHeight() + "*" + phoneModel.getWidth());
        }
        int total = phoneModelMapper.getPhoneModelCount(model);
        return new Page<>(phoneModelList, total);

    }

    /**
     * 新增或修改机型信息
     * @param phoneModel
     */
    @Transactional(readOnly = false)
    public void savePhoneModel(PhoneModel phoneModel){
        if(phoneModel.getId()==null||phoneModel.getId()==0){
            phoneModelMapper.addPhoneModel(phoneModel);
        }else{
            phoneModelMapper.updatePhoneModel(phoneModel);
        }
    }

    /**
     *  通过id删除机型信息
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public int deletePhone(Integer id) throws Exception{
        return phoneModelMapper.deletePhoneModelById(id);
    }
}
