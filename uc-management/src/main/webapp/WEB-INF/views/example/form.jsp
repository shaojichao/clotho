<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>


<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/assets/global/plugins/select2/select2.css"/>
<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN PAGE HEADER-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            表单示例 <small>以Metronic中的文件（metronic/admin/templates/admin/*.html）为基础,抽出PAGE LEVEL STYLES/SCRIPTS，更改PAGE HEADER和PAGE CONTENT</small>
        </h3>
        <ul class="page-breadcrumb breadcrumb">
            <li>
                <i class="fa fa-home"></i>
                <a href="/">首页</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">页面示例</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <a href="#">表单示例</a>
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
    <div class="col-md-12">
        <div class="tabbable tabbable-custom boxless tabbable-reversed">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#tab_0" data-toggle="tab">
                        Form Actions </a>
                </li>
                <li>
                    <a href="#tab_1" data-toggle="tab">
                        2 Columns </a>
                </li>
                <li>
                    <a href="#tab_2" data-toggle="tab">
                        2 Columns Horizontal </a>
                </li>
                <li>
                    <a href="#tab_3" data-toggle="tab">
                        2 Columns View Only </a>
                </li>
                <li>
                    <a href="#tab_4" data-toggle="tab">
                        Row Seperated </a>
                </li>
                <li>
                    <a href="#tab_5" data-toggle="tab">
                        Bordered </a>
                </li>
                <li>
                    <a href="#tab_6" data-toggle="tab">
                        Row Stripped </a>
                </li>
                <li>
                    <a href="#tab_7" data-toggle="tab">
                        Label Stripped </a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="tab_0">
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Actions On Bottom
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
                        <div class="portlet-body form">
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
                                        <button type="submit" class="btn blue">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Actions On Top
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal">
                                <div class="form-actions top fluid ">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn green">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
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
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Actions On Top & Bottom
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal">
                                <div class="form-actions top fluid">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn green">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
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
                                        <button type="submit" class="btn green">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Actions Without Background Color
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal">
                                <div class="form-actions top nobg fluid">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn green">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
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
                                <div class="form-actions nobg fluid">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn green">Submit</button>
                                        <button type="button" class="btn default">Cancel</button>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="portlet box red">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-gift"></i>Left Aligned
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
                                <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
                                    <form action="#">
                                        <div class="form-actions top">
                                            <button type="submit" class="btn green">Submit</button>
                                            <button type="button" class="btn default">Cancel</button>
                                        </div>
                                        <div class="form-body">
                                            <div class="form-group">
                                                <label class="control-label">Text</label>
                                                <input type="text" class="form-control" placeholder="Enter text">
                                                <span class="help-block">
                                                    A block of help text. </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Email Address</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-envelope"></i>
                                                    </span>
                                                    <input type="email" class="form-control" placeholder="Email Address">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Password</label>
                                                <div class="input-group">
                                                    <input type="password" class="form-control" placeholder="Password">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-user"></i>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Left Icon</label>
                                                <div class="input-icon">
                                                    <i class="fa fa-bell-o"></i>
                                                    <input type="text" class="form-control" placeholder="Left icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Right Icon</label>
                                                <div class="input-icon right">
                                                    <i class="fa fa-microphone"></i>
                                                    <input type="text" class="form-control" placeholder="Right icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Input With Spinner</label>
                                                <input type="password" class="form-control spinner" placeholder="Password">
                                            </div>
                                            <div class="form-group last">
                                                <label class="control-label">Static Control</label>
                                                <p class="form-control-static">
                                                    email@example.com
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <button type="submit" class="btn green">Submit</button>
                                            <button type="button" class="btn default">Cancel</button>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="portlet box purple">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-gift"></i>Right Aligned
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
                                <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
                                    <form action="#">
                                        <div class="form-actions top right">
                                            <button type="submit" class="btn green">Submit</button>
                                            <button type="button" class="btn default">Cancel</button>
                                        </div>
                                        <div class="form-body">
                                            <div class="form-group">
                                                <label class="control-label">Text</label>
                                                <input type="text" class="form-control" placeholder="Enter text">
                                                <span class="help-block">
                                                    A block of help text. </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Email Address</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-envelope"></i>
                                                    </span>
                                                    <input type="email" class="form-control" placeholder="Email Address">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Password</label>
                                                <div class="input-group">
                                                    <input type="password" class="form-control" placeholder="Password">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-user"></i>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Left Icon</label>
                                                <div class="input-icon">
                                                    <i class="fa fa-bell-o"></i>
                                                    <input type="text" class="form-control" placeholder="Left icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Right Icon</label>
                                                <div class="input-icon right">
                                                    <i class="fa fa-microphone"></i>
                                                    <input type="text" class="form-control" placeholder="Right icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Input With Spinner</label>
                                                <input type="password" class="form-control spinner" placeholder="Password">
                                            </div>
                                            <div class="form-group last">
                                                <label class="control-label">Static Control</label>
                                                <p class="form-control-static">
                                                    email@example.com
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-actions right">
                                            <button type="submit" class="btn green">Submit</button>
                                            <button type="button" class="btn default">Cancel</button>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="portlet box yellow">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-gift"></i>Actions Buttons
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
                                <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
                                    <form action="#">
                                        <div class="form-actions top">
                                            <div class="btn-set pull-left">
                                                <button type="submit" class="btn green">Submit</button>
                                                <button type="button" class="btn blue">Other Action</button>
                                            </div>
                                            <div class="btn-set pull-right">
                                                <button type="button" class="btn default">Action 1</button>
                                                <button type="button" class="btn red">Action 2</button>
                                                <button type="button" class="btn yellow">Action 3</button>
                                            </div>
                                        </div>
                                        <div class="form-body">
                                            <div class="form-group">
                                                <label class="control-label">Text</label>
                                                <input type="text" class="form-control" placeholder="Enter text">
                                                <span class="help-block">
                                                    A block of help text. </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Email Address</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-envelope"></i>
                                                    </span>
                                                    <input type="email" class="form-control" placeholder="Email Address">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Password</label>
                                                <div class="input-group">
                                                    <input type="password" class="form-control" placeholder="Password">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-user"></i>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Left Icon</label>
                                                <div class="input-icon">
                                                    <i class="fa fa-bell-o"></i>
                                                    <input type="text" class="form-control" placeholder="Left icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Right Icon</label>
                                                <div class="input-icon right">
                                                    <i class="fa fa-microphone"></i>
                                                    <input type="text" class="form-control" placeholder="Right icon">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Input With Spinner</label>
                                                <input type="password" class="form-control spinner" placeholder="Password">
                                            </div>
                                            <div class="form-group last">
                                                <label class="control-label">Static Control</label>
                                                <p class="form-control-static">
                                                    email@example.com
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="btn-set pull-left">
                                                <button type="submit" class="btn green">Submit</button>
                                                <button type="button" class="btn blue">Other Action</button>
                                            </div>
                                            <div class="btn-set pull-right">
                                                <button type="button" class="btn default">Action 1</button>
                                                <button type="button" class="btn red">Action 2</button>
                                                <button type="button" class="btn yellow">Action 3</button>
                                            </div>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="tab_1">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="horizontal-form">
                                <div class="form-body">
                                    <h3 class="form-section">Person Info</h3>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">First Name</label>
                                                <input type="text" id="firstName" class="form-control" placeholder="Chee Kin">
                                                <span class="help-block">
                                                    This is inline help </span>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group has-error">
                                                <label class="control-label">Last Name</label>
                                                <input type="text" id="lastName" class="form-control" placeholder="Lim">
                                                <span class="help-block">
                                                    This field has error. </span>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Gender</label>
                                                <select class="form-control">
                                                    <option value="">Male</option>
                                                    <option value="">Female</option>
                                                </select>
                                                <span class="help-block">
                                                    Select your gender </span>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Date of Birth</label>
                                                <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Category</label>
                                                <select class="select2_category form-control" data-placeholder="Choose a Category" tabindex="1">
                                                    <option value="Category 1">Category 1</option>
                                                    <option value="Category 2">Category 2</option>
                                                    <option value="Category 3">Category 5</option>
                                                    <option value="Category 4">Category 4</option>
                                                </select>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Membership</label>
                                                <div class="radio-list">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> Option 1 </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"> Option 2 </label>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <h3 class="form-section">Address</h3>
                                    <div class="row">
                                        <div class="col-md-12 ">
                                            <div class="form-group">
                                                <label>Street</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>City</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>State</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Post Code</label>
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Country</label>
                                                <select class="form-control">
                                                </select>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                </div>
                                <div class="form-actions right">
                                    <button type="button" class="btn default">Cancel</button>
                                    <button type="submit" class="btn blue"><i class="fa fa-check"></i> Save</button>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane " id="tab_2">
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal">
                                <div class="form-body">
                                    <h3 class="form-section">Person Info</h3>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">First Name</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" placeholder="Chee Kin">
                                                    <span class="help-block">
                                                        This is inline help </span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group has-error">
                                                <label class="control-label col-md-3">Last Name</label>
                                                <div class="col-md-9">
                                                    <select name="foo" class="select2me form-control">
                                                        <option value="1">Abc</option>
                                                        <option value="1">Abc</option>
                                                        <option value="1">This is a really long value that breaks the fluid design for a select2</option>
                                                    </select>
                                                    <span class="help-block">
                                                        This field has error. </span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Gender</label>
                                                <div class="col-md-9">
                                                    <select class="form-control">
                                                        <option value="">Male</option>
                                                        <option value="">Female</option>
                                                    </select>
                                                    <span class="help-block">
                                                        Select your gender. </span>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Date of Birth</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Category</label>
                                                <div class="col-md-9">
                                                    <select class="select2_category form-control" data-placeholder="Choose a Category" tabindex="1">
                                                        <option value="Category 1">Category 1</option>
                                                        <option value="Category 2">Category 2</option>
                                                        <option value="Category 3">Category 5</option>
                                                        <option value="Category 4">Category 4</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Membership</label>
                                                <div class="col-md-9">
                                                    <div class="radio-list">
                                                        <label class="radio-inline">
                                                            <input type="radio" name="optionsRadios2" value="option1"/>
                                                            Free </label>
                                                        <label class="radio-inline">
                                                            <input type="radio" name="optionsRadios2" value="option2" checked/>
                                                            Professional </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <h3 class="form-section">Address</h3>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Address 1</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Address 2</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">City</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">State</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Post Code</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Country</label>
                                                <div class="col-md-9">
                                                    <select class="form-control">
                                                        <option>Country 1</option>
                                                        <option>Country 2</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green">Submit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane " id="tab_3">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form class="form-horizontal" role="form">
                                <div class="form-body">
                                    <h2 class="margin-bottom-20"> View User Info - Bob Nilson </h2>
                                    <h3 class="form-section">Person Info</h3>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">First Name:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        Bob
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Last Name:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        Nilson
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Gender:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        Male
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Date of Birth:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        20.01.1984
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Category:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        Category1
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Membership:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        Free
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <h3 class="form-section">Address</h3>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Address:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        #24 Sun Park Avenue, Rolton Str
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">City:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        New York
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">State:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        New York
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Post Code:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        457890
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Country:</label>
                                                <div class="col-md-9">
                                                    <p class="form-control-static">
                                                        USA
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green"><i class="fa fa-pencil"></i> Edit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="tab_4">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal form-row-seperated">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="small" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="medium" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Gender</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                                <option value="">Male</option>
                                                <option value="">Female</option>
                                            </select>
                                            <span class="help-block">
                                                Select your gender. </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Date of Birth</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Category</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_category">
                                                <option value="Category 1">Category 1</option>
                                                <option value="Category 2">Category 2</option>
                                                <option value="Category 3">Category 5</option>
                                                <option value="Category 4">Category 4</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Multi-Value Select</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_sample1" multiple>
                                                <optgroup label="NFC EAST">
                                                    <option>Dallas Cowboys</option>
                                                    <option>New York Giants</option>
                                                    <option>Philadelphia Eagles</option>
                                                    <option>Washington Redskins</option>
                                                </optgroup>
                                                <optgroup label="NFC NORTH">
                                                    <option>Chicago Bears</option>
                                                    <option>Detroit Lions</option>
                                                    <option>Green Bay Packers</option>
                                                    <option>Minnesota Vikings</option>
                                                </optgroup>
                                                <optgroup label="NFC SOUTH">
                                                    <option>Atlanta Falcons</option>
                                                    <option>Carolina Panthers</option>
                                                    <option>New Orleans Saints</option>
                                                    <option>Tampa Bay Buccaneers</option>
                                                </optgroup>
                                                <optgroup label="NFC WEST">
                                                    <option>Arizona Cardinals</option>
                                                    <option>St. Louis Rams</option>
                                                    <option>San Francisco 49ers</option>
                                                    <option>Seattle Seahawks</option>
                                                </optgroup>
                                                <optgroup label="AFC EAST">
                                                    <option>Buffalo Bills</option>
                                                    <option>Miami Dolphins</option>
                                                    <option>New England Patriots</option>
                                                    <option>New York Jets</option>
                                                </optgroup>
                                                <optgroup label="AFC NORTH">
                                                    <option>Baltimore Ravens</option>
                                                    <option>Cincinnati Bengals</option>
                                                    <option>Cleveland Browns</option>
                                                    <option>Pittsburgh Steelers</option>
                                                </optgroup>
                                                <optgroup label="AFC SOUTH">
                                                    <option>Houston Texans</option>
                                                    <option>Indianapolis Colts</option>
                                                    <option>Jacksonville Jaguars</option>
                                                    <option>Tennessee Titans</option>
                                                </optgroup>
                                                <optgroup label="AFC WEST">
                                                    <option>Denver Broncos</option>
                                                    <option>Kansas City Chiefs</option>
                                                    <option>Oakland Raiders</option>
                                                    <option>San Diego Chargers</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Loading Data</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample2">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Tags Support List</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample3" value="red, blue">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Membership</label>
                                        <div class="col-md-9">
                                            <div class="radio-list">
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option1"/>
                                                    Free </label>
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option2" checked/>
                                                    Professional </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Street</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">City</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">State</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Post Code</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group last">
                                        <label class="control-label col-md-3">Country</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green"><i class="fa fa-pencil"></i> Edit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="tab_5">
                    <div class="portlet box blue ">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal form-bordered">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="small" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="medium" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Gender</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                                <option value="">Male</option>
                                                <option value="">Female</option>
                                            </select>
                                            <span class="help-block">
                                                Select your gender. </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Date of Birth</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Category</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_category">
                                                <option value="Category 1">Category 1</option>
                                                <option value="Category 2">Category 2</option>
                                                <option value="Category 3">Category 5</option>
                                                <option value="Category 4">Category 4</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Multi-Value Select</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_sample1" multiple>
                                                <optgroup label="NFC EAST">
                                                    <option>Dallas Cowboys</option>
                                                    <option>New York Giants</option>
                                                    <option>Philadelphia Eagles</option>
                                                    <option>Washington Redskins</option>
                                                </optgroup>
                                                <optgroup label="NFC NORTH">
                                                    <option>Chicago Bears</option>
                                                    <option>Detroit Lions</option>
                                                    <option>Green Bay Packers</option>
                                                    <option>Minnesota Vikings</option>
                                                </optgroup>
                                                <optgroup label="NFC SOUTH">
                                                    <option>Atlanta Falcons</option>
                                                    <option>Carolina Panthers</option>
                                                    <option>New Orleans Saints</option>
                                                    <option>Tampa Bay Buccaneers</option>
                                                </optgroup>
                                                <optgroup label="NFC WEST">
                                                    <option>Arizona Cardinals</option>
                                                    <option>St. Louis Rams</option>
                                                    <option>San Francisco 49ers</option>
                                                    <option>Seattle Seahawks</option>
                                                </optgroup>
                                                <optgroup label="AFC EAST">
                                                    <option>Buffalo Bills</option>
                                                    <option>Miami Dolphins</option>
                                                    <option>New England Patriots</option>
                                                    <option>New York Jets</option>
                                                </optgroup>
                                                <optgroup label="AFC NORTH">
                                                    <option>Baltimore Ravens</option>
                                                    <option>Cincinnati Bengals</option>
                                                    <option>Cleveland Browns</option>
                                                    <option>Pittsburgh Steelers</option>
                                                </optgroup>
                                                <optgroup label="AFC SOUTH">
                                                    <option>Houston Texans</option>
                                                    <option>Indianapolis Colts</option>
                                                    <option>Jacksonville Jaguars</option>
                                                    <option>Tennessee Titans</option>
                                                </optgroup>
                                                <optgroup label="AFC WEST">
                                                    <option>Denver Broncos</option>
                                                    <option>Kansas City Chiefs</option>
                                                    <option>Oakland Raiders</option>
                                                    <option>San Diego Chargers</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Loading Data</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample2">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Tags Support List</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample3" value="red, blue">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Membership</label>
                                        <div class="col-md-9">
                                            <div class="radio-list">
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option1"/>
                                                    Free </label>
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option2" checked/>
                                                    Professional </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Street</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">City</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">State</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Post Code</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group last">
                                        <label class="control-label col-md-3">Country</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green"><i class="fa fa-check"></i> Submit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="tab_6">
                    <div class="portlet box blue ">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal form-bordered form-row-stripped">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="small" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="medium" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Gender</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                                <option value="">Male</option>
                                                <option value="">Female</option>
                                            </select>
                                            <span class="help-block">
                                                Select your gender. </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Date of Birth</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Category</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_category">
                                                <option value="Category 1">Category 1</option>
                                                <option value="Category 2">Category 2</option>
                                                <option value="Category 3">Category 5</option>
                                                <option value="Category 4">Category 4</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Multi-Value Select</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_sample1" multiple>
                                                <optgroup label="NFC EAST">
                                                    <option>Dallas Cowboys</option>
                                                    <option>New York Giants</option>
                                                    <option>Philadelphia Eagles</option>
                                                    <option>Washington Redskins</option>
                                                </optgroup>
                                                <optgroup label="NFC NORTH">
                                                    <option>Chicago Bears</option>
                                                    <option>Detroit Lions</option>
                                                    <option>Green Bay Packers</option>
                                                    <option>Minnesota Vikings</option>
                                                </optgroup>
                                                <optgroup label="NFC SOUTH">
                                                    <option>Atlanta Falcons</option>
                                                    <option>Carolina Panthers</option>
                                                    <option>New Orleans Saints</option>
                                                    <option>Tampa Bay Buccaneers</option>
                                                </optgroup>
                                                <optgroup label="NFC WEST">
                                                    <option>Arizona Cardinals</option>
                                                    <option>St. Louis Rams</option>
                                                    <option>San Francisco 49ers</option>
                                                    <option>Seattle Seahawks</option>
                                                </optgroup>
                                                <optgroup label="AFC EAST">
                                                    <option>Buffalo Bills</option>
                                                    <option>Miami Dolphins</option>
                                                    <option>New England Patriots</option>
                                                    <option>New York Jets</option>
                                                </optgroup>
                                                <optgroup label="AFC NORTH">
                                                    <option>Baltimore Ravens</option>
                                                    <option>Cincinnati Bengals</option>
                                                    <option>Cleveland Browns</option>
                                                    <option>Pittsburgh Steelers</option>
                                                </optgroup>
                                                <optgroup label="AFC SOUTH">
                                                    <option>Houston Texans</option>
                                                    <option>Indianapolis Colts</option>
                                                    <option>Jacksonville Jaguars</option>
                                                    <option>Tennessee Titans</option>
                                                </optgroup>
                                                <optgroup label="AFC WEST">
                                                    <option>Denver Broncos</option>
                                                    <option>Kansas City Chiefs</option>
                                                    <option>Oakland Raiders</option>
                                                    <option>San Diego Chargers</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Loading Data</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample2">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Tags Support List</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample3" value="red, blue">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Membership</label>
                                        <div class="col-md-9">
                                            <div class="radio-list">
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option1"/>
                                                    Free </label>
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option2" checked/>
                                                    Professional </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Street</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">City</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">State</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Post Code</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group last">
                                        <label class="control-label col-md-3">Country</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green"><i class="fa fa-check"></i> Submit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="tab_7">
                    <div class="portlet box blue ">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Form Sample
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
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" class="form-horizontal form-bordered form-label-stripped">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="small" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last Name</label>
                                        <div class="col-md-9">
                                            <input type="text" placeholder="medium" class="form-control"/>
                                            <span class="help-block">
                                                This is inline help </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Gender</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                                <option value="">Male</option>
                                                <option value="">Female</option>
                                            </select>
                                            <span class="help-block">
                                                Select your gender. </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Date of Birth</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" placeholder="dd/mm/yyyy">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Category</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_category">
                                                <option value="Category 1">Category 1</option>
                                                <option value="Category 2">Category 2</option>
                                                <option value="Category 3">Category 5</option>
                                                <option value="Category 4">Category 4</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Multi-Value Select</label>
                                        <div class="col-md-9">
                                            <select class="form-control select2_sample1" multiple>
                                                <optgroup label="NFC EAST">
                                                    <option>Dallas Cowboys</option>
                                                    <option>New York Giants</option>
                                                    <option>Philadelphia Eagles</option>
                                                    <option>Washington Redskins</option>
                                                </optgroup>
                                                <optgroup label="NFC NORTH">
                                                    <option>Chicago Bears</option>
                                                    <option>Detroit Lions</option>
                                                    <option>Green Bay Packers</option>
                                                    <option>Minnesota Vikings</option>
                                                </optgroup>
                                                <optgroup label="NFC SOUTH">
                                                    <option>Atlanta Falcons</option>
                                                    <option>Carolina Panthers</option>
                                                    <option>New Orleans Saints</option>
                                                    <option>Tampa Bay Buccaneers</option>
                                                </optgroup>
                                                <optgroup label="NFC WEST">
                                                    <option>Arizona Cardinals</option>
                                                    <option>St. Louis Rams</option>
                                                    <option>San Francisco 49ers</option>
                                                    <option>Seattle Seahawks</option>
                                                </optgroup>
                                                <optgroup label="AFC EAST">
                                                    <option>Buffalo Bills</option>
                                                    <option>Miami Dolphins</option>
                                                    <option>New England Patriots</option>
                                                    <option>New York Jets</option>
                                                </optgroup>
                                                <optgroup label="AFC NORTH">
                                                    <option>Baltimore Ravens</option>
                                                    <option>Cincinnati Bengals</option>
                                                    <option>Cleveland Browns</option>
                                                    <option>Pittsburgh Steelers</option>
                                                </optgroup>
                                                <optgroup label="AFC SOUTH">
                                                    <option>Houston Texans</option>
                                                    <option>Indianapolis Colts</option>
                                                    <option>Jacksonville Jaguars</option>
                                                    <option>Tennessee Titans</option>
                                                </optgroup>
                                                <optgroup label="AFC WEST">
                                                    <option>Denver Broncos</option>
                                                    <option>Kansas City Chiefs</option>
                                                    <option>Oakland Raiders</option>
                                                    <option>San Diego Chargers</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Loading Data</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample2">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Tags Support List</label>
                                        <div class="col-md-9">
                                            <input type="hidden" class="form-control select2_sample3" value="red, blue">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Membership</label>
                                        <div class="col-md-9">
                                            <div class="radio-list">
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option1"/>
                                                    Free </label>
                                                <label>
                                                    <input type="radio" name="optionsRadios2" value="option2" checked/>
                                                    Professional </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Street</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">City</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">State</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Post Code</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group last">
                                        <label class="control-label col-md-3">Country</label>
                                        <div class="col-md-9">
                                            <select class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="col-md-offset-3 col-md-9">
                                                <button type="submit" class="btn green"><i class="fa fa-check"></i> Submit</button>
                                                <button type="button" class="btn default">Cancel</button>
                                            </div>
                                        </div>
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
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx}/resources/assets/global/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/resources/admin/pages/scripts/form-samples.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL INIT-->
<script>
    jQuery(document).ready(function() {
        TableManaged.init();
    });
</script>
<!-- END PAGE LEVEL INIT-->