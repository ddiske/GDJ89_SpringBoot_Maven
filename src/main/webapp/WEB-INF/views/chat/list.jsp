<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    	
	    		<h1>Chat List</h1>
	    		
	    		<div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                            <c:forEach items="${list}" var="vo">
                            	<tr>
									<td>${vo.username }</td>
									<td>${vo.name }</td>
									<td><img class="img-profile rounded-circle" src="/files/user/${vo.fileName }" style="width: 30px; height: 30px"></td>
									<td><span class="btn ${vo.status? 'btn-success' : 'btn-danger' } btn-circle" style="width: 30px; height: 30px" data-toggle="modal" data-target="#chat"></span></td>
                            	</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
	    		
				<!-- <div>
					<input type="text" id="message">
					<button id="send">SEND</button>
				</div> -->
	    
	    	</div>
	    	
		</div>
    	<!-- End of Main Content -->
	<c:import url="/WEB-INF/views/templates/contentFooter.jsp"></c:import>
	</div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
    
	
<c:import url="/WEB-INF/views/templates/footer.jsp"></c:import>
<div class="modal" id="chat" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Modal title</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div id="chat-body"></div>
        </div>
        <div class="modal-footer">
            <div>
                <sec:authorize access="isAuthenticated()">
		    		<sec:authentication property="principal" var="user"/>
	    		</sec:authorize>
                <input type="hidden" id="sender" value="${user.username}">
                <input type="hidden" id="receiver">
                <input type="text" id="message">
                <button id="send">SEND</button>
            </div>
          <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button> -->
        </div>
      </div>
    </div>
  </div>
<script src="/js/chat/chat.js"></script>
</body>
</html>