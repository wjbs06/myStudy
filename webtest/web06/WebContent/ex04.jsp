<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	java.io.InputStream is=application.getResourceAsStream("myfile.txt");
	java.io.InputStreamReader isr=new InputStreamReader(is);
	BufferedReader br=new BufferedReader(isr);
	String msg=null;
	while(true){
		msg=br.readLine();
		if(msg==null){break;}
		System.out.println(msg);
	}
	
	java.net.URL url=application.getResource("myfile.txt");
	System.out.println(url.getProtocol());
	System.out.println(url.getHost());
	System.out.println(url.getPath());
	
	String real=application.getRealPath("myfile.txt");
	System.out.println(real);
	%>
</body>
</html>