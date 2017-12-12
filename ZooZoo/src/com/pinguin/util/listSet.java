package com.pinguin.util;

import java.util.Comparator;

import com.pinguin.dto.freunfDto;

public class listSet implements Comparator<freunfDto> {

	@Override
	public int compare(freunfDto o1, freunfDto o2) {
		if (o1.getuDate() > o2.getuDate()) {
			return -1;
		} else if (o1.getuDate() < o2.getuDate()) {
			return 1;
		} else {
			return 0;
		}

	}

}
