package be.ucll.taskmanager.domain.service;

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
