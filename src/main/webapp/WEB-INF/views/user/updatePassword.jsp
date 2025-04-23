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
												<h1 class="h4 text-gray-900 mb-4">Password Update!</h1>
											</div>
											<sec:authentication property="principal" var="user"/>
											<form:form modelAttribute="userVO" cssClass="user" action="./updatePassword" method="post" enctype="multipart/form-data">
												<div class="form-group">
													<form:password cssClass="form-control form-control-user"
															id="exampleID" placeholder="Old Password" path="password"/>
															<div>
																<form:errors path="password"></form:errors>
															</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:password	cssClass="form-control form-control-user"
															id="examplePassword" placeholder="New Password" path="password"/>
															<div>
																<form:errors path="password"></form:errors>
															</div>
													</div>
													<div class="col-sm-6">
														<form:password cssClass="form-control form-control-user"
															id="examplePasswordCheck" placeholder="Password Check" path="passwordCheck"/>
													</div>
												</div>
												<button type="submit" class="btn btn-primary btn-user btn-block mt-2">수정하기</button>
												<hr>
											</form:form>
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