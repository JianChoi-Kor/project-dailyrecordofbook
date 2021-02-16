<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>tiles_temp</title>
<link rel="stylesheet" href="/res/css/common.css?ver=1">
<link rel="stylesheet" href="/res/css/header.css?ver=1">
<link rel="stylesheet" href="/res/css/footer.css?ver=1">
<link rel="stylesheet" href="<tiles:getAsString name="categoryCss"/>">
<script defer src="<tiles:getAsString name="categoryJs"/>"></script>

</head>
<body>
	<!-- header -->
	<tiles:insertAttribute name="header"/>
	
	<!-- content -->
	<section>
		<tiles:insertAttribute name="content"/>
	</section>
	
	<!-- footer -->
	<tiles:insertAttribute name="footer"/>
</body>
</html>