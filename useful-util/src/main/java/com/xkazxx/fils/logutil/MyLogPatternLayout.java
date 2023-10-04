package com.xkazxx.fils.logutil;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/10/4 22:34
 **/
public class MyLogPatternLayout extends Layout {
	 private BomPatternConverter header;
	@Override
	public String format(LoggingEvent loggingEvent) {
		return null;
	}

	@Override
	public boolean ignoresThrowable() {
		return false;
	}

	@Override
	public void activateOptions() {

	}

//	crlf过滤
//	类路径判断
//	自定义白名单判断
//	自定义日志字符规则判断
//	日志输出格式组装

}
