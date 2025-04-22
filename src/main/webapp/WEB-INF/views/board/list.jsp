<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	    		
				    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800"><spring:message code="board.notice.title"></spring:message> </h1>
                    
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                        <div class="row mb-2">
	                        <div class="col-2 offset-md-11">
	                       		<button class="btn btn-secondary" type="button"><a href="./add" style="text-decoration: none;">새 글 작성</a></button>
	                        </div>
                        </div>
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th class="col-1">Num</th>
                                            <th>Title</th>
                                            <th class="col-1">User</th>
                                            <th class="col-1">Date</th>
                                            <th class="col-1">Hit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${list}" var="vo">
                                        	<tr>
												<td>${vo.boardNum }</td>
												<td><a href="./detail?boardNum=${vo.boardNum }">${vo.boardTitle }</a></td>
												<td>${vo.userName }</td>                                       	
												<td>${vo.boardDate }</td>
												<td>${vo.boardHit }</td>
                                        	</tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                            	<div class="col">
                            		<form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100">
										<div class="input-group">
											<select name="kind" id="getKind">
												<option value="k1" ${pager.kind == 'k1'? 'selected':'' }>제목</option>
												<option value="k2" ${pager.kind == 'k2'? 'selected':'' }>제목+내용</option>
												<option value="k3" ${pager.kind == 'k3'? 'selected':'' }>작성자</option>
											</select>
											<input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" name="search" value="${pager.search}">
											<div class="input-group-append">
												<button class="btn btn-primary" type="submit">
													<i class="fas fa-search fa-sm"></i>
												</button>
											</div>
										</div>
									</form>
                            	</div>
                            	<div class="col">
                            		<nav aria-label="Page navigation example">
									  <ul class="pagination justify-content-end">
									    <li class="page-item ${pager.startCheck?'disabled':''}">
									      <a class="page-link" href="?page=${pager.startBlock-1 }&&kind=${pager.kind}&&search=${pager.search}">Previous</a>
									    </li>
									    <c:forEach begin="${pager.startBlock }" end="${pager.endBlock }" var="i">
										    <li class="page-item ${pager.page == i?'active':''}"><a class="page-link" href="?page=${i }&&kind=${pager.kind}&&search=${pager.search}">${i }</a></li>
									    </c:forEach>
									    <li class="page-item ${pager.endCheck?'disabled':''}">
									      <a class="page-link" href="?page=${pager.endBlock+1 }&&kind=${pager.kind}&&search=${pager.search}">Next</a>
									    </li>
									  </ul>
									</nav>
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