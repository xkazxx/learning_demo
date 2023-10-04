package com.xkazxx.fils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * @version v0.1
 * @author: created by xkazxx
 * @description: description
 * @date: 2023/10/4 22:00
 **/
public class Log4jTestMain {


	public static void main(String[] args) {
//		DOMConfigurator.configure("log4j.xml");
		DOMConfigurator.configure("useful-util/src/main/resources/log4j.xml");
		Logger logger = Logger.getLogger("org.apache.log4j.xml");

		logger.info("++++++++++");
	}
}