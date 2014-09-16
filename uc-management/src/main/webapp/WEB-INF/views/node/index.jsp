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
            页面名称 <small>页面描述信息</small>
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
            <!-- 选择时间范围
            <li class="pull-right">
                <div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
                    <i class="icon-calendar"></i>
                    <span></span>
                    <i class="fa fa-angle-down"></i>
                </div>
            </li>
            -->
        </ul>
        <!-- END PAGE TITLE & BREADCRUMB-->
    </div>
</div>
<!-- END PAGE HEADER-->

<div class="clearfix">
</div>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>Managed Table
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="#portlet-config" data-toggle="modal" class="config">
                    </a>
                    <a href="javascript:;" class="reload">
                    </a>
                    <a href="javascript:;" class="remove">
                    </a>
                </div>
            </div>
            <div class="portlet-body">
                <div class="table-toolbar">
                    <div class="btn-group">
                        <button id="sample_editable_1_new" class="btn green">
                            Add New <i class="fa fa-plus"></i>
                        </button>
                    </div>
                    <div class="btn-group pull-right">
                        <button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="#">
                                    Print </a>
                            </li>
                            <li>
                                <a href="#">
                                    Save as PDF </a>
                            </li>
                            <li>
                                <a href="#">
                                    Export to Excel </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover" id="sample_1">
                    <thead>
                        <tr>
                            <th class="table-checkbox">
                                <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/>
                            </th>
                            <th>
                                Username
                            </th>
                            <th>
                                Email
                            </th>
                            <th>
                                Points
                            </th>
                            <th>
                                Joined
                            </th>
                            <th>
                                Status
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                shuxer
                            </td>
                            <td>
                                <a href="mailto:shuxer@gmail.com">
                                    shuxer@gmail.com </a>
                            </td>
                            <td>
                                120
                            </td>
                            <td class="center">
                                12 Jan 2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                looper
                            </td>
                            <td>
                                <a href="mailto:looper90@gmail.com">
                                    looper90@gmail.com </a>
                            </td>
                            <td>
                                120
                            </td>
                            <td class="center">
                                12.12.2011
                            </td>
                            <td>
                                <span class="label label-sm label-warning">
                                    Suspended </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@yahoo.com">
                                    userwow@yahoo.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                user1wow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                restest
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                foopl
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                weep
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                coop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                pppol
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                goop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                weep
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                15.11.2011
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                toopl
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                16.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                9.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                tes21t
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                14.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                fop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                13.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-warning">
                                    Suspended </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                kop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                17.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                vopl
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.11.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                wap
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                12.12.2012
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                19.12.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                toop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                17.12.2010
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                weep
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                20
                            </td>
                            <td class="center">
                                15.11.2011
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-sm-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box yellow">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-user"></i>Table
                </div>
                <div class="actions">
                    <a href="#" class="btn btn-default btn-sm">
                        <i class="fa fa-pencil"></i> Add </a>
                    <div class="btn-group">
                        <a class="btn btn-default btn-sm" href="#" data-toggle="dropdown">
                            <i class="fa fa-cogs"></i> Tools <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="#">
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
                <table class="table table-striped table-bordered table-hover" id="sample_2">
                    <thead>
                        <tr>
                            <th class="table-checkbox">
                                <input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes"/>
                            </th>
                            <th>
                                Username
                            </th>
                            <th>
                                Email
                            </th>
                            <th>
                                Status
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                shuxer
                            </td>
                            <td>
                                <a href="mailto:shuxer@gmail.com">
                                    shuxer@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                looper
                            </td>
                            <td>
                                <a href="mailto:looper90@gmail.com">
                                    looper90@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-warning">
                                    Suspended </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@yahoo.com">
                                    userwow@yahoo.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                user1wow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                restest
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                foopl
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                weep
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                coop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                pppol
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
    <div class="col-md-6 col-sm-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet box purple">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>Table
                </div>
                <div class="actions">
                    <a href="#" class="btn btn-default btn-sm">
                        <i class="fa fa-plus"></i> Add </a>
                    <a href="#" class="btn btn-default btn-sm">
                        <i class="fa fa-print"></i> Print </a>
                </div>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover" id="sample_3">
                    <thead>
                        <tr>
                            <th class="table-checkbox">
                                <input type="checkbox" class="group-checkable" data-set="#sample_3 .checkboxes"/>
                            </th>
                            <th>
                                Username
                            </th>
                            <th>
                                Email
                            </th>
                            <th>
                                Status
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                shuxer
                            </td>
                            <td>
                                <a href="mailto:shuxer@gmail.com">
                                    shuxer@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                looper
                            </td>
                            <td>
                                <a href="mailto:looper90@gmail.com">
                                    looper90@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-warning">
                                    Suspended </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@yahoo.com">
                                    userwow@yahoo.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                user1wow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                restest
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                foopl
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                weep
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                coop
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                pppol
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    good@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                userwow
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    userwow@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-default">
                                    Blocked </span>
                            </td>
                        </tr>
                        <tr class="odd gradeX">
                            <td>
                                <input type="checkbox" class="checkboxes" value="1"/>
                            </td>
                            <td>
                                test
                            </td>
                            <td>
                                <a href="mailto:userwow@gmail.com">
                                    test@gmail.com </a>
                            </td>
                            <td>
                                <span class="label label-sm label-success">
                                    Approved </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx}/resources/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/admin/pages/scripts/table-managed.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        TableManaged.init();
    });
</script>