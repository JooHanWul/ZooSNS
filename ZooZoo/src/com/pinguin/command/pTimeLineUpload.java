package com.pinguin.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dto.pinguinDto;

public class pTimeLineUpload implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null;

		Date nowDate = new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);
		String uhr = new SimpleDateFormat("kk").format(nowDate);
		String min = new SimpleDateFormat("mm").format(nowDate);
		String sec = new SimpleDateFormat("ss").format(nowDate);
		int times = Integer.parseInt(uhr) * 60 * 60 + Integer.parseInt(min) * 60 + Integer.parseInt(sec);
		String julian = new SimpleDateFormat("'0'yyD").format(nowDate);
		String context = request.getParameter("text");

		HttpSession session = request.getSession();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		System.out.println(dto.getId());
		
		int i = 0;
		while (true) {
			String key = dto.getId() + "." + i;
			System.out.println("키값: " + key);
			String app = (String) request.getServletContext().getAttribute(key);
			System.out.println(app);
			if (app == null) {
				request.getServletContext().setAttribute(key,
						"txt " + julian + " " + times + " " + date + " " + context);
				System.out.println("타임라인 등록 완료");
				break;
			} else {
				i++;
			}
		}

		viewPage = "index.do";
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
