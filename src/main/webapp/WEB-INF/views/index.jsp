<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<%@ page import="java.util.Date" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Search Car</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" >
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/redmond/jquery-ui-1.8.20.custom.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/custom.css" />

</head>
<body>
    <input type="text" style="display:none;" value="${startYear}"/>
    <input type="text" style="display:none;" value="${endYear}"/>

    <div class="container main">
        <br/>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title gray">Search car by make/model/year
                </h1>
            </div>
            <div class="panel-body">
                <form class="form-horizontal search-form" role="form">
                    <div class="form-group">
                        <label for="yearOption" class="col-sm-3 control-label">Year</label>
                        <div class="col-sm-9 col-lg-3 col-md-6">
                            <select id="yearOption" class="form-control input-sum">
                                <c:forEach var="i" begin="${startYear}" end="${endYear}">
                                    <option
                                        <c:if test="${i==endYear}">
                                            selected
                                        </c:if>
                                    >
                                        ${i}
                                    </option>
                                </c:forEach>
                                <option value="-1">All years</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="makeOption" class="col-sm-3 control-label">Make
                            <span style="display:none;" class="text-danger validation-cmp" data-toggle="tooltip" field-name="Make" data-placement="right">*</span>
                        </label>
                        <div class="col-lg-3 col-md-6 col-sm-9">
                            <input id="makeOption" type="text" class="form-control input-sm required" data-type="string"></input>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modelOption" class="col-sm-3 control-label">Model
                            <span style="display:none;" class="text-danger validation-cmp" data-toggle="tooltip" field-name="Model" data-placement="right">*</span>
                        </label>
                        <div class="col-lg-3 col-md-6 col-sm-9">
                            <input id="modelOption" type="text" class="form-control input-sm required" data-type="string"></input>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="searchBtn" class="col-sm-3 control-label"></label>
                        <button id="searchBtn" type="button" class="btn btn-primary btn-sm" style="margin-left: 15px;">Search</button>
                    <div>
                    <div class="form-group">
                        <div class="col-md-7 col-md-offset-3">
                            <div id="display_list" class="list-group" display-position="bottom" style="padding-top: 20px;">

                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-ui-1.8.20.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/carCheck.js"></script>
</body>
</html>