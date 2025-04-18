<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    	
				<h1>Home</h1>
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