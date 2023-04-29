<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>

<script type="text/javascript">
$(function() {
	$('#memlist').on('click', function() {
		location.href = "<%= request.getContextPath() %>/memberList.do";
	})
	
	$('#insertmem').on('click', function() {
		pw = $('#pw').val();
		pwcheck = $('#pwcheck').val();
		
		if(pw != pwcheck) {
			alert("비밀번호가 다름");
			return false;
		} 
	})
	
	$('#checkid').on('click', function() {
		userid = $('#uid').val();
		
		$.ajax({
			url : "<%= request.getContextPath() %>/IDCheck.do",
			type : "get",
			data : { "id" : userid },
			success : function(res) {
				cnt = <%= request.getAttribute("id") %>
				console.log(cnt);
			},
			error : function(xhr) {
				alert(xhr.status);
			}
			//dataType : "json"
		})
	})
})
</script>
</head>
<body>
	<form action="<%= request.getContextPath()%>/insertMember.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>회원ID</td>
				<td><input type="text" name="userid" id="uid"><input type="button" id="checkid" value="중복확인"></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userpw" id="pw"></td>
			</tr>
			
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="checkpw" id="pwcheck"></td>
			</tr>
			
			<tr>
				<td>회원이름</td>
				<td><input type="text" name="username"></td>
			</tr>
			
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="usertel"></td>
			</tr>
			
			<tr>
				<td>회원주소</td>
				<td><input type="text" name="useraddr"></td>
			</tr>
			
			<tr>
				<td>프로필 사진</td>
				<td><input type="file" name="userfile"></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="저장" id="insertmem">
					<input type="reset" value="취소" id="cancel">
					<input type="button" value="회원목록" id="memlist">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>