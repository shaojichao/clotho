package com.runmit.clotho.management.controller.clotho;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runmit.clotho.core.domain.drip.ActivationCode;
import com.runmit.clotho.core.dto.ExtEntity;
import com.runmit.clotho.core.dto.ExtStatusEntity;
import com.runmit.clotho.core.service.ActivationCodeService;
import com.runmit.clotho.core.util.DateUtils;
import com.runmit.clotho.core.util.ShortUuid;
import com.runmit.clotho.management.security.SessionUtil;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年11月9日
 */
@Controller
@RequestMapping(value = "/clotho/code")
public class ActivationCodeController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ActivationCodeController.class);
	@Autowired
	private ActivationCodeService codeService;

	@RequestMapping(value = "/allList.do")
	public @ResponseBody ExtEntity<ActivationCode> getActivationCodes(
			@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			HttpServletRequest request) {
		List<ActivationCode> list = this.codeService.getList(start, limit);
		ExtEntity<ActivationCode> entity = new ExtEntity<ActivationCode>();
		entity.setResult(codeService.count());
		entity.setRows(list);
		return entity;
	}

	@RequestMapping(value = "/save.do")
	public @ResponseBody ExtStatusEntity save(int num, String dateEnd,
			HttpServletRequest request) {
		ExtStatusEntity entity = new ExtStatusEntity();

		try {
			for (int i = 0; i < num; i++) {
				ActivationCode code = new ActivationCode();
				code.setCode(ShortUuid.generateShortUuid());
				code.setDateEnd(DateUtils.parseDate(dateEnd));
				code.setStatus(1);
				code.setCreateby(SessionUtil.getLoginAdminName(request));
				this.codeService.addActivationCode(code);
			}
			entity.setMsg("succeed");
			entity.setSuccess(true);
		} catch (Exception ex) {
			LOGGER.error("save ActivationCode error", ex);
			entity.setMsg("保存失败");
			entity.setSuccess(false);
		}

		return entity;
	}

	@RequestMapping(value = "/excel.do")
	public void excel(int exid, int exnum,int status,HttpServletResponse response) {
		HSSFWorkbook workBook = new HSSFWorkbook();  
        try {  
    		HSSFSheet sheet = workBook.createSheet(); 
    		sheet.setColumnWidth(0, 256*20);
    		sheet.setColumnWidth(1, 256*40);
    		sheet.setColumnWidth(2, 256*10);
    		HSSFRow row = sheet.createRow(0);
        	HSSFCell cell = row.createCell(0);
        	cell.setCellValue("激活码");
        	HSSFCell date = row.createCell(1);
        	date.setCellValue("有效期");
        	HSSFCell statuscell = row.createCell(2);
        	statuscell.setCellValue("是否有效");
            List<ActivationCode>  list = codeService.getListById(exid, exnum,status);
            for(int i=0;i<list.size();i++){
            	row = sheet.createRow(i+1);
            	cell = row.createCell(0);
            	cell.setCellValue(list.get(i).getCode());
            	date = row.createCell(1);
            	date.setCellValue(DateUtils.getDateTime(list.get(i).getDateEnd()));
            	statuscell = row.createCell(2);
            	statuscell.setCellValue(list.get(i).getStatus()==0?"无效":"有效");
            }
    		workBook.setSheetName(0, "激活码");  
    		response.reset();  
            response.setContentType("application/msexcel;charset=UTF-8");  
            response.addHeader("Content-Disposition", "attachment;filename=\""  
                    + new String(("激活码" + ".xls").getBytes("GBK"),  
                            "ISO8859_1") + "\"");  
            OutputStream out = response.getOutputStream();  
            workBook.write(out);  
            out.flush();  
            out.close();  
            workBook.close();
        } catch (Exception e) {  
        	LOGGER.error("excel ActivationCode error", e);
        } 
	}
}
