package com.xkazxx.fils.logutil;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/10/4 22:41
 **/
public abstract class BomPatternConverter {
	public BomPatternConverter next;

	public abstract String process(String msg);

	public abstract boolean hasAfterProcess(String msg);
}
