package com.pinguin.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.pinguinDto;

public class pFreund implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "freund.jsp";
		ArrayList<String[]> fList = null;

		HttpSession session = request.getSession();
		
		pinguinDao dao = new pinguinDao();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		
		String uId = dto.getId();
		fList = dao.freundList(uId);
		
		request.setAttribute("freund", fList);
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
