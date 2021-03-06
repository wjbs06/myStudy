package com.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.GuestDao;
import com.bit.model.GuestDto;

public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//model �۾�
		GuestDao dao=new GuestDao();
		ArrayList<GuestDto> list=dao.selectAll();
		
		req.setAttribute("alist", list);
		//view ����
		RequestDispatcher rd=req.getRequestDispatcher("list.jsp");
		rd.forward(req, resp);
	}
}
