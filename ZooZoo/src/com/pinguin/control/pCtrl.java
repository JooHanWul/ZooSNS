package com.pinguin.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinguin.command.action;
import com.pinguin.command.pAddFreund;
import com.pinguin.command.pChatting;
import com.pinguin.command.pDelFreund;
import com.pinguin.command.pDelTimeline;
import com.pinguin.command.pError;
import com.pinguin.command.pFreund;
import com.pinguin.command.pFreundView;
import com.pinguin.command.pIndex;
import com.pinguin.command.pInfoUpdate;
import com.pinguin.command.pJoin;
import com.pinguin.command.pLogin;
import com.pinguin.command.pLogout;
import com.pinguin.command.pProfile;
import com.pinguin.command.pProfileUpdate;
import com.pinguin.command.pReadTimeLine;
import com.pinguin.command.pReply;
import com.pinguin.command.pTimeLineUpload;
import com.pinguin.command.pUpdate;

/**
 * Servlet implementation class login
 */
@WebServlet("*.do")
public class pCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private action ac = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public pCtrl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String uri = request.getRequestURI();
		// System.out.println("uri: " + uri);
		String conPath = request.getContextPath();
		// System.out.println("conPatrh: " + conPath);
		String command = uri.substring(conPath.length());
		// System.out.println("command: " + command);

		if (command.equals("/login.do")) {
			System.out.println("Action login");
			ac = new pLogin();
			ac.execute(request, response);
		} else if (command.equals("/join.do")) {
			System.out.println("Action join");
			ac = new pJoin();
			ac.execute(request, response);
		} else if (command.equals("/logout.do")) {
			System.out.println("Action logout");
			ac = new pLogout();
			ac.execute(request, response);
		} else if (command.equals("/error.do")) {
			System.out.println("Action Error");
			ac = new pError();
			ac.execute(request, response);
		} else if (command.equals("/update.do")) {
			System.out.println("Action user update");
			ac = new pUpdate();
			ac.execute(request, response);
		} else if (command.equals("/infoUpdate.do")) {
			System.out.println("Action user infoUpdate");
			ac = new pInfoUpdate();
			ac.execute(request, response);
		} else if (command.equals("/index.do")) {
			System.out.println("Action index");
			ac = new pIndex();
			ac.execute(request, response);
		} else if (command.equals("/timeLineUpload.do")) {
			System.out.println("Action timeLineUpload");
			ac = new pTimeLineUpload();
			ac.execute(request, response);
		} else if (command.equals("/readTimeLine.do")) {
			System.out.println("Action Read Timeline");
			ac = new pReadTimeLine();
			ac.execute(request, response);
		} else if (command.equals("/profile.do")) {
			System.out.println("Action Profile");
			ac = new pProfile();
			ac.execute(request, response);
		} else if (command.equals("/profileUpdate.do")) {
			System.out.println("Action Profile Update");
			ac = new pProfileUpdate();
			ac.execute(request, response);
		} else if (command.equals("/freund.do")) {
			System.out.println("Action freund Update");
			ac = new pFreund();
			ac.execute(request, response);
		} else if (command.equals("/addFreund.do")) {
			System.out.println("Action Add Freund");
			ac = new pAddFreund();
			ac.execute(request, response);
		} else if (command.equals("/delFreund.do")) {
			System.out.println("Action Delete Freund");
			ac = new pDelFreund();
			ac.execute(request, response);
		} else if (command.equals("/reply.do")) {
			System.out.println("Action Reply");
			ac = new pReply();
			ac.execute(request, response);
		} else if (command.equals("/freundView.do")) {
			System.out.println("Action freundView");
			ac = new pFreundView();
			ac.execute(request, response);
		} else if (command.equals("/chatting.do")) {
			System.out.println("Action Chatting");
			ac = new pChatting();
			ac.execute(request, response);
		} else if (command.equals("/delTimeline.do")) {
			System.out.println("Action Delete Timeline");
			ac = new pDelTimeline();
			ac.execute(request, response);
		}
	}
}
