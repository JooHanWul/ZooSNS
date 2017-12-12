package com.pinguin.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pDelTimeline implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "index.do";

		String delTimeline = request.getParameter("tag");
		System.out.println(delTimeline);

		request.getServletContext().setAttribute(delTimeline, "txt 000000 00000 0000-00-00 00:00:00");

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
