package com.pinguin.util;

import java.util.Comparator;

import com.pinguin.dto.freunfDto;
import com.pinguin.dto.replyDto;

public class replyListSet implements Comparator<replyDto> {

	@Override
	public int compare(replyDto o1, replyDto o2) {
		if (o1.getuDate() > o2.getuDate()) {
			return 1;
		} else if (o1.getuDate() < o2.getuDate()) {
			return -1;
		} else {
			return 0;
		}

	}

}
