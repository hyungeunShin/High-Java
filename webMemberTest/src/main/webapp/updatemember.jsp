<%@page import="member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		MemberVO vo = (MemberVO) request.getAttribute("member");
	%>
	<form action="<%= request.getContextPath()%>/updateMember.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td colspan="2"><img src="<%= request.getContextPath() %>/images/imageview.do?mem_photo=<%= vo.getMem_photo() %>" width="100" height="100"></td>
		</tr>
		
		<tr>
			<td>회원ID</td>
			<td><input type="text" readonly="readonly" name="uid" value="<%= vo.getMem_id() %>"><%= vo.getMem_id() %></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		
		<tr>
			<td>회원이름</td>
			<td><input type="text" name="uname" value="<%= vo.getMem_name() %>"></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="utel" value="<%= vo.getMem_tel() %>"></td>
		</tr>
		
		<tr>
			<td>회원주소</td>
			<td><input type="text" name="uaddr" value="<%= vo.getMem_addr() %>"></td>
		</tr>
		
		<tr>
			<td>프로필 사진</td>
			<td><input type="file" name="userfile"></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장">
				<input type="reset" value="취소">
				<input type="button" value="회원 리스트" onclick="location.href='<%= request.getContextPath() %>/memberList.do'">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>