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
											<sec:authentication property="principal" var="user"/>
											<form:form modelAttribute="userVO" cssClass="user" action="./update" method="post" enctype="multipart/form-data">
											<form:hidden path="username" value="${user.username }"/>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:input cssClass="form-control form-control-user"
															id="exampleName" placeholder="Name" path="name" value="${user.name }" />
													</div>
													<div class="col-sm-6">
														<input type="date" Class="form-control form-control-user"
															id="exampleBirth" placeholder="Birth" name="birth" value="${user.birth }"/>
															<div>
																<form:errors path="birth"></form:errors>
															</div>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:input	cssClass="form-control form-control-user"
															id="exampleInputEmail" placeholder="Email Address" path="email" value="${user.email }"/>
															<div>
																<form:errors path="email"></form:errors>
															</div>
													</div>
													<div class="col-sm-6">
														<form:input cssClass="form-control form-control-user"
															id="examplePhone" placeholder="Phone Number" path="phone" value="${user.phone }"></form:input>
													</div>
												</div>
				                                <input type="file" name="multipartFile">
												<button type="submit" class="btn btn-primary btn-user btn-block mt-2">수정하기</button>
												<hr>
												<a href="./updatePassword"	class="btn btn-google btn-user btn-block"> 비밀번호 변경</a>
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