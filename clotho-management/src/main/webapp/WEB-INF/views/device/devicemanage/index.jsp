<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>


<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<%--<link type="text/css" rel="stylesheet" href="${ctx}/resources/global/plugins/datatables/extensions/TableTools/css/dataTables.tableTools.min.css"/>--%>
<%--<link type="text/css" rel="stylesheet" href="${ctx}/resources/global/plugins/datatables/media/css/dataTables.editor.min.css"/>--%>
<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN PAGE HEADER-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            设备信息管理 <small>提供设备信息的增删改查功能</small>
        </h3>
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="/">首页</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">设备管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">设备信息管理</a>
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
<div class="col-md-12 col-sm-12">
<!-- BEGIN EXAMPLE TABLE PORTLET-->
<div class="portlet box yellow">
<div class="portlet-title">
    <div class="caption">
        <i class="fa fa-user"></i>设备信息
    </div>
    <div class="actions">
            <a class="btn default" data-toggle="modal" href="#add">
                <i class="fa fa-pencil"></i> 新增 </a>
            <a class="btn default" data-toggle="modal" href="#query">
                <i class="fa fa-search"></i> 查询 </a>
    </div>
</div>
<div class="portlet-body">
<table class="table table-striped table-bordered table-hover" id="devicedata">
<thead>
<tr>
    <th>
        设备id
    </th>
    <th>
        设备硬件号
    </th>
    <th>
        设备类型
    </th>
    <th>
        所在区域
    </th>
    <th>
        生产日期
    </th>
    <th>
        操作本行数据
    </th>
</tr>
</thead>
<tbody>
</tbody>
</table>
    <div id="add" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">设备信息添加</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="deviceadd" action="#" class="form-horizontal">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备硬件号</label>
                                <div class="col-md-4">
                                    <input id="devicesnadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                设备的唯一硬件标识号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备类型</label>
                                <div class="col-md-4">
                                    <input id="devicetypeadd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-安卓手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">所在区域</label>
                                <div class="col-md-4">
                                    <input id="locationadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备生产日期</label>
                                <div class="col-md-4">
                                    <input id="producedadd" type="text" class="form-control" placeholder="日期格式:YYYY-MM-DD">
                                            <span class="help-block">
                                                设备生产日期</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn blue">保存</button>
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                    </div>
                </div>
            </div>
        </div>

    <div id="query" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">设备信息查询</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="devicequery" action="#" class="form-horizontal">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备硬件号</label>
                                <div class="col-md-4">
                                    <input id="devicesnquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                设备的唯一硬件标识号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备类型</label>
                                <div class="col-md-4">
                                    <input id="devicetypequery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-安卓手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">所在区域</label>
                                <div class="col-md-4">
                                    <input id="locationquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备生产日期</label>
                                <div class="col-md-4">
                                    <input id="producedquery" type="text" class="form-control" placeholder="日期格式:YYYY-MM-DD">
                                            <span class="help-block">
                                                设备生产日期</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn blue">查询</button>
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>
            </div>
        </div>
    </div>

    <div id="modify" class="modal fade" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">设备信息修改</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="devicemodify" action="#" class="form-horizontal">
                        <div class="form-body">

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备硬件id</label>
                                <div class="col-md-4">
                                    <input id="deviceidmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                设备硬件id</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备硬件号</label>
                                <div class="col-md-4">
                                    <input id="devicesnmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                设备的唯一硬件标识号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备类型</label>
                                <div class="col-md-4">
                                    <input id="devicetypemodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-安卓手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">所在区域</label>
                                <div class="col-md-4">
                                    <input id="locationmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备生产日期</label>
                                <div class="col-md-4">
                                    <input id="producedmodify" type="text" class="form-control" placeholder="日期格式:YYYY-MM-DD">
                                            <span class="help-block">
                                                设备生产日期</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn blue">修改</button>
                                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>
            </div>
        </div>
    </div>

