<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>


<!-- BEGIN PAGE LEVEL STYLES -->

<!-- END PAGE LEVEL STYLES -->


<!-- BEGIN PAGE HEADER-->
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">
            对话框示例 <small>以Metronic中的文件（metronic/admin/templates/admin/*.html）为基础,抽出PAGE LEVEL STYLES/SCRIPTS，更改PAGE HEADER和PAGE CONTENT</small>
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
                <a href="#">对话框示例</a>
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
        <div class="note note-success">
            <h4 class="block">Bootstrap Native Modals</h4>
            <p>
                Modals are streamlined, but flexible, dialog prompts with the minimum required functionality and smart defaults. If you need more control over the Bootstrap Modals please check out <a href="ui_extended_modals.html">
                    extended modals </a>
                .
            </p>
        </div>
        <!-- BEGIN PORTLET-->
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-gift"></i>Static Example
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="javascript:;" class="reload">
                    </a>
                </div>
            </div>
            <div class="portlet-body">
                <table class="table table-hover table-striped table-bordered">
                    <tr>
                        <td>
                            Basic Example
                        </td>
                        <td>
                            <a class="btn default" data-toggle="modal" href="#basic">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Large Width Example
                        </td>
                        <td>
                            <a class="btn default" data-toggle="modal" href="#large">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Small Width Example
                        </td>
                        <td>
                            <a class="btn default" data-toggle="modal" href="#small">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Full Width Example
                        </td>
                        <td>
                            <a class="btn default" data-toggle="modal" href="#full">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Responsive
                        </td>
                        <td>
                            <a class="btn default" data-toggle="modal" href="#responsive">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            AJAX content loading
                        </td>
                        <td>
                            <a class=" btn default" href="ui_modals_ajax_sample.html" data-target="#ajax" data-toggle="modal">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Stackable
                        </td>
                        <td>
                            <a class=" btn default" data-target="#stack1" data-toggle="modal">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Static Background
                        </td>
                        <td>
                            <a class=" btn default" data-toggle="modal" href="#static">
                                View Demo </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Long Modals
                        </td>
                        <td>
                            <a class=" btn default" data-toggle="modal" href="#long">
                                View Demo </a>
                        </td>
                    </tr>
                </table>
                <div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Modal Title</h4>
                            </div>
                            <div class="modal-body">
                                Modal body goes here
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn blue">Save changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
                <div class="modal fade" id="full" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-full">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Modal Title</h4>
                            </div>
                            <div class="modal-body">
                                Modal body goes here
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn blue">Save changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
                <div class="modal fade bs-modal-lg" id="large" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Modal Title</h4>
                            </div>
                            <div class="modal-body">
                                Modal body goes here
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn blue">Save changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
                <div class="modal fade bs-modal-sm" id="small" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Modal Title</h4>
                            </div>
                            <div class="modal-body">
                                Modal body goes here
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn blue">Save changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
                <div id="responsive" class="modal fade" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Responsive & Scrollable</h4>
                            </div>
                            <div class="modal-body">
                                <div class="scroller" style="height:300px" data-always-visible="1" data-rail-visible1="1">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4>Some Input</h4>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                        </div>
                                        <div class="col-md-6">
                                            <h4>Some More Input</h4>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                            <p>
                                                <input type="text" class="col-md-12 form-control">
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">Close</button>
                                <button type="button" class="btn green">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="ajax" role="basic" aria-hidden="true">
                    <div class="page-loading page-loading-boxed">
                        <img src="../../assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
                        <span>
                            &nbsp;&nbsp;Loading... </span>
                    </div>
                    <div class="modal-dialog">
                        <div class="modal-content">
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
                <div id="stack1" class="modal fade" tabindex="-1" data-width="400">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Stack One</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h4>Some Input</h4>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                    </div>
                                </div>
                                <a class="btn green" data-toggle="modal" href="#stack2">
                                    Launch modal </a>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn">Close</button>
                                <button type="button" class="btn red">Ok</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="stack2" class="modal fade" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Stack Two</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h4>Some Input</h4>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                        <p>
                                            <input type="text" class="col-md-12 form-control">
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn">Close</button>
                                <button type="button" class="btn yellow">Ok</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="static" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Confirmation</h4>
                            </div>
                            <div class="modal-body">
                                <p>
                                    Would you like to continue with some arbitrary task?
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn default">Cancel</button>
                                <button type="button" data-dismiss="modal" class="btn green">Continue Task</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="long" class="modal fade modal-scroll" tabindex="-1" data-replace="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">A Fairly Long Modal</h4>
                            </div>
                            <div class="modal-body">
                                <img style="height: 800px" alt="" src="http://i.imgur.com/KwPYo.jpg">
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>
<!-- END PAGE CONTENT-->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL INIT-->
<script>
    jQuery(document).ready(function() {
    });
</script>
<!-- END PAGE LEVEL INIT-->