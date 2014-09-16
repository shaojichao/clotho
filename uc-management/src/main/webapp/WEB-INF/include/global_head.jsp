<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<title>Runmit | CDN Management</title>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${ctx}/resources/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctx}/resources/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctx}/resources/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>

<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/resources/global/plugins/respond.min.js"></script>
<script src="${ctx}/resources/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${ctx}/resources/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/resources/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN CORE SCRIPTS -->
<script type="text/javascript">
    var G = G || {};
    G.ctx = '${ctx}';
</script>
<script src="${ctx}/resources/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctx}/resources/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${ctx}/resources/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<!-- END CORE SCRIPTS -->

<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        QuickSidebar.init() // init quick sidebar
        //Index.init();
        //Index.initDashboardDaterange();
        //禁用Jquery vector map,这是jquery向量地图组件
        //Index.initJQVMAP(); // init index page's custom scripts
        //Index.initCalendar(); // init index page's custom scripts
        //Index.initCharts(); // init index page's custom scripts
        //Index.initChat();
        //Index.initMiniCharts();
        //Index.initIntro();
        //Tasks.initDashboardWidget();
    });

    function showContent(model, el) {
        $('.sub-menu li').removeClass('active');
        $(el).parent().addClass('active');
        //if (model === 'javascript:;') {
        //   return;
        //}
        $('#pageContent').load(G.ctx + model);
        return true;
    }
</script>


