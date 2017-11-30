package com.pinguin.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dto.pinguinDto;

public class pReadTimeLine implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null;
		HttpSession session = request.getSession();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		String[] timelineRead = null;
		ArrayList<String[]> timelines = new ArrayList<>();
		int i = 0;
		StringTokenizer tokenizer = null;
		while (true) {
			String getTag = dto.getId() + "." + i;
			System.out.println(getTag);
			String str = (String) request.getServletContext().getAttribute(getTag);
			System.out.println("타임라인 내용: " + str);
			if (str != null) {
				tokenizer = new StringTokenizer(str);
				if (tokenizer.nextToken().equals("txt")) {
					timelineRead = new String[3];
					timelineRead[0] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // julian date
					timelineRead[1] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					timelineRead[2] = ""; // context
					while (tokenizer.hasMoreTokens()) {
						timelineRead[2] += tokenizer.nextToken() + " ";
					}
					timelines.add(timelineRead);
				}
				i++;
			} else {
				break;
			}
		}

		if (timelines != null) {
			// 리스트 뒤집어 주기
			Collections.reverse(timelines);
		}
		String key = dto.getId() + "Timeline";
		System.out.println("등록키값: "+key);
		session.setAttribute(key, timelines);
		System.out.println("페이지 이동");
		viewPage = "index.do";
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);

	}
}
