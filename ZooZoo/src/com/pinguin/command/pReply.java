package com.pinguin.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pReply implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null;
		String fId = request.getParameter("fId");
		System.out.println("replyId:"+fId);
		String tag = request.getParameter("tag");
		String replyContent = request.getParameter("reply");
		String name = request.getParameter("rName");
		String id = request.getParameter("rId");
		String profileimg = request.getParameter("rProfile");
		

		Date nowDate = new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(nowDate);
		String uhr = new SimpleDateFormat("kk").format(nowDate);
		String min = new SimpleDateFormat("mm").format(nowDate);
		String sec = new SimpleDateFormat("ss").format(nowDate);
		int times = Integer.parseInt(uhr) * 60 * 60 + Integer.parseInt(min) * 60 + Integer.parseInt(sec);
		String julian = new SimpleDateFormat("'0'yyD").format(nowDate);

		// request.getServletContext().setAttribute(tag+"reply", "txt" + " " + julian +
		// "" + times + " " + date + " " + replyContent);

		int i = 0;
		while (true) {
			String key = tag + ".reply." + i;
			System.out.println("키값: " + key);
			String app = (String) request.getServletContext().getAttribute(key);
			System.out.println(app);
			if (app == null) {
				request.getServletContext().setAttribute(key, "txt " + id + " " + julian + " " + times + " " + date + " "
						+ name + " " + profileimg + " " + replyContent);
				// if (txt.equals("img")) {
				// request.getServletContext().setAttribute("img." + key, file);
				// } else if (txt.equals("vid")) {
				// request.getServletContext().setAttribute("vid." + key, file);
				// }
				System.out.println("타임라인 댓글 등록 완료");
				break;
			} else {
				i++;
			}
		}

		if (fId != null) {
			viewPage = "freundView.jsp";
		} else {
			viewPage = "index.do";
		}
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
