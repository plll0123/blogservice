<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog 블로그 메인</title>
<script type="text/javascript">
	function popup() {
		window.open('deleteRequest', 'popup', 'width=800, height=300, location=no, status=no, menubar=no, scrollbars=no, resizale=no, left=350, top=150');
	}
</script>
</head>
<body background="images/kubrickbgcolor.jpg">
	<center>
		<table background="images/kubrickheader.jpg" width="760" height="200" border="0" cellpadding="0" cellspacing="0">
			<tr><td height="60">&nbsp;</td></tr>
			<!-- 블로그 제목과 태그 시작 -->
			<tr><td height="60" align="center"><h1><font color="white">테스트 블로그</font></h1></td></tr>
			<tr><td height="20" align="center"><h3><font color="white">NO Tags</font></h3></td></tr>
			<!-- 블로그 제목과 태그 끝 -->
			<tr align="right">
				<td>
					<a href="#">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#">로그아웃</a>&nbsp;&nbsp;&nbsp;
					<a href="#">블로그관리</a>&nbsp;&nbsp;&nbsp;
					<a href="#">블로그메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>