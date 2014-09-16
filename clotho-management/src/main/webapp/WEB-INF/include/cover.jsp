<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>


<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${ctx}/resources/global/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="${ctx}/resources/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->


<!-- BEGIN PAGE HEADER-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            用户中心 <small>以Metronic中的文件（metronic/admin/templates/admin/*.html）为基础,抽出PAGE LEVEL STYLES/SCRIPTS，更改PAGE HEADER和PAGE CONTENT</small>
        </h3>
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="/">首页</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">（多级导航栏）首页</a>
            </li>
        </ul>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
</div>
<!-- END PAGE HEADER-->

<div class="clearfix">
</div>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <iframe src="../README.html" width="1000" height="600"></iframe>
</div>
<!-- END PAGE CONTENT-->


<!-- BEGIN PAGE LEVEL PLUGINS -->
<!--禁用Jquery vector map,这是jquery向量地图组件,必要时删除resources中对应的目录-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>-->
<!--禁用Jquery flot,这是绘图插件,支持柱状图和线图,必要时删除resources中对应的目录-->
<!--<script src="${ctx}/resources/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>-->
<!--<script src="${ctx}/resources/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>-->
<script src="${ctx}/resources/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="${ctx}/resources/global/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/global/plugins/gritter/js/jquery.gritter.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="${ctx}/resources/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
