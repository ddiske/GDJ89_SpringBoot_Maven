<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    	
				<div class="col-lg-8">
                    <!-- Post content-->
                    <article>
                    <div class="card bg-light mb-5">
                    <div class="card-body">
                        <!-- Post header-->
                        <header class="mb-4">
                            <!-- Post title-->
                            <h1 class="fw-bolder mb-1">${vo.boardTitle }</h1>
                            <!-- Post meta content-->
	                            <div class="text-muted fst-italic mb-2">${vo.boardDate }</div>
                            <div class="row">
	                            <div class="col">조회수 : ${vo.boardHit }</div>
	                            <div class="col">작성자 : ${vo.userName }</div>
                            </div>
                        </header>
                        <!-- Post content-->
                        <section class="mb-5">
                            <p class="fs-5 mb-4">${vo.boardContents }</p>
                        </section>
                        <div class="">
                        	<c:forEach items="${vo.boardFileVOs }" var="f">
                        		<%-- <img alt="" src="/files/${kind }/${f.fileName}">${f.oldName } --%>
                        		<a href="./fileDown?fileNum=${f.fileNum }">${f.oldName }</a>
                        	</c:forEach>
                        </div>
                        <div class="offset-md-10 align-self-end mb-2">
	                       	<button class="btn btn-secondary" type="button"><a href="./update?boardNum=${vo.boardNum }">수정</a></button>
	                       	<form action="./delete" method="post">
	                       		<input type="hidden" value="${vo.boardNum }" name="boardNum">
		                       	<button class="btn btn-danger" type="submit">삭제</button>
	                       	</form>
                        </div>                      	
                    </div>
                    </div>
                    </article>
                    <!-- Comments section-->
                    <section class="mb-5">
                        <div class="card bg-light">
                            <div class="card-body">
                                <!-- Comment form-->
                                <form class="mb-4"><textarea class="form-control" rows="3" placeholder="Join the discussion and leave a comment!"></textarea></form>
                                <!-- Comment with nested comments-->
                                <div class="d-flex mb-4">
                                    <!-- Parent comment-->
                                    <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..."></div>
                                    <div class="ms-3">
                                        <div class="fw-bold">Commenter Name</div>
                                        If you're going to lead a space frontier, it has to be government; it'll never be private enterprise. Because the space frontier is dangerous, and it's expensive, and it has unquantified risks.
                                        <!-- Child comment 1-->
                                        <div class="d-flex mt-4">
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..."></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">Commenter Name</div>
                                                And under those conditions, you cannot establish a capital-market evaluation of that enterprise. You can't get investors.
                                            </div>
                                        </div>
                                        <!-- Child comment 2-->
                                        <div class="d-flex mt-4">
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..."></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">Commenter Name</div>
                                                When you put money directly to a problem, it makes a good headline.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Single comment-->
                                <div class="d-flex">
                                    <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..."></div>
                                    <div class="ms-3">
                                        <div class="fw-bold">Commenter Name</div>
                                        When I look at the universe and all the ways the universe wants to kill us, I find it hard to reconcile that with statements of beneficence.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
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