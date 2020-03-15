package be.ucll.taskmanager.service;

import java.security.Provider;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Exception e){
        super(message, e);
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Exception e){
        super(e);
    }
}
