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
                <a href="#">用户信息修改</a>
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
        <i class="fa fa-user"></i>Table
    </div>
    <div class="actions">
        <a onclick="getuser();" id="useredit" class="btn btn-default btn-sm">
            <i class="fa fa-pencil"></i> Add </a>
        <div class="btn-group">
            <a class="btn btn-default btn-sm" href="#" data-toggle="dropdown">
                <i class="fa fa-cogs"></i> Tools <i class="fa fa-angle-down"></i>
            </a>
            <ul class="dropdown-menu pull-right">
                <li>
                    <a class="btn default" data-toggle="modal" href="#edit">
                        <i class="fa fa-pencil"></i> Edit </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-trash-o"></i> Delete </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-ban"></i> Ban </a>
                </li>
                <li class="divider">
                </li>
                <li>
                    <a href="#">
                        <i class="i"></i> Make admin </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="portlet-body">
<table class="table table-striped table-bordered table-hover" id="userdata">
<thead>
<tr>
    <%--<th class="table-checkbox">--%>
        <%--<input type="checkbox" class="group-checkable" data-set="#userdata .checkboxes"/>--%>
    <%--</th>--%>
    <th>
        id
    </th>
    <th>
        userid
    </th>
    <th>
        password
    </th>
</tr>
</thead>
<tbody>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--shuxer--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:shuxer@gmail.com">--%>
            <%--shuxer@gmail.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-success">--%>
                                    <%--Approved </span>--%>
    <%--</td>--%>
<%--</tr>--%>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--looper--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:looper90@gmail.com">--%>
            <%--looper90@gmail.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-warning">--%>
                                    <%--Suspended </span>--%>
    <%--</td>--%>
<%--</tr>--%>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--userwow--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:userwow@yahoo.com">--%>
            <%--userwow@yahoo.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-success">--%>
                                    <%--Approved </span>--%>
    <%--</td>--%>
<%--</tr>--%>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--user1wow--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:userwow@gmail.com">--%>
            <%--userwow@gmail.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-default">--%>
                                    <%--Blocked </span>--%>
    <%--</td>--%>
<%--</tr>--%>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--restest--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:userwow@gmail.com">--%>
            <%--test@gmail.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-success">--%>
                                    <%--Approved </span>--%>
    <%--</td>--%>
<%--</tr>--%>
<%--<tr class="odd gradeX">--%>
    <%--<td>--%>
        <%--<input type="checkbox" class="checkboxes" value="1"/>--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--foopl--%>
    <%--</td>--%>
    <%--<td>--%>
        <%--<a href="mailto:userwow@gmail.com">--%>
            <%--good@gmail.com </a>--%>
    <%--</td>--%>
    <%--<td>--%>
                                <%--<span class="label label-sm label-success">--%>
                                    <%--Approved </span>--%>
    <%--</td>--%>
<%--</tr>--%>
</tbody>
</table>

<div id="edit" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">设备信息添加</h4>
            </div>
            <div class="modal-body form">
                <!-- BEGIN FORM-->
                <form action="#" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Text</label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" placeholder="Enter text">
                                            <span class="help-block">
                                                A block of help text. </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Email Address</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-envelope"></i>
                                                </span>
                                    <input type="email" class="form-control" placeholder="Email Address">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Password</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="password" class="form-control" placeholder="Password">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-user"></i>
                                                </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Left Icon</label>
                            <div class="col-md-4">
                                <div class="input-icon">
                                    <i class="fa fa-bell-o"></i>
                                    <input type="text" class="form-control" placeholder="Left icon">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Right Icon</label>
                            <div class="col-md-4">
                                <div class="input-icon right">
                                    <i class="fa fa-microphone"></i>
                                    <input type="text" class="form-control" placeholder="Right icon">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Input With Spinner</label>
                            <div class="col-md-4">
                                <input type="password" class="form-control spinner" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group last">
                            <label class="col-md-3 control-label">Static Control</label>
                            <div class="col-md-4">
                                <p class="form-control-static">
                                    email@example.com
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn blue">保存</button>
                            <button type="button" class="btn default">取消</button>
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
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/page/user/usermanage/usermanage.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL INIT-->
<script>
    jQuery(document).ready(function() {
        alert(1312);
        $.ajax({
            type: "GET",
            url: "user/getuserinfo",
            dataType: "json",
            success: function(data) {
                if ($.fn.dataTable.isDataTable('#userdata')) {
                    alert("isdatatable");
                    table = $('#userdata').DataTable();
                    table.destroy();
                }
                UserTableManaged.init(data);
            }
        })
    })
    function getuser(){
            $.ajax({
                type: "GET",
                url: "user/getuserinfo",
                dataType: "json",
                success: function(data){
                    alert(JSON.stringify(data));
                    if ( $.fn.dataTable.isDataTable( '#userdata' ) ) {
                        alert("isdatatable");
                        table = $('#userdata').DataTable();
                        table.destroy();
                    }
                    $('#userdata').dataTable( {
                        "data": data,
                        "columns": [
                            { "data": "id", "sWidth": "33%"},
                            { "data": "userid", "sWidth": "33%"},
                            { "data": "password", "sWidth": "33%"}
                        ]
                    })
//                    $('#userdata').dataTable( {
//                        "lengthMenu": [
//                            [5, 15, 20, -1],
//                            [5, 15, 20, "All"] // change per page values here
//                        ],
//                        // set the initial value
//                        "pageLength": 5,
//                        "language": {
//                            "lengthMenu": "每页 _MENU_ 条记录",
//                            "paging": {
//                                "previous": "前一页",
//                                "next": "后一页"
//                            },
//                            "search":"在当前表格中查询：",
//                            info:"共 _TOTAL_ 条记录，当前显示 _START_ 到 _END_ "
//                        },
//                        "columnDefs": [{  // set default column settings
//                            'orderable': false,
//                            'targets': [0]
//                        }, {
//                            "searchable": false,
//                            "targets": [0]
//                        }],
//                        "order": [
//                            [1, "asc"]
//                        ], // set first column as a default sort by asc
//                        "ajax": data,
//                        "columns": [
//                            { "data": "id" },
//                            { "data": "userid" },
//                            { "data": "password" }
//                        ]
//                    } );
//                    $('#resText').empty();   //清空resText里面的所有内容
//                    var html = '';
//                    $.each(data, function(commentIndex, comment){
//                        html += '<div class="comment"><h6>' + comment['username']
//                                + ':</h6><p class="para"' + comment['content']
//                                + '</p></div>';
//                    });
//                    $('#resText').html(html);
                }
            })
    }

</script>
<!-- END PAGE LEVEL INIT-->