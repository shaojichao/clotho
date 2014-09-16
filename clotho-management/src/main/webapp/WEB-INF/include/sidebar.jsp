<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>

<div class="page-sidebar-wrapper">
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
            <%--<c:import url="/WEB-INF/include/sidebar_toggler.jsp"/>--%>
            <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
            <%--<c:import url="/WEB-INF/include/search_box.jsp"/>--%>

            <li class="start active ">
                <a href="/">
                    <i class="icon-home"></i>
                    <span class="title">首页</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-basket"></i>
                    <span class="title">页面示例</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;" onclick="showContent('example/table', this)">
                            <i class="icon-home"></i>
                            表格示例</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="showContent('example/form', this)">
                            <i class="icon-basket"></i>
                            表单示例</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="showContent('example/modal', this)">
                            <i class="icon-tag"></i>
                            对话框示例</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="showContent('example/modal_ext', this)">
                            <i class="icon-tag"></i>
                            扩展对话框示例</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-rocket"></i>
                    <span class="title">设备管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;" onclick="showContent('device/devicemanage', this)">
                            设备信息管理</a>
                    </li>
                </ul>
            </li>
            <!-- BEGIN FRONTEND THEME LINKS -->
            <li>
                <a href="javascript:;">
                    <i class="icon-star"></i>
                    <span class="title">
                        系统管理 </span>
                    <span class="arrow">
                    </span>
                </a>
                <ul class="sub-menu">
                    <li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="Complete eCommerce Frontend Theme For Metronic Admin">
                        <a href="javascript:;" onclick="showContent('node', this)">
                            <span class="title">
                                权限管理 </span>
                        </a>
                    </li>
                    <li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="Complete Corporate Frontend Theme For Metronic Admin">
                        <a href="javascript:;" onclick="showContent('node', this)"
                           <span class="title">
                                用户管理 </span>
                        </a>
                    </li>
                    <li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="Complete One Page Parallax Frontend Theme For Metronic Admin">
                        <a href="javascript:;" onclick="showContent('node', this)">
                            <span class="title">
                                其他管理 </span>
                        </a>
                    </li>
                </ul>
            </li>
            <!-- END FRONTEND THEME LINKS -->
            <li>
                <a href="javascript:;">
                    <i class="icon-diamond"></i>
                    <span class="title">用户管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;" onclick="showContent('user/usermanage')">
                            用户信息管理</a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="showContent('user/groupmanage')">
                            分组信息管理</a>
                    </li>
                </ul>
            </li>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-puzzle"></i>--%>
                    <%--<span class="title">管理菜单11</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="components_pickers.html">--%>
                            <%--管理11-1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_dropdowns.html">--%>
                            <%--管理11-2</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_form_tools.html">--%>
                            <%--管理11-3</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_editors.html">--%>
                            <%--管理11-4</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_ion_sliders.html">--%>
                            <%--管理11-5</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_noui_sliders.html">--%>
                            <%--管理11-6</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_jqueryui_sliders.html">--%>
                            <%--管理11-7</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="components_knob_dials.html">--%>
                            <%--管理11-8</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-settings"></i>--%>
                    <%--<span class="title">管理菜单12</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="form_controls.html">--%>
                            <%--管理12-1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="form_layouts.html">--%>
                            <%--管理12-2</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-logout"></i>--%>
                    <%--<span class="title">管理菜单13</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="quick_sidebar_push_page.html">管理13-1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="quick_sidebar_push_content.html">管理13-2</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="quick_sidebar_over_content.html">管理13-3</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-envelope-open"></i>--%>
                    <%--<span class="title">管理菜单14</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="email_newsletter.html">管理14-1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="email_system.html">管理14-2</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-docs"></i>--%>
                    <%--<span class="title">管理菜单15</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="page_portfolio.html">--%>
                            <%--<i class="icon-feed"></i>--%>
                            <%--<span class="badge badge-warning badge-roundless">new</span>管理15-1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_timeline.html">--%>
                            <%--<i class="icon-clock"></i>--%>
                            <%--<span class="badge badge-info">4</span>管理15-2</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_coming_soon.html">--%>
                            <%--<i class="icon-flag"></i>--%>
                            <%--管理15-3</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_blog.html">--%>
                            <%--<i class="icon-speech"></i>--%>
                            <%--管理15-4</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_blog_item.html">--%>
                            <%--<i class="icon-link"></i>--%>
                            <%--管理15-5</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_news.html">--%>
                            <%--<i class="icon-eye"></i>--%>
                            <%--<span class="badge badge-success">9</span>管理15-6</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_news_item.html">--%>
                            <%--<i class="icon-bell"></i>--%>
                            <%--管理15-7</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_about.html">--%>
                            <%--<i class="icon-users"></i>--%>
                            <%--管理15-8</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_contact.html">--%>
                            <%--<i class="icon-envelope-open"></i>--%>
                            <%--联系我们</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="page_calendar.html">--%>
                            <%--<i class="icon-calendar"></i>--%>
                            <%--<span class="badge badge-danger">14</span>日历</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-present"></i>--%>
                    <%--<span class="title">其他</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="extra_profile.html">--%>
                            <%--User Profile</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_lock.html">--%>
                            <%--Lock Screen</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_faq.html">--%>
                            <%--FAQ</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="inbox.html">--%>
                            <%--<span class="badge badge-danger">4</span>Inbox</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_search.html">--%>
                            <%--Search Results</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_invoice.html">--%>
                            <%--Invoice</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_pricing_table.html">--%>
                            <%--Pricing Tables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_404_option1.html">--%>
                            <%--404 Page Option 1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_404_option2.html">--%>
                            <%--404 Page Option 2</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_404_option3.html">--%>
                            <%--404 Page Option 3</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_500_option1.html">--%>
                            <%--500 Page Option 1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="extra_500_option2.html">--%>
                            <%--500 Page Option 2</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-folder"></i>--%>
                    <%--<span class="title">多级菜单</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="javascript:;">--%>
                            <%--<i class="icon-settings"></i> Item 1 <span class="arrow"></span>--%>
                        <%--</a>--%>
                        <%--<ul class="sub-menu">--%>
                            <%--<li>--%>
                                <%--<a href="javascript:;">--%>
                                    <%--<i class="icon-user"></i>--%>
                                    <%--Sample Link 1 <span class="arrow"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="sub-menu">--%>
                                    <%--<li>--%>
                                        <%--<a href="#"><i class="icon-power"></i> Sample Link 1</a>--%>
                                    <%--</li>--%>
                                    <%--<li>--%>
                                        <%--<a href="#"><i class="icon-paper-plane"></i> Sample Link 1</a>--%>
                                    <%--</li>--%>
                                    <%--<li>--%>
                                        <%--<a href="#"><i class="icon-star"></i> Sample Link 1</a>--%>
                                    <%--</li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-camera"></i> Sample Link 1</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-link"></i> Sample Link 2</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-pointer"></i> Sample Link 3</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="javascript:;">--%>
                            <%--<i class="icon-globe"></i> Item 2 <span class="arrow"></span>--%>
                        <%--</a>--%>
                        <%--<ul class="sub-menu">--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-tag"></i> Sample Link 1</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-pencil"></i> Sample Link 1</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"><i class="icon-graph"></i> Sample Link 1</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="#">--%>
                            <%--<i class="icon-bar-chart"></i>--%>
                            <%--Item 3 </a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-user"></i>--%>
                    <%--<span class="title">登录表单</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="login.html">--%>
                            <%--Login Form 1</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="login_soft.html">--%>
                            <%--Login Form 2</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-briefcase"></i>--%>
                    <%--<span class="title">表格</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="table_basic.html">--%>
                            <%--Basic Datatables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="table_responsive.html">--%>
                            <%--Responsive Datatables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="table_managed.html">--%>
                            <%--Managed Datatables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="table_editable.html">--%>
                            <%--Editable Datatables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="table_advanced.html">--%>
                            <%--Advanced Datatables</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="table_ajax.html">--%>
                            <%--Ajax Datatables</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-wallet"></i>--%>
                    <%--<span class="title">Portlets</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="portlet_general.html">--%>
                            <%--General Portlets</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="portlet_ajax.html">--%>
                            <%--Ajax Portlets</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="portlet_draggable.html">--%>
                            <%--Draggable Portlets</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="icon-pointer"></i>--%>
                    <%--<span class="title">地图</span>--%>
                    <%--<span class="arrow "></span>--%>
                <%--</a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a href="maps_google.html">--%>
                            <%--Google Maps</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="maps_vector.html">--%>
                            <%--Vector Maps</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
            <%--<li class="last ">--%>
                <%--<a href="charts.html">--%>
                    <%--<i class="icon-bar-chart"></i>--%>
                    <%--<span class="title">可视化图标</span>--%>
                <%--</a>--%>
            <%--</li>--%>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>