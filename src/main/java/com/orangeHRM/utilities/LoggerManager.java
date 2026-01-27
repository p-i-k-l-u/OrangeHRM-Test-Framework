package com.orangeHRM.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangeHRM.base.BaseClass;

public class LoggerManager {

	// This method return a Logger Instance for the Provides class

	public static Logger getLogger(Class<BaseClass> clazz) {

		return LogManager.getLogger();
	}

}
