package com.pinguin.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.pinguinDto;

public class pLogin implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null;
		pinguinDto dto = null;
		ArrayList<String> list = new ArrayList<>();
		String line = null;

		String id = request.getParameter("id");
		String pw = request.getParameter("psw");
		// System.out.println(id + ":" + pw);

		pinguinDao pDao = new pinguinDao();

		int checkId = pDao.login(id, pw);

		if (checkId == 1) { // 아이디와 비밀번호가 맞으면 다음페이지로 이동
			dto = pDao.userInfo(id, pw);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("whospinguin", dto);
			httpSession.setMaxInactiveInterval(-1);

			String path = request.getSession().getServletContext().getRealPath("/freund");
			File file = new File(path + "/freund" + dto.getId() + ".txt");

			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				file.createNewFile();
			}

			while ((line = br.readLine()) != null) {
				list.add(line);
			}

			httpSession.setAttribute("pinguinFreund", list);

			viewPage = "index.do";
			// viewPage = "readTimeLine.do";
		} else if (checkId == 0) { // 틀렸을경우 알림창 호출 후 로그인 페이지로 이동
			request.setAttribute("errorCode", "falseId");
			viewPage = "error.do";
		} else if (checkId == -1) { // 틀렸을경우 알림창 호출 후 로그인 페이지로 이동
			request.setAttribute("errorCode", "falsePw");
			viewPage = "error.do";
		}

		System.out.println("로그인 처리결과: " + viewPage);
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
