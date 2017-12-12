package com.pinguin.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dto.pinguinDto;

public class pAddFreund implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		String fId = request.getParameter("fid");
		// String fName = request.getParameter("fName");
		// String fProfile = request.getParameter("fProfile");
		String path = request.getSession().getServletContext().getRealPath("/freund");
		File file = new File(path+ "/freund" + dto.getId() + ".txt");
		System.out.println(file);
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
		} catch (FileNotFoundException e) {
			file.createNewFile();
		}
		
		bw.write(fId);
		bw.newLine();
		bw.close();

		String viewPage = "freund.do";

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
