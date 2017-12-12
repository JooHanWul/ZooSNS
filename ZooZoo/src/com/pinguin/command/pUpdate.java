package com.pinguin.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.pinguinDto;

public class pUpdate implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pinguinDto dto = null;
		String id, pw, name, newPw;
		String viewPage = null;

		id = request.getParameter("id");
		pw = request.getParameter("psw");
		newPw = request.getParameter("newPw");
		name = request.getParameter("name");

		pinguinDao pDao = new pinguinDao();
		if (pDao.update(id, pw, newPw, name)) {
			viewPage = "index.do";
			
			dto = pDao.userInfo(id, newPw);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("whospinguin", dto);
			httpSession.setMaxInactiveInterval(-1);
		} else {
			request.setAttribute("errorCode", "updateFalse");
			viewPage = "error.do";
		}

		

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
