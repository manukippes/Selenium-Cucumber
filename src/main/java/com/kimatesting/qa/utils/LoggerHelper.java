package com.kimatesting.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {

    public static Logger log;

    /**
     * Create log static variable
     */
    public static void createLogger(){
        log = LoggerFactory.getLogger(LoggerHelper.class);
    }
}
