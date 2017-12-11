package com.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private  String className;
    private Logger logger;
    public Log(Class<?> clazz){
        this.className = clazz.getName();
        this.logger = LogManager.getLogger(clazz);
    }
    public  Log(String className){
        this.className = className;
        this.logger = LogManager.getLogger(String.valueOf(Thread.currentThread().getId()));
    }

    public void info(String message) {
        String me = parseMessage(message);
        //logger.info(clazz.getCanonicalName() + ": " + message);
        logger.info(me);
    }


    public void debug(String message) {
        String me = parseMessage(message);
        logger.debug(me);
        //logger.debug(clazz.getCanonicalName() + ": " + message);
    }

    public void error(String message) {

        String me = parseMessage(message);
        logger.error(me);
        //logger.error(clazz.getCanonicalName() + ": " + message);
    }


    public void trace(String message) {
        String me = parseMessage(message);
        logger.trace(me);
        //logger.trace(clazz.getCanonicalName() + ": " + message);
    }


    public void warn(String message) {

        String me = parseMessage(message);
        logger.warn(me);
        //logger.warn(clazz.getCanonicalName() + ": " + message);
    }


    public void fatal(String message) {
        String me = parseMessage(message);
        logger.fatal(me);
        //logger.fatal(clazz.getCanonicalName() + ": " + message);
    }

    private String parseMessage(Object message){
        return "[Thread: "+String.valueOf(Thread.currentThread().getId())+"]("+className+")  "+message;
    }

}
