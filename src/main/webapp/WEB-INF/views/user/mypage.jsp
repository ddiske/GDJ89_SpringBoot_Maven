<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/templates/header.jsp"></c:import>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<c:import url="/WEB-INF/views/templates/sidebar.jsp"></c:import>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<c:import url="/WEB-INF/views/templates/topbar.jsp"></c:import>

				<!-- content  /.container-fluid  -->
				<div class="container-fluid">

					<div class="container">

						<div class="card o-hidden border-0 shadow-lg my-5">
							<div class="card-body p-0">
								<!-- Nested Row within Card Body -->
								<div class="row">
									<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
									<div class="col-lg-7">
										<div class="p-5">
											<div class="text-center">
												<h1 class="h4 text-gray-900 mb-4">My Page!</h1>
											</div>
											
											<h3>ID : <sec:authentication property="name"/></h3>
											<hr>
											<sec:authentication property="principal" var="user"/>
											<h3>Name : ${user.name }</h3>
											<hr>
											<h3>Email : ${user.email }</h3>
											<hr>
											<h3>Phone : <sec:authentication property="principal.phone"/></h3>
											<hr>
											<a href="./update">정보수정</a>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>

			</div>
			<!-- End of Main Content -->
			<c:import url="/WEB-INF/views/templates/contentFooter.jsp"></c:import>
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->


	<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
</body>
</html>