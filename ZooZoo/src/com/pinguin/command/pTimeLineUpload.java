package com.pinguin.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pinguin.dto.pinguinDto;

public class pTimeLineUpload implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String viewPage = null;
		String txt = "";

		String uploadPath = request.getSession().getServletContext().getRealPath("/FileFolder");
		System.out.println(uploadPath);

		int size = 1024 * 1024 * 100; // 100MB
		String name = "";
		String file = "";
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());

		Enumeration<?> files = multi.getFileNames();
		try {
			String str = (String) files.nextElement();
			file = multi.getFilesystemName(str);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		System.out.println("file: " + file);
		if (file != null) {
			if (file.endsWith(".mp4")) {
				txt = "vid";
			} else if (file.toLowerCase().endsWith(".jpg") || file.toLowerCase().endsWith(".png")
					|| file.toLowerCase().endsWith(".gif") || file.toLowerCase().endsWith(".jpeg")) {
				txt = "img";
			}
		} else {
			txt = "txt";
		}

		Date nowDate = new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);
		String uhr = new SimpleDateFormat("kk").format(nowDate);
		String min = new SimpleDateFormat("mm").format(nowDate);
		String sec = new SimpleDateFormat("ss").format(nowDate);
		int times = Integer.parseInt(uhr) * 60 * 60 + Integer.parseInt(min) * 60 + Integer.parseInt(sec);
		String julian = new SimpleDateFormat("'0'yyD").format(nowDate);
		String context = multi.getParameter("text");

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
						txt + " " + julian + " " + times + " " + date + " " + context);
				if (txt.equals("img")) {
					request.getServletContext().setAttribute("img." + key, file);
				} else if (txt.equals("vid")) {
					request.getServletContext().setAttribute("vid." + key, file);
				}
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
