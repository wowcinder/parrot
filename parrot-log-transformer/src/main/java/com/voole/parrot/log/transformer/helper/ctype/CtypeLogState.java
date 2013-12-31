/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.ctype;

public class CtypeLogState {
	private boolean isMatch;
	private int repeatTimes;
	private boolean isAddEmptyEnd;

	public CtypeLogState() {
		isMatch = false;
		repeatTimes = 0;
		isAddEmptyEnd = false;
	}

	public boolean isMatch() {
		return isMatch;
	}

	public int getRepeatTimes() {
		return repeatTimes;
	}

	public boolean isAddEmptyEnd() {
		return isAddEmptyEnd;
	}

	public void setMatch(boolean isMatch) {
		this.isMatch = isMatch;
	}

	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}

	public void setAddEmptyEnd(boolean isAddEmptyEnd) {
		this.isAddEmptyEnd = isAddEmptyEnd;
	}

}