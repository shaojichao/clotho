<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tag-lib.tag" %>

<li class="sidebar-search-wrapper hidden-xs">
    <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
    <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
    <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
    <form class="sidebar-search" action="extra_search.html" method="POST">
        <a href="javascript:;" class="remove">
            <i class="icon-close"></i>
        </a>
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search...">
            <span class="input-group-btn">
                <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
            </span>
        </div>
    </form>
    <!-- END RESPONSIVE QUICK SEARCH FORM -->
</li>