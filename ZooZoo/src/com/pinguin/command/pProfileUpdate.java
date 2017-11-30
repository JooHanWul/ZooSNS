package com.pinguin.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.pinguinDto;

public class pProfileUpdate implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		pinguinDao dao = new pinguinDao();
		HttpSession session = request.getSession();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");

		String path = request.getSession().getServletContext().getRealPath("/profileImg");
		System.out.println(path);

		int size = 1024 * 1024 * 10; // 10MB
		String file = "";

		MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

		Enumeration<?> files = multi.getFileNames();

		try {
			String str = (String) files.nextElement();
			file = multi.getFilesystemName(str);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
		System.out.println("file: " + file);
		if (file != null) {
			dto.setProfileImg(file);
		} else {
			dto.setProfileImg("null.png");
		}

		System.out.println(dto.getProfileImg());
		dao.updateProfile(dto.getProfileImg(), dto.getId());

		session.setAttribute("whospinguin", dto);

		String viewPage = "index.jsp";

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
