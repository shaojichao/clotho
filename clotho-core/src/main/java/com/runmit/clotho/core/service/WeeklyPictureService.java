package com.runmit.clotho.core.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.runmit.clotho.core.domain.picture.WeeklyPicture;
import com.runmit.clotho.core.mapper.WeeklyPictureMapper;
import com.runmit.clotho.log.domain.OpLog.OpType;
import com.runmit.clotho.log.service.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hongbin.cao
 *
 * @date 2015年4月7日
 */
@Service
@Transactional
public class WeeklyPictureService {

	@Autowired
    private WeeklyPictureMapper weeklyPictureMapper;
    @Reference(version="1.0.0")
	private OpLogService opLogService;
    
    @Transactional(readOnly = true)
    public List<WeeklyPicture> getList(int start, int limit, String type, String lang){
    	return weeklyPictureMapper.getPictureList(start, limit, type, lang);
    }

    @Transactional(readOnly = true)
    public WeeklyPicture getPicture(int id){
        return this.weeklyPictureMapper.getWeeklyPicture(id);
    }

    @Transactional(readOnly = true)
    public WeeklyPicture getPictureWeekly(String lang){
        return weeklyPictureMapper.getPictureListRest(lang);
    }
    
    @Transactional(readOnly = false)
    public void save(WeeklyPicture weeklyPicture){
    	if(weeklyPicture.getId()==null||weeklyPicture.getId()==0){
    		this.weeklyPictureMapper.saveWeeklyPicture(weeklyPicture);
    		this.opLogService.saveObj(weeklyPicture, OpType.INSERT, "weeklyPicture", "clotho", weeklyPicture.getCreateby());
    	}else{
            WeeklyPicture temp = this.weeklyPictureMapper.getWeeklyPicture(weeklyPicture.getId());
    		this.weeklyPictureMapper.updateWeeklyPicture(weeklyPicture);
    		this.opLogService.updateObj(temp, weeklyPicture, OpType.UPDATE, "weeklyPicture", "clotho", weeklyPicture.getUpdateby());
    	}
    }
    
    @Transactional(readOnly = false)
    public void delete(int id,String oper){
        WeeklyPicture temp = this.weeklyPictureMapper.getWeeklyPicture(id);
    	this.opLogService.saveObj(temp, OpType.DELETE, "weeklyPicture", "clotho", oper);
    	this.weeklyPictureMapper.delete(id);
    }

}
