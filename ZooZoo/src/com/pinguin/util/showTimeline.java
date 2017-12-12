package com.pinguin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pinguin.dao.pinguinDao;
import com.pinguin.dto.freunfDto;
import com.pinguin.dto.replyDto;

public class showTimeline {

	public ArrayList<freunfDto> list(HttpServletRequest request, String uId) {
		HttpSession session = request.getSession();

		pinguinDao dao = new pinguinDao();
		String[] timelineRead = null;
		freunfDto fDto = null;
		ArrayList<freunfDto> timelines = new ArrayList<>();
		timelines = new ArrayList<>();
		StringTokenizer tokenizer = null;
		ArrayList<String> fList = (ArrayList<String>) session.getAttribute("pinguinFreund");
		String[] fInfo = null;

		if (!fList.contains(uId)) {
			fList.add(uId);
		}

		System.out.println(fList.toString());

		for (String f : fList) {
			int i = 0;
			while (true) {
				String getTag = f + "." + i;
				fInfo = dao.freundInfo(f);

				System.out.println(getTag);
				String str = (String) request.getServletContext().getAttribute(getTag);
				System.out.println("타임라인 내용: " + str);
				if (str != null) {
					tokenizer = new StringTokenizer(str);
					timelineRead = new String[5];
					timelineRead[0] = fInfo[0]; // Name
					timelineRead[1] = fInfo[1]; // profile Image
					String firstToken = tokenizer.nextToken();
					if (firstToken.equals("txt")) { // 타임라인이 텍스트로만 구성
						timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
						timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
						timelineRead[4] = "<input type='hidden' value='" + getTag + "' name='tag'>"; // context
						while (tokenizer.hasMoreTokens()) {
							timelineRead[4] += tokenizer.nextToken() + " ";
						}
						fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
								timelineRead[4], timelineRead[1], getTag);
						timelines.add(fDto);
					} else if (firstToken.equals("img")) { // 타임라인이 텍스트와 사진으로 구성
						timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
						timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
						timelineRead[4] = "<img alt='' src='FileFolder/"; // html 이미지 태그
						timelineRead[4] += (String) request.getServletContext().getAttribute("img." + getTag); // context
						timelineRead[4] += "' width='466'><hr size='1' color='#ffffff'>";
						timelineRead[4] += "<input type='hidden' value='" + getTag + "' name='tag'>";
						while (tokenizer.hasMoreTokens()) {
							timelineRead[4] += tokenizer.nextToken() + " ";
						}
						fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
								timelineRead[4], timelineRead[1], getTag);
						timelines.add(fDto);
					} else if (firstToken.equals("vid")) { // 타임라인이 텍스트와 동영상으로 구성
						timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
						timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
						timelineRead[4] = "<video src='FileFolder/"; // html 동영상 태그
						timelineRead[4] += (String) request.getServletContext().getAttribute("vid." + getTag); // context
						timelineRead[4] += "' controls='controls' width='466'><hr size='1' color='#ffffff'>";
						timelineRead[4] += "<input type='hidden' value='" + getTag + "' name='tag'>";
						while (tokenizer.hasMoreTokens()) {
							timelineRead[4] += tokenizer.nextToken() + " ";
						}
						fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
								timelineRead[4], timelineRead[1], getTag);
						timelines.add(fDto);
					}
					i++;
				} else {
					break;
				}
			}
		}

		return timelines;
	}

	public ArrayList<freunfDto> fList(HttpServletRequest request, String f) {
		HttpSession session = request.getSession();

		pinguinDao dao = new pinguinDao();
		String[] timelineRead = null;
		freunfDto fDto = null;
		ArrayList<freunfDto> timelines = new ArrayList<>();
		timelines = new ArrayList<>();
		StringTokenizer tokenizer = null;
		String[] fInfo = null;

		int i = 0;
		while (true) {
			String getTag = f + "." + i;
			fInfo = dao.freundInfo(f);

			System.out.println(getTag);
			String str = (String) request.getServletContext().getAttribute(getTag);
			System.out.println("타임라인 내용: " + str);
			if (str != null) {
				tokenizer = new StringTokenizer(str);
				timelineRead = new String[5];
				timelineRead[0] = fInfo[0]; // Name
				timelineRead[1] = fInfo[1]; // profile Image
				String firstToken = tokenizer.nextToken();
				if (firstToken.equals("txt")) { // 타임라인이 텍스트로만 구성
					timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
					timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					timelineRead[4] = "<input type='hidden' value='" + getTag + "' name='tag'>"; // context
					while (tokenizer.hasMoreTokens()) {
						timelineRead[4] += tokenizer.nextToken() + " ";
					}
					fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
							timelineRead[4], timelineRead[1], getTag);
					timelines.add(fDto);
				} else if (firstToken.equals("img")) { // 타임라인이 텍스트와 사진으로 구성
					timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
					timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					timelineRead[4] = "<img alt='' src='FileFolder/"; // html 이미지 태그
					timelineRead[4] += (String) request.getServletContext().getAttribute("img." + getTag); // context
					timelineRead[4] += "' width='466'><hr size='1' color='#ffffff'>";
					timelineRead[4] += "<input type='hidden' value='" + getTag + "' name='tag'>";
					while (tokenizer.hasMoreTokens()) {
						timelineRead[4] += tokenizer.nextToken() + " ";
					}
					fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
							timelineRead[4], timelineRead[1], getTag);
					timelines.add(fDto);
				} else if (firstToken.equals("vid")) { // 타임라인이 텍스트와 동영상으로 구성
					timelineRead[2] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
					timelineRead[3] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					timelineRead[4] = "<video src='FileFolder/"; // html 동영상 태그
					timelineRead[4] += (String) request.getServletContext().getAttribute("vid." + getTag); // context
					timelineRead[4] += "' controls='controls' width='466'><hr size='1' color='#ffffff'>";
					timelineRead[4] += "<input type='hidden' value='" + getTag + "' name='tag'>";
					while (tokenizer.hasMoreTokens()) {
						timelineRead[4] += tokenizer.nextToken() + " ";
					}
					fDto = new freunfDto(Integer.parseInt(timelineRead[2]), timelineRead[3], timelineRead[0],
							timelineRead[4], timelineRead[1], getTag);
					timelines.add(fDto);
				}
				i++;
			} else {
				break;
			}
		}

		return timelines;
	}

	public ArrayList<replyDto> reList(HttpServletRequest request, freunfDto s) {
		StringTokenizer tokenizer = null;
		replyDto rDto = null;
		ArrayList<replyDto> reLines = new ArrayList<>();
		String[] replyRead = null;
		System.out.println("댓글 출력 준비");
		int i = 0;
		
		while (true) {
			String reply = s.getTag() + ".reply." + i;
			System.out.println(reply);
			String str = (String) request.getServletContext().getAttribute(reply);
			if (str != null) {
				tokenizer = new StringTokenizer(str);
				replyRead = new String[6];
				replyRead[4] = "";
				String firstToken = tokenizer.nextToken();
				if (firstToken.equals("txt")) { // 댓글이 텍스트로만 구성
					replyRead[0] = tokenizer.nextToken(); // Id
					replyRead[1] = tokenizer.nextToken() + "" + tokenizer.nextToken(); // julian date
					replyRead[2] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					replyRead[4] = tokenizer.nextToken(); // Name
					replyRead[5] = tokenizer.nextToken(); // Profile Image
					replyRead[3] = ""; // context
					while (tokenizer.hasMoreTokens()) {
						replyRead[3] += tokenizer.nextToken() + " ";
					}
				}
				int julius = Integer.parseInt(replyRead[1]);
				rDto = new replyDto(julius, replyRead[2], replyRead[4], replyRead[3], replyRead[5], replyRead[0]);
				reLines.add(rDto);
			} else {
				break;
			}
			i++;
		}

		return reLines;
	}
	
}