<div id="delete" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">设备信息删除</h4>
            </div>
            <div class="modal-body form">
                <!-- BEGIN FORM-->
                <form id="devicedelete" action="#" class="form-horizontal">
                    <div class="form-body">

                        <div class="form-group">
                            <div class="col-md-4">
                                <input id="deviceiddelete" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                是否确认删除该记录</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn blue">删除</button>
                            <button type="button" data-dismiss="modal" class="btn default">取消</button>
                        </div>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>
    </div>
</div>
</div>
<!-- END EXAMPLE TABLE PORTLET-->
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/media/js/dataTables.editor.min.js"></script>--%>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/page/device/devicemanage/devicemanage.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL INIT-->
<script>
    jQuery(document).ready(function() {
        initform();
        $("#devicedelete").submit(function() {
            $.ajax({
                type : 'POST',
                url : 'device/deletedeviceinfo',
                data: {deviceid:$("#deviceiddelete").val()},
                dataType : 'json',
                success : function(data) {
                    $("#delete").modal('hide');
                    initform();
                },
                error : function() {
                    alert("删除失败");
                }
            });
        });
        $("#devicemodify").submit(function() {
            $.ajax({
                type : 'GET',
                url : 'device/modifydeviceinfo',
                data: {deviceid:$("#deviceidmodify").val(),devicesn:$("#devicesnmodify").val(),devicetype:$("#devicetypemodify").val(),location:$("#locationmodify").val(),produceddate:$("#producedmodify").val()},
                dataType : 'json',
                success : function(data) {
                    $("#modify").modal('hide');
                    if ($.fn.dataTable.isDataTable('#devicedata')) {
                        table = $('#devicedata').DataTable();
                        table.destroy();
                    }
                    DeviceTableManaged.init(data);
                },
                error : function() {
                    alert("修改失败");
                }
            });
        });
        $("#devicequery").submit(function() {
            $.ajax({
                type : 'GET',
                url : 'device/querydeviceinfo',
                data: {devicesn:$("#devicesnquery").val(),devicetype:$("#devicetypequery").val(),location:$("#locationquery").val(),produceddate:$("#producedquery").val()},
                dataType : 'json',
                success : function(data) {
                    $("#query").modal('hide');
                    if ($.fn.dataTable.isDataTable('#devicedata')) {
                        table = $('#devicedata').DataTable();
                        table.destroy();
                    }
                    DeviceTableManaged.init(data);
                },
                error : function() {
                    alert("查询失败");
                }
            });
        });
        $("#deviceadd").submit(function() {
                $.ajax({
                    type : 'POST',
                    url : 'device/adddeviceinfo',
                    data: {devicesn:$("#devicesnadd").val(),devicetype:$("#devicetypeadd").val(),location:$("#locationadd").val(),produceddate:$("#producedadd").val()},
                    dataType : 'json',
                    success : function(data) {
                        $("#add").modal('hide');
                        initform();
                        flag = true;
                    },
                    error : function() {
                        alert("新增数据失败");
                    }
                });
        });
    })
    function updateDevice(data){
        $("#deviceidmodify").val(data).attr("readonly","readonly");
        $.ajax({
            type: "GET",
            data:{deviceid:data},
            url: "device/getdeviceinfobyid",
            dataType: "json",
            success: function(data) {
                $("#devicesnmodify").val(data["devicesn"]);
                $("#devicetypemodify").val(data["devicetype"]);
                $("#locationmodify").val(data["location"]);
                $("#producedmodify").val(data["produceddate"]);
            },
            error : function() {
                alert("初始化数据失败");
            }
        })
    }
    function deleteDevice(data){
        $("#deviceiddelete").val(data).hide();
    }
    function initform(){
        $.ajax({
            type: "GET",
            url: "device/getdeviceinfo",
            dataType: "json",
            success: function(data) {
                if ($.fn.dataTable.isDataTable('#devicedata')) {
                    table = $('#devicedata').DataTable();
                    table.destroy();
                }
                DeviceTableManaged.init(data);
            }
        })
    }

</script>
<!-- END PAGE LEVEL INIT-->