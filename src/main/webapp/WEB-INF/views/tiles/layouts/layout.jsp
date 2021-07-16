<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
<title>타일즈 템플릿 사용</title>
</head>
<body>
		<t:insertAttribute name="header" />
		<t:insertAttribute name="content" />
		<t:insertAttribute name="footer" />
</body>
</html>