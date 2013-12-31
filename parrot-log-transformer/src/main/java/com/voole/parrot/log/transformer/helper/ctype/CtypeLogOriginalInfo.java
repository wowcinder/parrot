/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.ctype;

public class CtypeLogOriginalInfo {
	private final Integer length;
	final Boolean isVerticalEnding;
	private final String[] originalStrs;

	public CtypeLogOriginalInfo(String log) {
		if (log.charAt(log.length() - 1) == '|') {
			isVerticalEnding = true;
		} else {
			isVerticalEnding = false;
		}
		originalStrs = log.split("\\||\\t");
		this.length = originalStrs.length;
	}

	public String[] getOriginalStrs() {
		return originalStrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj instanceof CtypeLogOriginalInfo) {
			CtypeLogOriginalInfo that = (CtypeLogOriginalInfo) obj;
			return this.getLength().equals(that.getLength())
					&& this.getIsVerticalEnding().equals(
							that.getIsVerticalEnding());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getLength().hashCode() + 3
				* this.getIsVerticalEnding().hashCode();
	}

	public Integer getLength() {
		return length;
	}

	public Boolean getIsVerticalEnding() {
		return isVerticalEnding;
	}

}