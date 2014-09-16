<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <c:import url="/WEB-INF/include/global_head.jsp"/>
    </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
    <!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
    <!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
    <!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
    <!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
    <!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
    <!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
    <!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
    <!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
    <body class="page-header-fixed page-quick-sidebar-over-content">
        <!-- BEGIN HEADER -->
        <c:import url="/WEB-INF/include/header.jsp"/>
        <!-- END HEADER -->
        <div class="clearfix">
        </div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <c:import url="/WEB-INF/include/sidebar.jsp"/>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
                    <!--不知道这个有什么用，先注释掉...-->
                    <%--<c:import url="/WEB-INF/include/portlet_configuration.jsp"/>--%>
                    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
                    <!-- BEGIN STYLE CUSTOMIZER -->
                    <c:import url="/WEB-INF/include/style_customizer.jsp"/>
                    <!-- END STYLE CUSTOMIZER -->

                    <!--管理页面内容区 BEGIN-->
                    <!--使用ajax.load()动态加载页面-->
                    <div id="pageContent">
                        <c:import url="/WEB-INF/include/cover.jsp"/>
                    </div>
                    <!--管理页面内容区 END--> 
                </div>
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <c:import url="/WEB-INF/include/quick_sidebar.jsp"/>
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <c:import url="/WEB-INF/include/footer.jsp"/>
        <!-- END FOOTER -->
        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
        <%--<c:import url="/WEB-INF/include/load_scripts.jsp"/>--%>
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>
