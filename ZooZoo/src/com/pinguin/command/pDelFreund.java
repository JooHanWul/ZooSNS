package com.pinguin.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pinguin.dto.pinguinDto;

public class pDelFreund implements action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> meinFreund = new ArrayList<>();
		HttpSession session = request.getSession();
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		String fid = request.getParameter("fid");

		String path = request.getSession().getServletContext().getRealPath("/freund");
		File file = new File(path + "/freund" + dto.getId() + ".txt");
		System.out.println(file);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			file.createNewFile();
		}

		String line = null;
		while ((line = br.readLine()) != null) {
			meinFreund.add(line);
		}
		meinFreund.remove(meinFreund.indexOf(fid));

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (FileNotFoundException e) {
			file.createNewFile();
		}

		for (String f : meinFreund) {
			bw.write(f);
			bw.newLine();
			bw.flush();
		}

		bw.close();

		String viewPage = "freund.do";

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
