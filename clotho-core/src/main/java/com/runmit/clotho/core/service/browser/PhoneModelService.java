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
    
}
