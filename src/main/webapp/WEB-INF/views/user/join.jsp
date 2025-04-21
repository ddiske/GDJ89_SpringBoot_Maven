<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
												<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
											</div>
											<form class="user" action="./join" method="post" enctype="multipart/form-data">
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<input type="text" class="form-control form-control-user"
															id="exampleID" placeholder="ID" name="username">
													</div>
													<div class="col-sm-6">
														<input type="password" class="form-control form-control-user"
															id="examplePassword" placeholder="Password" name="password">
													</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<input type="text" class="form-control form-control-user"
															id="exampleName" placeholder="Name" name="name">
													</div>
													<div class="col-sm-6">
														<input type="date" class="form-control form-control-user"
															id="exampleBirth" placeholder="Birth" name="birth">
													</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<input type="email"
															class="form-control form-control-user"
															id="exampleInputEmail" placeholder="Email Address" name="email">
													</div>
													<div class="col-sm-6">
														<input type="tel"
															class="form-control form-control-user"
															id="examplePhone" placeholder="Phone Number" name="phone">
													</div>
												</div>
				                                <input type="file" name="multipartFile">
												<button type="submit" class="btn btn-primary btn-user btn-block mt-2">Register Account</button>
												<hr>
												<a href="index.html"
													class="btn btn-google btn-user btn-block"> <i
													class="fab fa-google fa-fw"></i> Register with Google
												</a> <a href="index.html"
													class="btn btn-facebook btn-user btn-block"> <i
													class="fab fa-facebook-f fa-fw"></i> Register with Facebook
												</a>
											</form>
											<hr>
											<div class="text-center">
												<a class="small" href="forgot-password.html">Forgot
													Password?</a>
											</div>
											<div class="text-center">
												<a class="small" href="login.html">Already have an
													account? Login!</a>
											</div>
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