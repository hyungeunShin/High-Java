<%@page import="member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
<title>Insert title here</title>

</head>
<body>
	<%
		List<MemberVO> list = (List<MemberVO>) request.getAttribute("list");
	%>
	
	<h3>회원 목록</h3>
	<br><hr><br>
	
	<table border="1">
		<thead>
			<tr><td colspan="5"><input type="button" value="회원등록" id="insertmem" onclick="location.href='insertmember.jsp'"></td></tr>
			<tr>
				<th>ID</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화</th>
				<th>주소</th>
			</tr>
		</thead>
		
		<tbody>
			<%
				if(list == null || list.size() == 0) {
			%>
				<tr><td colspan="7" style="text-align: center;">저장된 회원이 하나도 없습니다</td></tr>
			<%		
				} else {
					for(MemberVO vo : list) {
			%>
				<tr>
					<td><a href="<%= request.getContextPath()%>/memberdetail.do?memid=<%= vo.getMem_id()%>"><%= vo.getMem_id() %></a></td>
					<td><%= vo.getMem_pass() %></td>
					<td><%= vo.getMem_name() %></td>
					<td><%= vo.getMem_tel() %></td>
					<td><%= vo.getMem_addr() %></td>
				</tr>
			<%			
					}
				}
			%>
		</tbody>
	</table>
</body>
</html>