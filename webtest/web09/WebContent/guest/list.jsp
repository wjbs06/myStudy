<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%
int rowcnt=10;
String temp1=request.getParameter("rowcnt");
if(temp1!=null){rowcnt=Integer.parseInt(temp1);}
int tot=0;
String sql2="select count(*) as tot from guest";
try{
	stmt=MyOracle.getConnection().createStatement();
	rs=stmt.executeQuery(sql2);
	if(rs.next()){
		tot=rs.getInt(1);
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	MyOracle.getConnection().close();
}

int startNum=1;
String pg=request.getParameter("page");
if(pg!=null){
	startNum=rowcnt*(Integer.parseInt(pg)-1)+1;
	//p1=1=10*0+1
	//p2=11=10*1+1
	//p3=21=10*2+1
}
String keyword=request.getParameter("keyword");
String sql="SELECT ROWNUM AS RN,SABUN,NAME,NALJA,PAY FROM (SELECT * FROM GUEST ORDER BY SABUN)";
//if(keyword!=null) sql+="WHERE LIKE NAME '%"+keyword+"%'";
//if(keyword!=null) sql="SELECT ROWNUM AS RN,SABUN,NAME,NALJA,PAY FROM (SELECT * FROM GUEST WHERE LIKE NAME '%"+keyword+"%' ORDER BY SABUN)";
//if(keyword!=null) sql="select * from ("+sql+") where name like '%"+keyword+"%'";
//sql="select * from ("+sql+") where rn between 11 and 20";
if(keyword!=null){
	sql="select rownum as page,rn,sabun,name,nalja,pay from ("+sql+") where name like '%"+keyword+"%'";
	sql="select * from ("+sql+") where rn between "+startNum+" and "+(startNum+(rowcnt-1))+"";
}else{
	sql="select * from ("+sql+") where rn between "+startNum+" and "+(startNum+(rowcnt-1))+"";
}
ArrayList<GuestBean> list =new ArrayList<GuestBean>();

try{
	Statement stmt= MyOracle.getConnection().createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	while(rs.next()){
		GuestBean bean=new GuestBean();
		bean.setRn(rs.getInt("rn"));
		bean.setSabun(rs.getInt("sabun"));
		bean.setName(rs.getString("name"));
		bean.setNalja(rs.getDate("nalja"));
		bean.setPay(rs.getInt("pay"));
		list.add(bean);
	}
	
}catch(Exception e){
	
}finally{
	MyOracle.getConnection().close();
}
%>
<body>
	<%@ include file="../template/header.jspf" %>
	<%@ include file="../template/menu.jspf" %>
				<h1>리스트페이지</h1>
				<p align="center">
				<form action="list.jsp">
					<input type="text" name="keyword">
					<input type="submit" value="검색">				
				</form>
				</p>
				<p align="right">
					<form action="list.jsp">
					<select name="rowcnt">
						<option value="5">5개씩 보기</option>
						<option value="10">10개씩 보기</option>
						<option value="15">15개씩 보기</option>
					</select>
					<input type="submit" value="보기">
					</form>
				</p>
				<table border="1" cellspacing="0" width="80%" align="center">	
					<tr>
						<th width="120">글번호</th>
						<th>사번</th>
						<th>이름</th>
						<th width="120">날짜</th>
						<th>금액</th>
					</tr>
					<%
						for(int i=0; i<list.size(); i++){
							GuestBean javaBean=list.get(i);
							
					%>
					<tr>
						<td><%=javaBean.getRn() %></td>
						<td><%=javaBean.getSabun() %></td>
						<td><a href="detail.jsp?idx=<%=javaBean.getSabun() %>"><%=javaBean.getName() %></a></td>
						<td><%=javaBean.getNalja() %></td>
						<td><%=javaBean.getPay() %></td>
					</tr>
					<%} %>
				</table>
				<%
				int leng=3;
				int p=1;
				if(pg!=null){
					p=Integer.parseInt(pg);
				}
				//int start=(p-1)/3*leng;	//0,5,10
				int start=(p-2);
				if(start<0)start=0;
				int end=start+leng;	//5,10,15
				tot=(tot-1)/10+1;
				if(start!=0){
				%>
				<a href="list.jsp?page=1&rowcnt=<%=rowcnt%>">[처음으로]</a>
				<a href="list.jsp?page=<%=start %>&rowcnt=<%=rowcnt%>">[prev]</a>
				<% 
				}
				for(int i=start;i<end;i++){
					if(i+1>tot)break;
				%>
						<a href="list.jsp?page=<%=i+1%>&rowcnt=<%=rowcnt%>">[<%=i+1%>]</a>
				<%
				}
				if(tot+1>end){
				%>
				<a href="list.jsp?page=<%=end+1 %>&rowcnt=<%=rowcnt%>">[next]</a>
				<a href="list.jsp?page=<%=tot %>&rowcnt=<%=rowcnt%>">[끝으로]</a>
				<%}	%>
	<a href="add.jsp">[입력]</a>
	<%@ include file="../template/footer.jspf" %>
</body>
</html>