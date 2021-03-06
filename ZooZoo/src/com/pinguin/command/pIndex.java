package com.pinguin.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dto.pinguinDto;

public class pIndex implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = "index.jsp";
		pinguinDto dto = null;
		ArrayList<String> list = new ArrayList<>();

		HttpSession httpSession = request.getSession();
		dto = (pinguinDto) httpSession.getAttribute("whospinguin");

		String path = request.getSession().getServletContext().getRealPath("/freund");
		File file = new File(path + "/freund" + dto.getId() + ".txt");

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			file.createNewFile();
		}
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (NullPointerException e) {
			System.err.println("친구가 없구나...");
		}

		httpSession.setAttribute("pinguinFreund", list);

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
		// response.sendRedirect(viewPage);
	}

}
