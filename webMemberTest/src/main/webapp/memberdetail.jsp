<%@page import="member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>

</head>
<body>
	<%
		MemberVO vo = (MemberVO) request.getAttribute("member");
	%>
	
	<table border="1">
		<tr>
			<td colspan="2"><img src="<%= request.getContextPath() %>/images/imageview.do?mem_photo=<%= vo.getMem_photo() %>" width="100" height="100"></td>
		</tr>
		
		<tr>
			<td>회원ID</td>
			<td><%= vo.getMem_id() %></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><%= vo.getMem_pass() %></td>
		</tr>
		
		<tr>
			<td>회원이름</td>
			<td><%= vo.getMem_name() %></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><%= vo.getMem_tel() %></td>
		</tr>
		
		<tr>
			<td>회원주소</td>
			<td><%= vo.getMem_addr() %></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<button type="button" id="update" onclick="location.href='<%= request.getContextPath() %>/memberdetail.do?memid=<%= vo.getMem_id() %>&isupdate=true'">수정</button>
				<button type="button" id="delete" onclick="location.href='<%= request.getContextPath() %>/deleteMember.do?memid=<%= vo.getMem_id() %>'">삭제</button>
				<button type="button" id="memlist" onclick="location.href='<%= request.getContextPath() %>/memberList.do'">회원 리스트</button>
			</td>
		</tr>
	</table>
</body>
</html>