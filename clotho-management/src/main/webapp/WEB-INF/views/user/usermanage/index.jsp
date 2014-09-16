<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>


<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN PAGE HEADER-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            用户信息管理 <small>提供用户信息的增删改查功能</small>
        </h3>
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="/">首页</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">用户管理</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">用户信息管理</a>
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
        <i class="fa fa-user"></i>用户信息
    </div>
    <div class="actions">
            <a class="btn default" data-toggle="modal" href="#add">
                <i class="fa fa-pencil"></i> 新增 </a>
            <a class="btn default" data-toggle="modal" href="#query">
                <i class="fa fa-search"></i> 查询 </a>
    </div>
</div>
<div class="portlet-body">
<table class="table table-striped table-bordered table-hover" id="userdata">
<thead>
<tr>
    <th>
        用户id
    </th>
    <th>
        账号
    </th>
    <th>
        账号类型
    </th>
    <th>
        姓名
    </th>
    <th>
        昵称
    </th>
    <th>
        年龄
    </th>
    <th>
        性别
    </th>
    <th>
        手机号
    </th>
    <th>
        邮箱
    </th>
    <th>
        职业
    </th>
    <th>
        地址
    </th>
    <th>
        会员类型
    </th>
    <th>
        区域码
    </th>
    <th>
        头像
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
                    <h4 class="modal-title">用户信息添加</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="useradd" action="#" class="form-horizontal">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">用户账号</label>
                                <div class="col-md-4">
                                    <input id="useridadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                只支持手机或者邮箱注册</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">账号类型</label>
                                <div class="col-md-4">
                                    <input id="useridtypeadd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-邮箱 2-手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>
                                <div class="col-md-4">
                                    <input id="nameadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户姓名</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">昵称</label>
                                <div class="col-md-4">
                                    <input id="nicknameadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户昵称</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">年龄</label>
                                <div class="col-md-4">
                                    <input id="ageadd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户年龄</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>
                                <div class="col-md-4">
                                    <input id="genderadd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-男 0-女</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">手机号</label>
                                <div class="col-md-4">
                                    <input id="mobileadd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户手机号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">邮箱</label>
                                <div class="col-md-4">
                                    <input id="mailadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户邮箱</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">职业</label>
                                <div class="col-md-4">
                                    <input id="occupationadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户职业</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">地址</label>
                                <div class="col-md-4">
                                    <input id="addressadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户地址</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">会员类型</label>
                                <div class="col-md-4">
                                    <input id="vipleveladd" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                会员等级</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">区域码</label>
                                <div class="col-md-4">
                                    <input id="locationadd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">头像</label>
                                <div class="col-md-4">
                                    <input id="headposteradd" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户头像URL</span>
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
                    <h4 class="modal-title">用户信息查询</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="userquery" action="#" class="form-horizontal">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">用户账号</label>
                                <div class="col-md-4">
                                    <input id="useridquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户账号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">账号类型</label>
                                <div class="col-md-4">
                                    <input id="useridtypequery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-邮箱 2-手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>
                                <div class="col-md-4">
                                    <input id="namequery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户姓名</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">昵称</label>
                                <div class="col-md-4">
                                    <input id="nicknamequery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户昵称</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">年龄</label>
                                <div class="col-md-4">
                                    <input id="agequery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户年龄</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>
                                <div class="col-md-4">
                                    <input id="genderquery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-男 0-女</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">手机号</label>
                                <div class="col-md-4">
                                    <input id="mobilequery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户手机号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">邮箱</label>
                                <div class="col-md-4">
                                    <input id="mailquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户邮箱</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">职业</label>
                                <div class="col-md-4">
                                    <input id="occupationquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户职业</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">会员类型</label>
                                <div class="col-md-4">
                                    <input id="viplevelquery" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                会员等级</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">区域码</label>
                                <div class="col-md-4">
                                    <input id="locationquery" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
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
                    <h4 class="modal-title">用户信息修改</h4>
                </div>
                <div class="modal-body form">
                    <!-- BEGIN FORM-->
                    <form id="usermodify" action="#" class="form-horizontal">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">用户id</label>
                                <div class="col-md-4">
                                    <input id="idmodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户唯一标识</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">用户账号</label>
                                <div class="col-md-4">
                                    <input id="useridmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                手机或者邮箱账号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">账号类型</label>
                                <div class="col-md-4">
                                    <input id="useridtypemodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-邮箱 2-手机</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>
                                <div class="col-md-4">
                                    <input id="namemodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户姓名</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">昵称</label>
                                <div class="col-md-4">
                                    <input id="nicknamemodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户昵称</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">年龄</label>
                                <div class="col-md-4">
                                    <input id="agemodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户年龄</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>
                                <div class="col-md-4">
                                    <input id="gendermodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                1-男 0-女</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">手机号</label>
                                <div class="col-md-4">
                                    <input id="mobilemodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                用户手机号</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">邮箱</label>
                                <div class="col-md-4">
                                    <input id="mailmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户邮箱</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">职业</label>
                                <div class="col-md-4">
                                    <input id="occupationmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户职业</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">地址</label>
                                <div class="col-md-4">
                                    <input id="addressmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户地址</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">会员类型</label>
                                <div class="col-md-4">
                                    <input id="viplevelmodify" type="text" class="form-control" placeholder="请输入数字">
                                            <span class="help-block">
                                                会员等级</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">区域码</label>
                                <div class="col-md-4">
                                    <input id="locationmodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                统一的两位区域标识码</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">头像</label>
                                <div class="col-md-4">
                                    <input id="headpostermodify" type="text" class="form-control" placeholder="请输入文本">
                                            <span class="help-block">
                                                用户头像URL</span>
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
                <h4 class="modal-title">用户信息删除</h4>
            </div>
            <div class="modal-body form">
                <!-- BEGIN FORM-->
                <form id="userdelete" action="#" class="form-horizontal">
                    <div class="form-body">

                        <div class="form-group">
                            <div class="col-md-4">
                                <input id="iddelete" type="text" class="form-control" placeholder="请输入文本">
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
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/page/user/usermanage/usermanage.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL INIT-->
<script>
    jQuery(document).ready(function() {
        initform();
        $("#userdelete").submit(function() {
            $.ajax({
                type : 'POST',
                url : 'user/deleteuserinfo',
                data: {id:$("#iddelete").val()},
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
        $("#usermodify").submit(function() {
            $.ajax({
                type : 'GET',
                url : 'user/modifyuserinfo',
                data: {userid:$("#useridmodify").val(),usersn:$("#usersnmodify").val(),usertype:$("#usertypemodify").val(),location:$("#locationmodify").val(),produceddate:$("#producedmodify").val()},
                dataType : 'json',
                success : function(data) {
                    $("#modify").modal('hide');
                    if ($.fn.dataTable.isDataTable('#userdata')) {
                        table = $('#userdata').DataTable();
                        table.destroy();
                    }
                    UserTableManaged.init(data);
                },
                error : function() {
                    alert("修改失败");
                }
            });
        });
        $("#userquery").submit(function() {
            $.ajax({
                type : 'GET',
                url : 'user/queryuserinfo',
                data: {usersn:$("#usersnquery").val(),usertype:$("#usertypequery").val(),location:$("#locationquery").val(),produceddate:$("#producedquery").val()},
                dataType : 'json',
                success : function(data) {
                    $("#query").modal('hide');
                    if ($.fn.dataTable.isDataTable('#userdata')) {
                        table = $('#userdata').DataTable();
                        table.destroy();
                    }
                    UserTableManaged.init(data);
                },
                error : function() {
                    alert("查询失败");
                }
            });
        });
        $("#useradd").submit(function() {
                $.ajax({
                    type : 'POST',
                    url : 'user/adduserinfo',
                    data: {usersn:$("#usersnadd").val(),usertype:$("#usertypeadd").val(),location:$("#locationadd").val(),produceddate:$("#producedadd").val()},
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
    function updateUser(data){
        $("#idmodify").val(data).attr("readonly","readonly");
        $.ajax({
            type: "GET",
            data:{userid:data},
            url: "user/getuserinfobyid",
            dataType: "json",
            success: function(data) {
                $("#usersnmodify").val(data["usersn"]);
                $("#usertypemodify").val(data["usertype"]);
                $("#locationmodify").val(data["location"]);
                $("#producedmodify").val(data["produceddate"]);
            },
            error : function() {
                alert("初始化数据失败");
            }
        })
    }
    function deleteUser(data){
        $("#iddelete").val(data).hide();
    }
    function initform(){
        $.ajax({
            type: "GET",
            url: "user/getuserinfo",
            dataType: "json",
            success: function(data) {
                if ($.fn.dataTable.isDataTable('#userdata')) {
                    table = $('#userdata').DataTable();
                    table.destroy();
                }
                UserTableManaged.init(data);
            }
        })
    }

</script>
<!-- END PAGE LEVEL INIT-->