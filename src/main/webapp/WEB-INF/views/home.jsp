<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./templates/header.jsp"></c:import>
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
<c:import url="./templates/sidebar.jsp"></c:import>
	<!-- Content Wrapper -->
   	<div id="content-wrapper" class="d-flex flex-column">	
		<!-- Main Content -->
    	<div id="content">
     	<c:import url="./templates/topbar.jsp"></c:import>


			<!-- content  /.container-fluid  -->
	    	<div class="container-fluid">
	    	
	    		<sec:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
	    			<h3>Admin View</h3>
	    		</sec:authorize>
	    		<sec:authorize access="isAuthenticated()">
		    		<sec:authentication property="principal" var="user"/>
	    		</sec:authorize>
	    	
	    		<p>
		    		<spring:message code="welcome"></spring:message>
	    		</p>
	    		<p>
		    		<spring:message code="hello" var="m"></spring:message>
	    		</p>
	    		<p>
	    			<spring:message code="hi" text="안녕"></spring:message>
	    		</p>
				<h1>Home</h1>
				<h3>${m }</h3>
				<%-- <c:if test="${not empty user.sns }">
					<img alt="" src="${user.fileName }">
				</c:if>
				<c:if test="${empty user.sns }">
					<img alt="" src="/files/user/${user.fileName }">
				</c:if> --%>
				
				<spring:message code="welcome.login" arguments="${user.username }, ${user.name }" argumentSeparator=","></spring:message>
				
				<sec:authorize access="isAuthenticated()" url="/notice/add" var="user">
					<a href="/user">Notice</a>
				</sec:authorize>
				
				<img alt="" src="/images/1.jpg">
				<img alt="" src="/images/2.jpg">
	    
	    	</div>
		
	
		</div>
    	<!-- End of Main Content -->
	<c:import url="./templates/contentFooter.jsp"></c:import>
	</div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
    
	
<c:import url="./templates/footer.jsp"></c:import>
</body>
</html>