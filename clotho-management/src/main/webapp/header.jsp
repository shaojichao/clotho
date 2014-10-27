<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.runmit.clotho.core.domain.admin.Admin" %>
<%@ page import="com.runmit.clotho.management.security.SecurityConstant" %>
<%
	String host = request.getServerName();
	int port = request.getServerPort();
	String path = request.getContextPath();
	Admin admin = (Admin)request.getSession().getAttribute(SecurityConstant.ADMIN_SESSION_ATTRIBUTE);
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/static/ext/packages/ext-theme-neptune/build/resources/ext-theme-neptune-all.css">
<script type="text/javascript" src="<%=path%>/static/ext/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/static/ext/packages/ext-theme-neptune/build/ext-theme-neptune.js"></script>
<script>
	var host = '<%=host%>',
		port = '<%=port%>',
		path = '<%=path%>',
		adminID = '<%=admin==null?-1:admin.getId()%>',
		adminName = '<%=admin==null?"":admin.getName()%>';
	Ext.Ajax.on('requestexception',checkUserSessionStatus, this); 
		function checkUserSessionStatus(conn, response, options, eOpts){ 
			var status = response.status;
			
			if(status == 401){//未登录或者登录过期
				top.location.href = path;
				return;
			}
			if(status == 403){
				Ext.Msg.alert('系统提示', "您无权执行此操作！");return;
			}
		}
</script>