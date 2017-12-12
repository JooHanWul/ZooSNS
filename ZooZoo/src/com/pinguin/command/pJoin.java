package com.pinguin.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.pinguinDto;

public class pJoin implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pinguinDto dto = null;
		String id, pw, name, gender, birth;
		String viewPage = null;

		id = request.getParameter("id");
		pw = request.getParameter("psw");
		name = request.getParameter("name");
		birth = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
		gender = request.getParameter("Geschlechtsunterschied");

		pinguinDao pDao = new pinguinDao();
		if (pDao.join(id, pw, name, birth, gender)) {
			viewPage = "index.do";
			// viewPage = "readTimeLine.do";
		} else {
			request.setAttribute("errorCode", "JoinFalse");
			viewPage = "error.do";
		}

		dto = pDao.userInfo(id, pw);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("whospinguin", dto);
		httpSession.setMaxInactiveInterval(-1);

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
