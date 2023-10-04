package com.xkazxx.fils.logutil;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/10/4 22:48
 **/
public class BomWhitePatternConverter extends BomPatternConverter {
	@Override
	public String process(String msg) {
		return "false";
	}

	@Override
	public boolean hasAfterProcess(String msg) {
		return false;
	}
}
