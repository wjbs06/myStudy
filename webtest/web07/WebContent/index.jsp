<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>내용</h1>
	<%
//	out.println(application.getAttribute("sub"));
	//out.println(application.getAttribute("content"));
	//String[] row=(String[])application.getAttribute("row");
	java.util.ArrayList<String[]> list=(java.util.ArrayList<String[]>)application.getAttribute("list");
	%>
	<table>
		<tr>
			<td>제목</td>			
			<td>내용</td>
		</tr>
		<%//if(row!=null){  
			if(list!=null){
			for(int i=0;i<list.size();i++){
				String[] row=list.get(i);
			
		%>
		<tr>
			<td><%=row[0] %></td>			
			<td><%=row[1] %></td>
		</tr>
		<%} 
		}%>
	</table>	
	
	<p><a href="add.jsp">입력</a></p>
</body>
</html>