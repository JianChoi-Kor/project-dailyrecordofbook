<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tiles_temp</title>
<link rel="stylesheet" href="/res/css/common.css?ver=1">
</head>
<body>
	<div>
		<tiles:insertAttribute name="header"/>
		<section>
			<tiles:insertAttribute name="content"/>
		</section>
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>