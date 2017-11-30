package com.pinguin.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pIndex implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "index.jsp";

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
		// response.sendRedirect(viewPage);
	}

}
