package com.runmit.clotho.log.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.util.StringUtils;

import com.runmit.clotho.log.domain.OpLog;
import com.runmit.clotho.log.domain.OpLog.OpType;


/**
 * @author zhipeng.tian
 * 
 * 2014年9月29日
 */

public interface OpLogMapper {
	 @Insert("INSERT INTO OpLog (`content`,`opType`,`opMod`,`systemId`,`createby`) "
	            + "VALUES (#{content},#{opType},#{opMod},#{systemId},#{createby})")
	 @Options(flushCache = true, useGeneratedKeys = true, keyProperty = "id")
	 void addOpLog(OpLog opLog);
	 
	 @SelectProvider(type=GetOpLogs.class,method="getOpLogs")
	 List<OpLog> getOpLogs(@Param("start")int start,@Param("limit")int limit,@Param("opType")OpType opType,@Param("opMod")String opMod,@Param("systemId")String systemId);
	 
	 class GetOpLogs{
		 public String getOpLogs(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select * from OpLog where id!=0");
			 if(para.get("opType")!=null&&(OpType)para.get("opType")!=OpType.ALL){
				 sb.append(" and opType='").append(para.get("opType").toString()).append("'");
			 }
			 if(!StringUtils.isEmpty((String)para.get("opMod"))){
				 sb.append(" and opMod='").append(para.get("opMod")).append("'");
			 }
			 if(para.get("systemId")!=null&&!((String)para.get("systemId")).equalsIgnoreCase("all")){
				 sb.append(" and systemId='").append(para.get("systemId")).append("'");
			 }
			 sb.append(" order by id desc ");
			 sb.append(" start ").append(para.get("start")).append(",").append((Integer)para.get("limit"));
			 return sb.toString();
		 }
	 }
	 
	 @SelectProvider(type=GetOpLogsCount.class,method="getOpLogsCount")
	 long getOpLogsCount(@Param("opType")OpType opType,@Param("opMod")String opMod,@Param("systemId")String systemId);
	 
	 class GetOpLogsCount{
		 public String getOpLogsCount(Map<String, Object> para){
			 StringBuilder sb = new StringBuilder("select count(id) from OpLog where id!=0");
			 if(para.get("opType")!=null&&(OpType)para.get("opType")!=OpType.ALL){
				 sb.append(" and opType='").append(para.get("opType").toString()).append("'");
			 }
			 if(!StringUtils.isEmpty((String)para.get("opMod"))){
				 sb.append(" and opMod='").append(para.get("opMod")).append("'");
			 }
			 if(para.get("systemId")!=null&&!((String)para.get("systemId")).equalsIgnoreCase("all")){
				 sb.append(" and systemId='").append(para.get("systemId")).append("'");
			 }
			 return sb.toString();
		 }
	 }
}
